package main;

import java.util.Arrays;

/*
dp[i][j] stands for in i th sum, the max non-overlap sum we can have from 0 to j
or the the maximum sum in ith using k overlap from 0-j
pos[i][j] stands for in i th sum, the first starting index for that sum.
https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/discuss/108230/Clean-Java-DP-O(n)-Solution.-Easy-extend-to-Sum-of-K-Non-Overlapping-SubArrays.
idea is to fix the middle sum and find other two
*/
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
    	int []res = new int[3];
    	if(nums == null || k <= 0 || nums.length < 3*k)
    		return res;
    	int n = nums.length;
    	int []prefix = new int[n+1];
    	for(int i = 1;i <= n;i++)
    		prefix[i] = prefix[i-1] + nums[i-1];
    	
    	int [][]dp = new int[4][n+1];
    	int [][]pos = new int[4][n+1];
    	
    	/* we are looking for 3 non-overlapping subarray each of size k with max sum
    	 * j = k*i will give 1st array i.e start of 2nd array
    	 */
    	for(int i = 1;i <= 3;i++) {
    		for(int j = k*i;j <= n;j++) {
    			// sum of k+1 to j + previous max overlap possible
    			int currsum = prefix[j] - prefix[j-k] + dp[i-1][j-k];
    			if(currsum > dp[i][j-1]) {
    				dp[i][j] = currsum;
    				pos[i][j] = j-k;
    			} else {
    				dp[i][j] = dp[i][j-1];
    				pos[i][j] = pos[i][j-1];
    			}
    		}
    	}
    	// end index of previous should be start of curr - 1
    	int index = n;
    	for(int i = 2;i >= 0;i--) {
    		res[i] = pos[i+1][index];
    		index = res[i];
    	}
    	
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,2,1,2,6,7,5,1};
		int k = 2;
		System.out.println(Arrays.toString(new Solution().maxSumOfThreeSubarrays(nums, k)));
	}
}
