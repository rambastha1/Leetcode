package main;

import java.util.Arrays;

class Solution {
	// can use 1D
    public int calculateMinimumHP(int[][] dungeon) {
    	int m = dungeon.length, n = dungeon[0].length;
    	// dp[i][j] is minimum health required to arrive at (i,j)
    	int [][]dp = new int[m+1][n+1];
    	for(int []d : dp)
    		Arrays.fill(d, Integer.MAX_VALUE);
    	dp[m][n-1] = dp[m-1][n] = 1;
    	for(int i = m-1;i >= 0;i--) {
    		for(int j = n-1;j>= 0;j--) {
    			/* this value will be negative for magic orb meaning if knight arrives even at health 1, its fine his health will increase
    			 * if this is negative knight -> demons->need to arrive at dungeons[i][j] + 1 -> +1 because even at health 0 -> knight dies  
    			 */
    			int need = Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j];
    			dp[i][j] = need <= 0?1:need;
    		}
    	}
    	return dp[0][0];
    }
}

public class Main {
	public static void main(String[] args) {
		int[][] dungeon = {{-2,-3,3},{-5,-10,1},{10,30,-5}};
		//int[][] dungeon = {{0,-3}};
		System.out.println(new Solution().calculateMinimumHP(dungeon));
	}
}
