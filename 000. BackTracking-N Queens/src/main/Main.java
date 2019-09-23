package main;

public class Main 
{
	public static void print(int [][]board) {
		for(int i = 0;i < board.length;i++) {
			for(int j = 0;j < board.length;j++)
				System.out.print(board[i][j] + "\t");
			System.out.println();
		}
		System.out.println();
	}
	
	public static boolean isSafe(int [][]board, int x, int y)
	{
		for(int i = 0;i < y;i++)
			if(board[x][i] == 1)
				return false;
		for(int i = x, j = y; i >= 0 && j >= 0;i--, j--)
			if(board[i][j] == 1)
				return false;
		for(int i = x, j = y;i < board.length && j >= 0;i++, j--)
			if(board[i][j] == 1)
				return false;
		return true;			
	}

	
	public static boolean solveNQUtil(int [][]board, int col)
	{
		if(col >= board.length)
		{
			//For All Solution
			print(board);
			return true;
			/* For one Solution
			return true;*/
		}
		for(int i = 0;i < board.length;i++)
		{
			if(isSafe(board, i, col))
			{
				board[i][col] = 1;
				//For All Solution
				solveNQUtil(board, col + 1);
				/* For One Solution
				 * if(solveNQUtil(board, col + 1))
					return true;*/
				board[i][col] = 0;
			}
		}
		return false;
	}
	
	public static void solveNQ()
	{
		int [][]board = {{0, 0, 0, 0},
	            		 {0, 0, 0, 0},
	            		 {0, 0, 0, 0},
	            		 {0, 0, 0, 0}
	        			};
		if(!solveNQUtil(board, 0))
			System.out.println("No Solution");
		else
			print(board);
	}

	public static void main(String[] args) {
		solveNQ();
	}
}
