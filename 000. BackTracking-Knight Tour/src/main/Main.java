package main;

// https://www.geeksforgeeks.org/the-knights-tour-problem-backtracking-1/
class Knight
{
	private int N = 8;
	
	public boolean isSafe(int x, int y, int [][]soln)
	{
		return (x >= 0 && x < 8 && y >= 0 && y < 8 && soln[x][y] == -1);
	}
	
	public void print(int [][]soln)
	{
		for(int i = 0;i < N;i++)
		{
			for(int j = 0;j < N;j++)
				System.out.print(soln[i][j] + "\t");
			System.out.println();
		}
	}
	
	public boolean knight()
	{
		int [][]soln = new int[N][N];
		for(int i = 0;i < N;i++)
			for(int j = 0;j < N;j++)
				soln[i][j] = -1;
		
		int []xmove = {2, 1, -1, -2, -2, -1, 1, 2};
		int []ymove = {1, 2, 2, 1, -1, -2, -2, -1};
		
		soln[0][0] = 0;
		if(!knightUtil(0, 0, 1, xmove, ymove, soln))
			return false;
		else
			print(soln);
		return true;
	}
	
	public boolean knightUtil(int x, int y, int index, int []xmove, int []ymove, int [][]soln)
	{
		if(index == N * N)
			return true;
		
		int x_next, y_next;
		for(int k = 0;k < this.N;k++)
		{
			x_next = x + xmove[k];
			y_next = y + ymove[k];
			//System.out.println("x:" + x_next + "\ty:" + y_next + "\t" + isSafe(x_next, y_next, soln));
			if(isSafe(x_next, y_next, soln))
			{
				soln[x_next][y_next] = index;
				if(knightUtil(x_next, y_next, index + 1, xmove, ymove, soln))
					return true;
				else
					soln[x_next][y_next] = -1;					
			}
		}
		return false;
	}
}

public class Main 
{	
	public static void main(String[] args) 
	{
		Knight knight = new Knight();
		knight.knight();
	}
}
