package main;

class Solution {
	int max = 0, curr = 0;
    public int maxAreaOfIsland(int[][] grid) {
    	boolean [][]visited = new boolean[grid.length][grid[0].length];
    	for(int i = 0;i < grid.length;i++) {
    		for(int j = 0;j < grid[0].length;j++) {
    			if(grid[i][j] == 1 && !visited[i][j]) {
    				curr = 0;
    				dfs(grid, visited, i, j);
    			}
    		}
    	}
    	return max;
    }
    
    void dfs(int [][]grid, boolean [][]visited, int i, int j) {
    	visited[i][j] = true;
    	curr++;
    	max = Math.max(max, curr);
    	int []x_path = {0,-1,0,1};
    	int []y_path = {-1,0,1,0};
    	for(int m = 0;m < 4;m++) {
    		int x = i + x_path[m];
    		int y = j + y_path[m];
    		if(issafe(grid, x, y) && grid[x][y] == 1 && !visited[x][y])
    			dfs(grid, visited, x, y);
    	}
    }
    
    boolean issafe(int[][]grid, int x, int y) {
    	return x >= 0 && x <= grid.length-1 && y >= 0 && y <= grid[0].length-1;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
		                {0,0,0,0,0,0,0,1,1,1,0,0,0},
		                {0,1,1,0,1,0,0,0,0,0,0,0,0},
		                {0,1,0,0,1,1,0,0,1,0,1,0,0},
		                {0,1,0,0,1,1,0,0,1,1,1,0,0},
		                {0,0,0,0,0,0,0,0,0,0,1,0,0},
		                {0,0,0,0,0,0,0,1,1,1,0,0,0},
		                {0,0,0,0,0,0,0,1,1,0,0,0,0}};
		System.out.println(new Solution().maxAreaOfIsland(grid));
	}
}