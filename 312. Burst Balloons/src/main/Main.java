package main;

// https://leetcode.com/problems/burst-balloons/discuss/76229/For-anyone-that-is-still-confused-after-reading-all-kinds-of-explanations...

class Solution {
	// Time 0(N^3) and Space 0(n^2) 
    public int maxCoins(int[] nums) {
    	int n = nums.length;
    	if(n == 0)
    		return 0;
    	
        int [][]dp = new int[n][n];
        for(int gap = 0;gap <= n;gap++) {
        	for(int i = 0, j = gap;j < n;i++,j++) {
    			for(int k = i;k <= j;k++) {
    				/* why nums[i-1] and nums[j+1]?why not nums[k-1] and nums[k+1]?
    				 * k is last index of balloon to burst
    				 */
    				int left = (i<=0)?1:nums[i-1];
    				int right = (j>=n-1)?1:nums[j+1];
    				int val = (left * nums[k] * right) + (k==i?0:dp[i][k-1]) + (k==j?0:dp[k+1][j]);
    				dp[i][j] = Math.max(dp[i][j], val);
    			}
        	}
        }
        return dp[0][n-1];
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {3,1,5,8};
		System.out.println(new Solution().maxCoins(nums));
	}
}
