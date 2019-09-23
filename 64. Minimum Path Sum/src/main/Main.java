package main;

class Solution {
	
	//Constant Space
	public int minPathSum(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		for(int i = 1;i < m;i++)
			grid[i][0] = grid[i-1][0] + grid[i][0];
		for(int j = 1;j < n;j++)
			grid[0][j] = grid[0][j-1] + grid[0][j];
		
		for(int i = 1;i < m;i++)
			for(int j = 1;j < n;j++)
				grid[i][j] = grid[i][j] + Math.min(grid[i-1][j], grid[i][j-1]);
		
		return grid[m-1][n-1];
	}
	
	/*public int minPathSum(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int [][]dp = new int[m][n];
		dp[0][0] = grid[0][0];
		for(int i = 1;i < m;i++)
			dp[i][0] = dp[i-1][0] + grid[i][0];
		for(int j = 1;j < n;j++) {
			dp[0][j] = dp[0][j-1] + grid[0][j];
		}
		
		for(int i = 1;i < m;i++) {
			for(int j = 1;j < n;j++) {
				dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
			}
		} 
		return dp[m-1][n-1];
	}*/
	
	//DFS Working but TLE
	/*int ans = Integer.MAX_VALUE;
    public int minPathSum(int[][] grid) {
    	if(grid == null || grid.length == 0)
    		return 0;
    	dfs(grid, 0, 0, 0);
    	return ans;
    }
    
    int dfs(int [][]grid, int x, int y, int sum) {
    	if(!issafe(grid, x, y))
    		return 0;
    	
    	sum += grid[x][y];
    	if(x == grid.length-1 && y == grid[0].length-1) 
    		ans = Math.min(ans, sum);
    	int a = dfs(grid, x, y+1, sum);
    	int b = dfs(grid, x+1, y, sum);
    	return Math.min(sum+a, sum+b);
    }
    
    boolean issafe(int [][]grid, int x, int y) {
    	return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }*/
}

public class Main {
	public static void main(String[] args) {
		int [][]grid = {{1,3,1}, {1,5,1}, {4,2,1}};
		System.out.println(new Solution().minPathSum(grid));
	}
}