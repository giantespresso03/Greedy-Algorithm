package com.company;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class Main {​
    // Function to read in file as a string, parse through it later
    public static String readFile(String fileName) throws Exception {​
        String data = "";
        File f = new File(fileName);
        // Error check if file exists
        if (f.exists() && !f.isDirectory()) {​
            data = new String(Files.readAllBytes(Paths.get(fileName)));
            return data;
        }​else{​
            System.out.println("File does not exist");
            System.exit(0);
        }​
        return fileName;
    }​
    public static void main(String args[]) throws Exception {​
        // Read in the file of names
        System.out.println("Enter File Name: ");
        Scanner sfScan = new Scanner(System.in);
        String data = readFile(sfScan.next());
        // Split the names by new line characters
        String[] names = data.split("\n", 0);
        // Create a graph
        Graph graph = new MultiGraph("Tutorial");
        // Display Graph
        graph.display();
        // Create a vector filled with each name
        Vector<People> list = new Vector<People>();
        // Makes sure names are without the end line character
        for (int i = 0; i < names.length; i++) {​
            names[i] = names[i].substring(0, names[i].length() - 1);
        }​
        // Values to hold number of couples, number of singles, and number of total people
        int singles = 0;
        int couples = 0;
        int totWeight = 0;
        // Add in each person to the vector while adding them into the graph as nodes
        for (int i = 0; i < names.length; i++) {​
            list.add(new People(names[i]));
            if (list.lastElement().weight == 1){​
                singles++;
            }​else{​
                couples++;
            }​
            // Read in total weight of people
            totWeight += list.elementAt(i).weight;
            graph.addNode(names[i]);
        }​
        // Create an adjacency matrix for the name list
        boolean[][] adj = new boolean[names.length][names.length];
        // Initialize nodes so that they cannot visit themselves, do it by initialize their spot in the matrix to true
        for (int i = 0; i < names.length; i++) {​
            adj[i][i] = true;
        }​
        int groupSize;
        int groups;
        boolean valid = true;
        Scanner myScan = new Scanner(System.in);
        // Ensure that user input makes a valid group size and group number
        do{​
            valid = true;
            do{​
                System.out.println("Enter Group Number");
                groups = myScan.nextInt();
            }​ while (groups <= 0);
            groupSize = totWeight / groups;
            if (groupSize < 2 || groupSize > totWeight || (groupSize == 2 && couples > 0) || (groupSize == 3 && couples > 1) || (groupSize % 2 == 0 && singles == 1) || (groupSize % 2 == 1 && singles == 0)){​
                valid = false;
            }​
        }​while (!valid);
        int rem = totWeight% groups;
        // Number of total connections to be made will always be n * (n - 1), when we hit this number, we are done
        int connection = names.length * (names.length - 1);
        int counter = 0;
        // Iterator to help print out the groups
        int iterationNum = 1;
        // Create a vector of groups to hold all groups for this iteration
        Vector<Group> gList = new Vector<Group>();
        // While we do not have n * (n - 1) connections
        while (counter < connection) {​
            int remCount = rem;
            // Iterate through the hosts, if any of the hosts are missing a visitor, then they host a group
            for (int i = 0; i < names.length; i++) {​
                // Iterate through the column to see visitors of person i
                for (int j = 0; j < names.length; j++) {​
                    // If a person has not visited i, and they are free, add them into a group with i
                    if (adj[j][i] == false && list.get(j).free == true) {​
                        // If i is free, create a group with them in it
                        if (list.get(i).free) {​
                            // If there are remainders, make the group size 1 more
                            if (remCount > 0){​
                                gList.add(new Group(groupSize + 1));
                                remCount = remCount - 1;
                            }​else {​
                                gList.add(new Group(groupSize));
                            }​
                            gList.lastElement().Add((list.get(i)));
                            list.get(i).free = false;
                        }​
                        // Check if the person to be added can fit into the group, if they can add them, and create connection
                        if (gList.lastElement().Check(list.get(j))) {​
                            gList.lastElement().Add(list.get(j));
                            adj[j][i] = true;
                            counter++;
                            list.get(j).free = false;
                            graph.addEdge("a" + counter , list.get(i).name, list.get(j).name);
                        }​
                    }​
                }​
                // If this host needs no more visitors, the move on
                if (!gList.isEmpty()) {​
                    // If the current group is not full, the host needs to have guests they have already had before
                    if (gList.lastElement().isFull() == false) {​
                        for (int t = 0; t < names.length; t++) {​
                            if (list.get(t).free == true && t != i && gList.lastElement().Check(list.get(t))) {​
                                gList.lastElement().Add(list.get(t));
                                list.get(t).free = false;
                            }​
                            if (gList.lastElement().isFull()) {​
                                break;
                            }​
                        }​
                    }​
                }​
            }​
            // Force number of groups to always be equal to group number
            if (gList.size() != groups){​
                for (int q = 0; q < names.length; q++){​
                    for (int y = 0; y < names.length; y++){​
                        if (list.get(q).free && list.get(y).free){​
                            // If there are remainders, create a group size of 1 more
                            if (remCount > 0){​
                                gList.add(new Group(groupSize + 1));
                                remCount = remCount - 1;
                            }​else {​
                                gList.add(new Group(groupSize));
                            }​
                            gList.lastElement().Add(list.get(q));
                            list.get(q).free = false;
                            list.get(y).free = false;
                        }​
                        if (list.get(y).free){​
                            if (gList.lastElement().Check(list.get(y))){​
                                gList.lastElement().Add(list.get(y));
                                list.get(y).free = false;
                            }​
                        }​
                    }​
                }​
            }​
            // Change everyone's availability to true
            for (int z = 0; z < names.length; z++){​
                list.get(z).free = true;
            }​
            // Print out each iteration
            System.out.println("Iteration: " + iterationNum);
            for (int q = 0; q < gList.size(); q++){​
                System.out.print("G" + iterationNum);
                System.out.print(",");
                System.out.print(q + 1);
                System.out.print(":");
                System.out.println(gList.get(q).occupants);
            }​
            System.out.print("\n");
            // Clear the groups for the iteration
            gList.clear();
            iterationNum++;
        }​
    }​
}​
