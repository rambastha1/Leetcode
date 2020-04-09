package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
/* dp[i][j] denotes whether s[0....i-1] matches p[0.....j-1],

First, we need to initialize dp[i][0], i= [1,m]. All the dp[i][0] should be false because p has nothing in it.

Then, initialize dp[0][j], j = [1, n]. In this case, s has nothing, to get dp[0][j] = true, p must be '*', '**', '***',etc. 
Once p.charAt(j-1) != '*', all the dp[0][j] afterwards will be false.

Then start the typical DP loop.
 * 
 */
class Solution {
    public boolean isMatch(String s, String p) {
    	int m = s.length(), n = p.length();
    	char []S = s.toCharArray(), P = p.toCharArray();
    	
    	/* // 0(m*n) time and space
    	 * https://leetcode.com/problems/wildcard-matching/discuss/17812/My-java-DP-solution-using-2D-table
    	 * for(int i = 1;i <= m;i++) {
    		for(int j = 1;j <= n;j++) {
    			if(P[j-1] != '*') {
    				dp[i][j] = dp[i-1][j-1] && (S[i-1] == P[j-1] || P[j-1] == '?');
    			} else {
    				dp[i][j] = dp[i-1][j] || dp[i][j-1];
    			}
    		}
    	}*/
    	
    	// https://leetcode.com/problems/wildcard-matching/discuss/17810/Linear-runtime-and-constant-space-solution
    	// 0(MN) time constant space
    	int i = 0, j = 0, match = 0, starindex = -1;
    	while(i < m) {
    		if(j < n && (P[j] == '?' || P[j] == S[i])) {
    			i++;
    			j++;
    		} else if(j < n && P[j] == '*') {
    			starindex = j;
    			match = i;
    			j++;
    		} else if(starindex != -1) {
    			j = starindex +1;
    			match++;
    			i = match;
    		} else
    			return false;
    	}
    	while(j < n && P[j] == '*')
    		j++;
    	return j == n;
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "aab", p = "c*a*b";
		System.out.println(new Solution().isMatch(s, p));
	}
}
