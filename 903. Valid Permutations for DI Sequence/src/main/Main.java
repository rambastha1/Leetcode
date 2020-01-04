package main;

import java.util.Arrays;

/*
 *  https://leetcode.com/problems/valid-permutations-for-di-sequence/discuss/168278/C%2B%2BJavaPython-DP-Solution-O(N2)
 *  dp[i][j] -> number of permutations of S.substring(0,i+1) with (i+1) digit being j smallest of rest
 *  for 'D' -> find higher j ones -> suffix sum
 *  for 'I' -> find lower j ones -> prefix sum
 *  for eg. 0,1,2,3,4
 *  4 is 5th smallest 0 -> 1st smallest
 */


class Solution {
	
	// 1D solution
	public int numPermsDISequence(String S) {
		int n = S.length();
    	int mod = (int)1e9 + 7;
    	int []dp = new int[n+1];
    	Arrays.fill(dp, 1);
    	
    	for(int i = 0;i < n;i++) {
    		int []temp = new int[n+1];
    		if(S.charAt(i) == 'I') {
    			// amazing use of curr to calculate prefix sum
    			for(int j = 0, curr = 0;j < n-i;j++) {
    				temp[j] = curr = (curr + dp[j])%mod;
    			}
    		} else {
    			// amazing use of curr to calculate suffix sum
    			for(int j = n-i-1, curr = 0;j >= 0;j--) {
    				temp[j] = curr = (curr + dp[j+1])%mod;
    			}
    		}
    		dp = temp;
    	}
    	return dp[0];
	}
	
    public int numPermsDISequence1(String S) {
    	int n = S.length();
    	int mod = (int)1e9 + 7;
    	int [][]dp = new int[n+1][n+1];
    	Arrays.fill(dp[0], 1);
    	
    	for(int i = 0;i < n;i++) {
    		if(S.charAt(i) == 'I') {
    			// amazing use of curr to calculate prefix sum
    			for(int j = 0, curr = 0;j < n-i;j++) {
    				dp[i+1][j] = curr = (curr + dp[i][j])%mod;
    			}
    		} else {
    			// amazing use of curr to calculate suffix sum
    			for(int j = n-i-1, curr = 0;j >= 0;j--) {
    				dp[i+1][j] = curr = (curr + dp[i][j+1])%mod;
    			}
    		}
    	}
    	return dp[n][0];
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "DID";
		System.out.println(new Solution().numPermsDISequence(S));
		System.out.println(new Solution().numPermsDISequence1(S));
	}
}
