package main;

class Maze
{
	public boolean isSafe(int x, int y, int [][]soln, int [][]maze)
	{
		return (x >= 0 && x < soln.length && y >= 0 && y < soln.length && maze[x][y] != 0 &&
				soln[x][y] == 0);
	}
	
	public void print(int [][]soln)
	{
		for(int i = 0;i < soln.length;i++)
		{
			for(int j = 0;j < soln.length;j++)
				System.out.print(soln[i][j] + "\t");
			System.out.println();
		}
	}
	
	public void solveMaze(int [][]maze)
	{
		int [][] soln = new int[maze.length][maze.length];
		int []xmove = {0, -1, 0, 1};
		int []ymove = {1, 0, -1 ,0};
		
		if(!solveUtil(maze, soln, xmove, ymove, 0, 0, 1))
			System.out.println("No Solution");
		else
			print(soln);
	}
	
	public boolean solveUtil(int [][]maze, int [][]soln, int []xmove, int []ymove, int x, int y, int index)
	{
		if(x == maze.length - 1 && y == maze.length -1)
		{
			soln[x][y] = 1;
			return true;
		}
		int x_next, y_next;
		for(int i = 0;i < maze.length;i++)
		{
			x_next = x + xmove[i];
			y_next = y + ymove[i];
			
			if(isSafe(x_next, y_next, soln, maze))
			{
				soln[x][y] = 1;
				if(solveUtil(maze, soln, xmove, ymove, x_next, y_next, index + 1))
					return true;
				else
					soln[x_next][y_next] = 0;
			}
		}
		return false;
	}
}

public class Main 
{
	public static void main(String[] args) 
	{
		int maze[][] = {{1, 0, 0, 0},
	            		{1, 1, 0, 1},
	            		{0, 1, 0, 0},
	            		{1, 1, 1, 1}
	        		   };
		Maze m = new Maze();
		m.solveMaze(maze);
	}
}