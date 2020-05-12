import java.io.*;
import java.util.*;

public class boardDFS{
	static int counter = 0;
	static int counter2 = 0;
	static int counter3 = 0;

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
		StringBuffer stringbuffer = new StringBuffer();
		String line;
		

		line = reader.readLine();
		String stringarray[] = line.split(" ");
		int n = stringarray.length;
		int[][] finalArray = new int[n][n];

		for(int i=0;i<n;i++){
			if(stringarray[i].equals(".")){finalArray[0][i] = 0;}
				else finalArray[0][i] = Integer.parseInt(stringarray[i]);
		}

		for(int r=1;r<n;r++){
				line = reader.readLine();
				stringarray = line.split(" ");
			for(int c=0;c<n;c++){
				if(stringarray[c].equals(".")){finalArray[r][c] = 0;}
					else finalArray[r][c] = Integer.parseInt(stringarray[c]);
			}
		}

		String s = toString(finalArray)+"\n";
		String goal = dfs(finalArray, s);
		System.out.println(goal);
		System.out.println("Total number of search nodes added to the frontier queue: "+counter);
		System.out.println("Total number of search nodes selected from the frontier queue for expansion: "+counter2);
		System.out.println("Maximum size of the search queue at any given time during the search: "+counter3);


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

	public static boolean isRightValue(int[][] board, int row, int col){
		int n = board.length;
		return (((row*n)+col) == board[row][col]);
	}

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

	public static int[][] goUp(int[][] board, int row, int col){
		int n = board.length;
		int[][] newboard = new int[n][n];

		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				newboard[i][j] = board[i][j];
			}
		}

		if(row-1<0){return null;}
		else{
			int val = newboard[row-1][col];
			newboard[row][col] = val;
			newboard[row-1][col] = 0;
			
			return newboard;
		}


	}

	public static int[][] goDown(int[][] board, int row, int col){
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

	public static int[][] goLeft(int[][] board, int row, int col){
		int n = board.length;
		int[][] newboard = new int[n][n];

		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				newboard[i][j] = board[i][j];
			}
		}


		if(col-1<0){return null;}
		else{
			int val = newboard[row][col-1];
			newboard[row][col] = val;
			newboard[row][col-1] = 0;
		
			return newboard;
		}
	}

	public static int[][] goRight(int[][] board, int row, int col){
		int n = board.length;
		int[][] newboard = new int[n][n];

		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				newboard[i][j] = board[i][j];
			}
		}

		if(col+1>=n){return null;}
		else{
			int val = newboard[row][col+1];
			newboard[row][col] = val;
			newboard[row][col+1] = 0;
		
			return newboard;
		}
	}

	public static String toString(int[][] arrayIn){
		String out = "";
		for(int i=0;i<arrayIn.length;i++){
			for(int j=0;j<arrayIn.length;j++){
				out = out+arrayIn[i][j] + " ";
			}
			out = out+"\n";
		}
		return out;
	}

	public static String dfs(int[][] a, String path){
		Stack<int[][]> bq = new Stack<>();
		Stack<String> pq = new Stack<>();
		bq.push(a);
		pq.push(path);
		counter++;

		int c = 0;

		while(!bq.isEmpty()){
			int[][] board = bq.pop();
			if(counter2>=100000){ System.out.println("no solution found (100k limit reached") ;break;}
			String p = pq.pop();;
			counter2++;
			if(counter3<bq.size()){counter3=bq.size();}
			
			int inR=0;
			int inC=0;

			for(int i=0;i<board.length;i++){
				for(int j=0;j<board.length;j++){
					if(board[i][j]==0){
						inR = i;
						inC = j;
						break;
					}
				}
			}

			c++;			

			if(isGoal(board)){
				return p;
			}
		
			String r="",d="",u="",l="";
			int[][] temp2 = goUp(board, inR, inC);
			if((temp2!=null)){
				u = p+toString(temp2)+"\n";
			}
			int[][] temp3 = goRight(board, inR, inC);
			if(!(temp3==null)){
				r = p+toString(temp3)+"\n";
			}


			int[][] temp4 = goLeft(board,inR, inC);
			if(!(temp4==null)){
				l = p+toString(temp4)+"\n";
			}
			int[][] temp5 = goDown(board,inR, inC);
			if(!(temp5==null)){
				d = p+toString(temp5)+"\n";
			}

			if(temp3 != null) { bq.push(temp3); pq.push(r); counter++; }
			if(temp4 != null) { bq.push(temp4); pq.push(l); counter++; }
			if(temp5 != null) { bq.push(temp5); pq.push(d); counter++; }
			if(temp2 != null) { bq.push(temp2); pq.push(u); counter++; }
			

		}
		return null;
	}


}
