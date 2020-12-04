# Greedy Algorithm

### Used IDE: IntelliJ
### Used Language: Java

### Project Introduction:
  Along with the rest of CSC 3430 class, which was directed by Dr. Arias, our group (Matthew Tang and Junwon Song) was assigned to design and develop a program that can help out with a church community activity, which is having every member going to everyone’s home. However, the program has to be set up under these conditions:
- The user has to enter in how many people will be assigned per group
- If there's a married couple, married couple always go together
- If there's a leftover person after assigning everyone to a full group, then the leftover person will be added to the last full group

### Project Description:
  In terms of coding language, we decided to use Jave due to its flexibility with libraries and our familiarity with C++'s syntax. For example, thanks to the utilization of libraries, forming a graph in Java was relatively easier due to the fact that we were able to use a library called `'JGraphT'` and it was a matter of calling few functions to create, and connect the nodes with the edges. Additionally, animating the graph was very straightforward as well as we used the library called `'GraphStream'` which helped us visually represent nodes and edges on a separate tab.

### Project Requirements:
  We installed and used `GraphStream` library through `Maven` for this program. For a reference, the artifact is `org.graphstream:`. For a manual installation of the `GraphStream` library, we have also included a file called gs-core1.3.jar as well.

### User Manual
Before attempting to start the program, it is crucial for the user to create a .txt file that has an endline at the end of the file for the program to work as intended.

In order to start and run the program as intended, please follow these steps to effectively utilize the program:

1. Build and run the program through preferred IDE (`GraphStream` and `JGraphT` libraries has to be installed)
2. Create and enter the appropriate file name (Please take a look at picture #2 for a desired format)
3. Enter the desired group size

If the user didn't encounter an error for step 2 and 3, the program should successfully run and display two results:

1. Visualization of every single iteration on the console
2. Animated visualization of the graph that the program has created

### Result/Reflection
  When our group first tackled this project, primarily, we were focused on trying to make this program as efficient as possible. We tried many different algorithms to reduce the memory of this program and even tried to research if there's any other ways to reduce the time complexity from O(N^3) as well. As a result, we have realized that O(N^3) would be the most efficient time complexity for this problem and decided to stick with it.
  After the program was constructed initially, our group took a step further and tried our best to make the program as optimized as possible in order to further boost the efficiency of our program. As a result, we were able to deepen our understandings of graph, time complexity, and optimization.
