Author: Aditya Kumar

- Breadth-First Search (MAIN) and Depth-First search

Heuristic used for A*: I used Manhattan distance for my heuristic.

Summary: 
         My implementation includes searching each possibile move on a given matrix (int[][]) and then using a queue to store
         all the possible boards that result from the current board. We also have a path queue that stores the path take in 
         order to print out the steps when goal state is reached. This way we search all possibilities until goal is reached 
         and the path is stored for each board. Search stop when we have serached 100k boards.
         
         The 4x4 is very much more complicated than the 3x3 test cases. 
         Many more nodes were searched and were placed in the explored 
         queue when the board size goes up.

Instructions: 
BFS command line:   java boardBFS testFile.txt
DFS command line:   java boardDFS testFile.txt

Warnings: 
- A* is not working, I have my heuristic implemented.
- DFS is very slow on 3x3...
- BFS works as it should.
