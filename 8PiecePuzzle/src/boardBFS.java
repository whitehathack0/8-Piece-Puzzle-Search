import java.io.*;
import java.util.*;

public class boardBFS{

	static int counter = 0;  // Total number of search nodes added to the frontier queue
	static int counter2 = 0; // Total number of search nodes selected from the frontier queue for expansion
	static int counter3 = 0; // Maximum size of the search queue at any given time during the search

	private static class node{
		private static int[][] board;
	 	private static String path;

	 	public node(int[][] b, String p){
	 		board = b;
	 		path = p;
	 	}
	}

	public static void main(String[] args) throws Exception {

		File fileIn = new File(args[0]);
		FileReader filereader = new FileReader(fileIn);
		BufferedReader reader = new BufferedReader(filereader);
		String line;

		line = reader.readLine();
		String stringArray[] = line.split(" ");
		
		int n = stringArray.length;
		int[][] finalArray = new int[n][n];

		//Set input of the user to the finalArray[][]
		for(int r=0;r<n;r++){ 
				stringArray = line.split(" ");
			for(int c=0;c<n;c++){
				if(stringArray[c].equals(".")){finalArray[r][c] = 0;}
					else finalArray[r][c] = Integer.parseInt(stringArray[c]);
			}
			line = reader.readLine();
		}

		String s = toString(finalArray)+"\n";
		String goal = bfs(finalArray, s);
		System.out.println(goal);
		System.out.println("Total number of search nodes added to the frontier queue: "+counter);
		System.out.println("Total number of search nodes selected from the frontier queue for expansion: "+counter2);
		System.out.println("Maximum size of the search queue at any given time during the search: "+counter3);


		// Sample inputs:

		 int[][] matrix = {
		  { 1, 2, 5 },
		  { 3, 4, 0 },
		  { 6, 7, 8 }
		};

		int[][] matrix2 = {
		  { 0, 1, 2 },
		  { 3, 4, 5 },
		  { 6, 7, 8 }
		};

		int[][] matrix3 = {
		  { 8, 1, 2 },
		  { 3, 4, 5 },
		  { 6, 7, 0 }
		};

		int[][] matrix4 = {
		  { 8, 1, 2 },
		  { 3, 4, 5 },
		  { 0, 7, 6 }
		};
	}

	/*  isRightValue() : Check if supplied board and positon is in the correct positon
		Param:  Board: currrent board state
				row: row positon within board
				col" col poisiton within board
	 */
	public static boolean isRightValue(int[][] board, int row, int col){
		int n = board.length;
		return(((row*n)+col) == board[row][col]);
	}

	/*  isGoal() : Check if supplied board is in goal state
		Param:  Board: currrent board state
	 */
	public static boolean isGoal(int[][] board){
		int n = board.length;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(!(isRightValue(board,i,j))){
					return false;
				}
			}
		}
		return true;
	}


	 /*  goUp() : Check if up move is possible, then return resulting board after move
		Param:  Board: currrent board state
				row: row positon within board where '0' is located
				col" col positon within board where '0' is located
	 */
	public static int[][] goUp(int[][] board, int row, int col){ // swapping 0 with element above
		int n = board.length;
		int[][] newboard = new int[n][n];

		/* Copy over given board[][] state to newBoard[][] */
		for(int i=0;i<n;i++){ 
			for(int j=0;j<n;j++){
				newboard[i][j] = board[i][j];
			}
		}

		/* Check if move is possible */
		if(row-1<0){
			return null;
		}
		else{
			int val = newboard[row-1][col]; //store value above current 0
			newboard[row][col] = val; //change value of old position of 0 with temp val
			newboard[row-1][col] = 0; //change value one higher with 0
			
			return newboard;
		}


	}

	/*  goDown() : Check if down move is possible, then return resulting board after move
		Param:  Board: currrent board state
				row: row positon within board where '0' is located
				col" col positon within board where '0' is located
	 */
	public static int[][] goDown(int[][] board, int row, int col){  // swapping 0 with element below
		int n = board.length;
		int[][] newboard = new int[n][n];

		for(int i=0;i<n;i++){ 
			for(int j=0;j<n;j++){
				newboard[i][j] = board[i][j];
			}
		}
		if(row+1>=n){return null;}
		else{
			int val = newboard[row+1][col];
			newboard[row][col] = val;
			newboard[row+1][col] = 0;
			return newboard;
		}
	}

	/*  goLeft() : Check if up move is possible, then return resulting board after move
		Param:  Board: currrent board state
				row: row positon within board where '0' is located
				col" col positon within board where '0' is located
	 */
	public static int[][] goLeft(int[][] board, int row, int col){ // swapping 0 with element to the left
		int n = board.length;
		int[][] newboard = new int[n][n];

		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				newboard[i][j] = board[i][j];
			}
		}
		if(col-1<0){return null;}
		else{
			int val = newboard[row][col-1]; //left
			newboard[row][col] = val;
			newboard[row][col-1] = 0;
		
			return newboard;
		}
	}

	/*  goRight() : Check if up move is possible, then return resulting board after move
		Param:  Board: currrent board state
				row: row positon within board where '0' is located
				col" col positon within board where '0' is located
	 */
	public static int[][] goRight(int[][] board, int row, int col){ // swapping 0 with element to the right
		int n = board.length;
		int[][] newboard = new int[n][n];

		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				newboard[i][j] = board[i][j];
			}
		}
		if(col+1>=n){return null;}
		else{
			int val = newboard[row][col+1]; //right
			newboard[row][col] = val;
			newboard[row][col+1] = 0;
		
			return newboard;
		}
	}

	/*  toString() : Print out the int[][] in matrix format with length and width = length of input int[][]
		Param:  arrayIn: board state
	 */
	public static String toString(int[][] board){
		String out = "";
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board.length;j++){
				out = out+board[i][j] + "  ";
			}
			out = out+"\n";
		}
		return out;
	}



	/*  bfs() : Perform BFS on given board and path taken so far
		Param:  a: Current board state 
				path: Current path of board
	 */
	public static String bfs(int[][] a, String path){  
		Queue<int[][]> bq = new LinkedList<>(); //List that holds all boards
		Queue<String> pq = new LinkedList<>(); //List that holds path
		bq.add(a);
		pq.add(path);
		counter++;

		/* 
			This whole while loop works on one board called board and adds possible moves to the queue
			and works on each board in the bq queue until options are exhausted OR goal state is reached.
		*/

		while(!bq.isEmpty()){ 
			int[][] board = bq.remove(); //dequeue board
			String p = pq.remove(); //dequeue path
			/* Check if the board is the goal and then return it */
			if(isGoal(board)){
				return p;
			}

			if(counter2>=100000){
				System.out.println("No solution found (100k board in queue) ");
				break;
			}
			counter2++;
			if(counter3<bq.size()){
				counter3=bq.size();
			}
			
			int inR=0;
			int inC=0;

			//These nested for-loops finds the current positon of 0 in the board
			for(int i=0;i<board.length;i++){ 
				for(int j=0;j<board.length;j++){
					if(board[i][j]==0){ 
						inR = i;
						inC = j;
						break;
					}
				}
			}
			

			/*  Here we are going to attempt to apply 
				each directional move, up,down,left,right IF possible 
			*/

			String r="",d="",u="",l="";

			/* 
				1. Call each move
				2. If the move is possible add the path 
				   to string var for corresponding move.
				3. Enqueue the board and path to the queue.
				4. Increment counter
			*/

			int[][] temp2 = goUp(board, inR, inC);
			if(temp2!=null){
				u = p+toString(temp2)+"\n"; 
				bq.add(temp2); pq.add(u); counter++;
			}

			int[][] temp3 = goRight(board, inR, inC);
			if(temp3!=null){
				r = p+toString(temp3)+"\n";
				bq.add(temp3); pq.add(r); counter++; 
			}

			int[][] temp4 = goLeft(board,inR, inC);
			if(temp4!=null){
				l = p+toString(temp4)+"\n";
				bq.add(temp4); pq.add(l); counter++;

			}
			int[][] temp5 = goDown(board,inR, inC);
			if(temp5!=null){
				d = p+toString(temp5)+"\n";
				bq.add(temp5); pq.add(d); counter++;
			}
			

		}
		return null;
	}


}
