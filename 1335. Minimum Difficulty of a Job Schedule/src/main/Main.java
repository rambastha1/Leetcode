package main;
// https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/discuss/490297/Java-Bottom-Up-DP
class Solution {
	public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if(n < d)
    		return -1;
        // minimum difficulty to do 0-j jobs in i days
        int [][]dp = new int[d][n]; 
        dp[0][0] = jobDifficulty[0];
        for(int i = 1;i < n;i++)
        	dp[0][i] = Math.max(dp[0][i-1], jobDifficulty[i]);
        
        for(int i = 1;i < d;i++) {
        	// j from i because 0-j jobs cannot be done in < j days
        	for(int j = i;j < n;j++) {
        		int localmax = jobDifficulty[j];
        		dp[i][j] = Integer.MAX_VALUE;
        		// taking maximum and making it a part of i days job difficulties 
        		for(int k = j;k >= i;k--) {
        			localmax = Math.max(localmax, jobDifficulty[k]);
        			dp[i][j] = Math.min(dp[i][j], dp[i-1][k-1] + localmax);
        		}
        	}
        }
    	return dp[d-1][n-1];
    }
}

public class Main {
	public static void main(String[] args) {
		int []jobDifficulty = {7,1,7,1,7,1};
		int d = 3;
		System.out.println(new Solution().minDifficulty(jobDifficulty, d));
	}
}
