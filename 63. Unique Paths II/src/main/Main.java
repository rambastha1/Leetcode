package main;

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    	if(obstacleGrid == null || obstacleGrid.length == 0 || 
    			obstacleGrid[0].length == 0 || obstacleGrid[0][0] == 1)
    		return 0;

    	int m = obstacleGrid.length, n = obstacleGrid[0].length;
    	int []dp = new int[n];
    	dp[0] = 1;
    	for(int i = 0; i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			if(obstacleGrid[i][j] == 1)
    				dp[j] = 0;
    			else if(j > 0)
    				dp[j] += dp[j-1];
    		}
    	}
    	return dp[n-1];
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]obstacleGrid = {{0,0,0}, {0,1,0}, {0,0,0}};
		//int [][]obstacleGrid = {{0,0}, {1,1}, {0,0}};
		System.out.println(new Solution().uniquePathsWithObstacles(obstacleGrid));
	}
}
