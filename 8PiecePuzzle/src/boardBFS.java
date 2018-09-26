import java.io.*;
import java.util.*;

public class boardBFS{
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
		// for(int i=0;i<stringarray.length;i++)
		// {
		// 	System.out.print(stringarray[i]);
		// }
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
		String goal = bfs(finalArray, s);
		System.out.println(goal);
		System.out.println("Total number of search nodes added to the frontier queue: "+counter);
		System.out.println("Total number of search nodes selected from the frontier queue for expansion: "+counter2);
		System.out.println("Maximum size of the search queue at any given time during the search: "+counter3);

		// for(int i=0;i<finalArray.length;i++)
		// {
		// 	for(int j =0;j<finalArray.length;i++)
		// 	{
		// 		System.out.println(finalArray[i][j] + " ");
		// 	}
		// }

		// for (int i = 0; i < goal.board.length; i++) {
		//     for (int j = 0; j < goal.board[i].length; j++) {
		//         System.out.print(goal.board[i][j] + " ");
		//     }

		//     System.out.println();
		// }

		// while((line=stringbuffer.readLine()) !=null){

		// }

		 int[][] testBoard = new int[3][3];
		 testBoard[0][0] = 1;
		 testBoard[0][1] = 2;
		 testBoard[0][2] = 5;
		 testBoard[1][0] = 3;
		 testBoard[1][1] = 4;
		 testBoard[1][2] = 0;
		 testBoard[2][0] = 6;
		 testBoard[2][1] = 7;
		 testBoard[2][2] = 8;

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


		//goUp(matrix,1,2);
		//goDown(matrix,1,2);
		//goLeft(matrix,1,2);
		//goRight(matrix2,0,0);
		// for (int i = 0; i < matrix2.length; i++) {
		//     for (int j = 0; j < matrix2[i].length; j++) {
		//         System.out.print(matrix2[i][j] + " ");
		//     }

		//     System.out.println();
		// }

		//System.out.println(isGoal(matrix4));
		//System.out.println(goUp(matrix,1,2));

		 //System.out.println(testBoard[1][1]);

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

	public static String bfs(int[][] a, String path){
		// node a = new node(finalArray, null);
		// bfs(a);
		// a.board = 
		Queue<int[][]> bq = new LinkedList<>();
		Queue<String> pq = new LinkedList<>();
		bq.add(a);
		pq.add(path);
		counter++;

		int c = 0;

		while(!bq.isEmpty()){
			int[][] board = bq.remove();
			if(counter2>=100000){ System.out.println("no solution found (100k limit reached") ;break;}
			String p = pq.remove();;
			counter2++;
			if(counter3<bq.size()){counter3=bq.size();}
			//visited.add(temp);
			//System.out.println(queue.size());
			
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

			// for(int i = 0; i < board.length; i++){
			// 	for(int j = 0; j < board.length; j++){
			// 		System.out.print(board[i][j] + " ");
			// 	}
			// 	System.out.println();
			// }

			if(isGoal(board)){
				return p;
			}
			//create a int[][] and store goUp and chekc if null and then use tht var in the if; point is to not perform twice
			

			// for(int i = 0; i < board.length; i++){
			// 	for(int j = 0; j < board.length; j++){
			// 		System.out.print("u" + temp2[i][j] + " ");
			// 	}
			// 	System.out.println();
			// }
			String r="",d="",u="",l="";
			int[][] temp2 = goUp(board, inR, inC);
			if((temp2!=null)){
				u = p+toString(temp2)+"\n";
				//System.out.println("u");
				//System.out.println(u);
				//System.out.println(queue.size());
			}
			int[][] temp3 = goRight(board, inR, inC);
			if(!(temp3==null)){
				r = p+toString(temp3)+"\n";
				//System.out.println("r");
				//System.out.println(r);
			}

			// for(int i = 0; i < board.length; i++){
			// 	for(int j = 0; j < board.length; j++){
			// 		System.out.print("r" + temp3[i][j] + " ");
			// 	}
			// 	System.out.println();
			// }

			int[][] temp4 = goLeft(board,inR, inC);
			if(!(temp4==null)){
				l = p+toString(temp4)+"\n";
				//System.out.println("l");
				//System.out.println(l);

			}
			int[][] temp5 = goDown(board,inR, inC);
			if(!(temp5==null)){
				d = p+toString(temp5)+"\n";
				//System.out.println("d");
				//System.out.println(d);
			}

			// for(int i = 0; i < board.length; i++){
			// 	for(int j = 0; j < board.length; j++){
			// 		System.out.print("bb" + board[i][j] + " ");
			// 	}
			// 	System.out.println();
			// }


			if(temp2 != null) { bq.add(temp2); pq.add(u); counter++; }
			if(temp3 != null) { bq.add(temp3); pq.add(r); counter++; }
			if(temp4 != null) { bq.add(temp4); pq.add(l); counter++; }
			if(temp5 != null) { bq.add(temp5); pq.add(d); counter++; }
			

		}
		return null;
	}


}



