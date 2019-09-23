package main;

class Solution {
	int count = 0;
    public int uniquePathsIII(int[][] grid) {
    	if(grid == null || grid.length == 0 || grid[0].length == 0)
    		return 0;
    	int []start = null, end = null;
    	int m = grid.length, n = grid[0].length, count = 0;
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			if(grid[i][j] == 0)
    				continue;
    			if(grid[i][j] == 1) {
    				start = new int[2];
    				start[0] = i;
    				start[1] = j;
    			} else if(grid[i][j] == 2) {
    				end = new int[2];
    				end[0] = i;
    				end[1] = j;
    			} else 
    				count++;
    		}
    	}
    	
    	if(start == null || end == null)
    		return 0;
    	
    	boolean [][]visited = new boolean[m][n];
    	int [][]dirs = {{0,-1}, {-1,0}, {0,1}, {1,0}};
    	dfs(grid, end, start[0], start[1], visited, dirs, count);
    	return this.count;
    }
    
    private void dfs(int [][]grid, int []end, int x, int y, boolean [][]visited, int [][]dirs, int count) {
    	if(!issafe(grid, x, y, visited))
    		return;
    	
    	if(count == (grid.length*grid[0].length)-1) {
    		if(x == end[0] && y == end[1])
    			this.count++;
    		return;
    	}
    	
    	count++;
    	visited[x][y] = true;
    	for(int i = 0;i < 4;i++) {
    		int X = x + dirs[i][0];
    		int Y = y + dirs[i][1];
    		if(issafe(grid, X, Y, visited))
    			dfs(grid, end, X, Y, visited, dirs, count);
    	}
    	
    	count--;
    	visited[x][y] = false;
    }
    
    private boolean issafe(int [][]grid, int x, int y, boolean [][]visited) {
    	return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && !visited[x][y] && grid[x][y] != -1;
    }
}

public class Main {
	public static void main(String[] args) {
		//int [][]grid = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
		//int [][]grid = {{1,0,0,0},{0,0,0,0},{0,0,0,2}};
		int [][]grid = {{0,1},{2,0}};
		System.out.println(new Solution().uniquePathsIII(grid));
	}
}
