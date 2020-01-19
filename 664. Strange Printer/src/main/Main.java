package main;

class Solution {
	// 0(N^3) time and 0(N^2) space
	// https://leetcode.com/problems/strange-printer/discuss/152758/Clear-Logical-Thinking-with-Code
    public int strangePrinter(String s) {
    	int n = s.length();
    	int [][]dp = new int[n][n];
    	// dp[i][j] -> minimum steps required to build string [i,j] (closed interval)
    	for(int i = 0;i < n;i++) {
    		dp[i][i] = 1;
    	}
    	for(int gap = 1;gap < n;gap++) {
    		for(int i = 0, j = gap;j < n;i++, j++) {
    			if(i + 1 == j) {
    				dp[i][j] = s.charAt(i) == s.charAt(j)?1:2;
    			} else {
    				dp[i][j] = Integer.MAX_VALUE;
    				for(int k = i;k < j;k++) {
    					// two seperate halves and minimum combine sum
    					int val = dp[i][k] + dp[k+1][j];
    					dp[i][j] = Math.min(dp[i][j], val);
    				}
    				/*
    				 * Consider string "ababa" i = 0, j = 4, k = 2 state[i][j] would be 4(state[0][2] + state[3][4]). 
    				 * The last and the first character are counted as two different steps. But since s[0] == s[4] = 'a' , 
    				 * both can be created in single steps - print [0,4] as 'a' in one step. Thus we need to subtract 1.
    				 */
    				if(s.charAt(i) == s.charAt(j))
    					dp[i][j]--;
    			}
    		}
    	}
    	return dp[0][n-1];
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "aba";
		System.out.println(new Solution().strangePrinter(s));
	}
}
