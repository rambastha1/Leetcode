package main;

// https://leetcode.com/problems/minimum-cost-to-merge-stones/discuss/247567/JavaC%2B%2BPython-DP

class Solution {
	// Time 0(n^3/K) Space 0(N^2)
    public int mergeStones(int[] stones, int K) {
    	int n = stones.length;
    	// must be merged into 1 stone
    	if((n-1)%(K-1) > 0)
    		return -1;
    	
    	int []sum = new int[n+1];
    	for(int i = 1;i <= n;i++)
    		sum[i] = sum[i-1] + stones[i-1];
    	
    	// MCM technique
    	int [][]dp = new int[n][n];
    	for(int gap = K;gap <= n;gap++) {
    		for(int i = 0, j= gap-1;j < n;i++, j++) {
    			dp[i][j] = Integer.MAX_VALUE;
    		/* k increases with K-1 because for consecutive K merges into 1, then add next K-1
    		 * makes it K merges into 1
    		 */
    			for(int k = i;k < j;k += K-1) {
    				dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j]);
    			}
    			// merges into 1 thus dp[i][j] = sum of i to j
    			// this is what was missing in 1167 (not able to figure out)
    			if((j-i)%(K-1) == 0)
    				dp[i][j] += sum[j+1] - sum[i];
    		}
    	}
    	return dp[0][n-1];
    }
}

public class Main {
	public static void main(String[] args) {
		int []stones = {3,2,4,1};
		int K = 2;
		System.out.println(new Solution().mergeStones(stones, K));
	}
}
