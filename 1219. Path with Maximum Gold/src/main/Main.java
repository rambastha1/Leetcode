package main;

class Solution {
    int [][]dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    public int getMaximumGold(int[][] grid) {
    	int m = grid.length, n = grid[0].length;
    	int ans = Integer.MIN_VALUE;
    	boolean [][]visited = new boolean[m][n];
    	
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
                if(grid[i][j] == 0)
                    continue;
    			ans = Math.max(ans, dfs(grid, i, j, visited));
    		}
    	}
    	return ans;
    }
    
    private int dfs(int [][]grid, int x,int y, boolean [][]visited) {
    	if(!issafe(grid, x, y, visited))
    		return 0;
    	visited[x][y] = true;
    	int res = grid[x][y]; 
    	for(int i = 0;i < 4;i++) {
    		int X = x + dir[i][0];
    		int Y = y + dir[i][1];
    		if(!issafe(grid, X, Y, visited))
    			continue;
    		res = Math.max(res, grid[x][y] + dfs(grid, X, Y, visited));
    	}
    	visited[x][y] = false;
    	return res; 
    }
    
    private boolean issafe(int [][]grid, int x,int y, boolean [][]visited) {
    	return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] != 0 && !visited[x][y]; 
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]grid = {{0,6,0},{5,8,7},{0,9,0}};
		System.out.println(new Solution().getMaximumGold(grid));
	}
}
