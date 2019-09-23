package main;

// https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/discuss/165330/Java-beat-98-DP-%2B-2Sum
// dp[r][i]: length of longest fibonacci sequence end with A[r](starting), A[i](end point)
class Solution {
    public int lenLongestFibSubseq(int[] A) {
    	if(A == null || A.length < 3)
    		return 0;
    	int n = A.length;
    	int [][]dp = new int[n][n];
    	for(int i = 0;i < n;i++)
    		for(int j = 0;j < n;j++)
    			dp[i][j] = 2;
    	
    	int maxlen = Integer.MIN_VALUE;
    	for(int i = 2;i < n;i++) {
    		int l = 0, r = i-1;
    		while(l < r) {
    			int sum = A[l] + A[r];
    			if(sum == A[i]) {
    				dp[r][i] = dp[l][r] + 1;
    				maxlen = Math.max(maxlen, dp[r][i]);
    				l++;
    				r--;    				
    			} else if(sum < A[i])
    				l++;
    			else 
    				r--;
    		}
    	}
    	return maxlen>2?maxlen:0;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {1,3,7,11,12,14,18};
		System.out.println(new Solution().lenLongestFibSubseq(A));
	}
}