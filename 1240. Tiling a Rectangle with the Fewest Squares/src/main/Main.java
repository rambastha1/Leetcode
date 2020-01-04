package main;

class Solution {
    public int tilingRectangle(int n, int m) {
    	int [][]dp = new int[n+1][m+1];
    	for(int i = 1;i <= m;i++)
    		dp[1][i] = i;
    	
    	for(int i = 2;i <= n;i++) {
    		for(int j = 1;j <= m;j++) {
    			if(i == j) {
    				dp[i][j] = 1;
    			} else if(i > j) {
    				if(i%j == 0) {
    					dp[i][j] = dp[j][j] *(i/j);
    				} else 
    					dp[i][j] = dp[i-1][j] + dp[i%j][j];
    			} else {
    				if(j%i == 0) {
    					dp[i][j] = dp[i][i] * (j/i);
    				} else {
    					dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + dp[i][j%i];
    				}
    			}
    		}
    	}
    	return dp[n][m];
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 5, m = 8;
		System.out.println(new Solution().tilingRectangle(n, m));
	}
}
