package main;

import java.util.Arrays;

/* https://www.geeksforgeeks.org/count-distinct-occurrences-as-a-subsequence/
 * "When you see string problem that is about subsequence or matching, dynamic programming method should come to mind naturally. "
 */
class Solution {
    
	// Time and space 0(m*n)
	public int numDistinct(String s, String t) {
    	int m = t.length(), n = s.length();
    	if(m == 0)
    		return 1;
    	if(n == 0 || n < m)
    		return 0;
    	
    	int [][]dp = new int[m+1][n+1];
    	for(int i = 0;i <= m;i++)
    		dp[i][0] = 0;
    	Arrays.fill(dp[0], 1);
    	for(int i = 1;i <= m;i++) {
    		for(int j = 1;j <= n;j++) {
    			if(s.charAt(j-1) != t.charAt(i-1)) {
    				dp[i][j] = dp[i][j-1];
    			} else {
    				dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
    			}
    		}
    	}
    	return dp[m][n];
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "banana", t = "ban";
		System.out.println(new Solution().numDistinct(s, t));
		System.out.println(new Solution().numDistinct(s, t));
	}
}
