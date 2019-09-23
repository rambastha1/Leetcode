package main;

class Solution {
	
    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
    	if(grid == null || grid.length == 0 || grid[0].length == 0)
    		return grid;
    	boolean [][]visited = new boolean[grid.length][grid[0].length];
    	dfs(grid, r0, c0, r0, c0, color, visited, grid[r0][c0]);
    	print(grid);
    	return grid;
    }
    
    void dfs(int [][]grid, int r, int c, int x, int y, int color, boolean [][]visited, int val) {
    	if(!issafe(grid, x, y, visited))
    		return;
    	
    	visited[x][y] = true;
    	if(x == 0 || x== grid.length -1 || y == 0 || y == grid[0].length-1) {
    		grid[x][y] = color;
    	}
    	
    	int [][]dirs = {{0,-1}, {-1,0}, {0,1}, {1,0}};
    	for(int i = 0;i < 4;i ++) {
    		int X = x + dirs[i][0];
    		int Y = y + dirs[i][1];
    		if(issafe(grid, X, Y, visited)) {
    			if(grid[X][Y] == val)
    				dfs(grid, r, c, X, Y, color, visited, val);
    			else
    				grid[x][y] = color;
     		}
    	}
    }
    
    public boolean issafe(int [][]grid, int x, int y, boolean [][]visited) {
    	return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && !visited[x][y];
    }
    
    void print(int [][]grid) {
    	for(int i = 0;i < grid.length;i++) {
    		for(int j = 0;j < grid[0].length;j++)
    			System.out.print(grid[i][j] + " ");
    		System.out.println();
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]grid = {{1,1},{1,2}};
		int r0 = 0, c0 = 0, color = 3;
		
		/*int [][]grid = {{1,2,2},{2,3,2}};
		int r0 = 0, c0 = 1, color = 3;*/
		
		/*int [][]grid = {{1,1,1},{1,1,1},{1,1,1}};
		int r0 = 1, c0 = 1, color = 2;*/

		/*int [][]grid = {{1,2,1,2,1,2},{2,2,2,2,1,2},{1,2,2,2,1,2}};
		int r0 = 1, c0 = 3, color = 1;*/
		
		new Solution().colorBorder(grid, r0, c0, color);
	}
}
