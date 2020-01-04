package main;

import java.util.Arrays;

/* https://www.geeksforgeeks.org/count-distinct-subsequences/
 * problem 115
 * https://leetcode.com/problems/distinct-subsequences-ii/discuss/192030/Java-DP-O(N2)-time-greater-O(N)-time-greater-O(1)-space
 * https://www.geeksforgeeks.org/count-distinct-occurrences-as-a-subsequence/ for 115
 */
class Solution {
	
	// Time 0(N) and Space 0(1)
	// as dp[i] depends only on dp[i-1]
	public int distinctSubseqII(String S) {
		int n = S.length();
		// store count of each character directly in last
		long []count = new long[26];
    	int mod = (int)1e9+7;
    	
    	// Empty substring has only one subsequence
    	long sum = 1;
    	
    	for(int i = 1;i <= n;i++) {
    		char c = S.charAt(i-1);
    		long temp = sum;
    		/* a is always greater than b, however, a % mod can be smaller than b % mod. To prevent it going negative, 
    		 * we add mod. It does not affect the result as we do % mod in the end.
    		 */
    		sum = ((sum*2)%mod - count[c-'a']%mod + mod)%mod;
    		// why old sum?
    		count[c-'a'] = temp;
    	}
    	return (int)sum%mod - 1;
	}
	
	// Time 0(N) and Space 0(N)
    public int distinctSubseq1II(String S) {
    	int n = S.length();
    	long []dp = new long[n+1];
    	int []last = new int[26];
    	Arrays.fill(last, -1);
    	int mod = (int)1e9+7;
    	
    	// Empty substring has only one subsequence 
    	dp[0] = 1;
    	for(int i = 1;i <= n;i++) {
    		char c = S.charAt(i-1);
    		// this because there are n new sequences ending with current letter + already there dp[i-1] add this letter at end of it 
    		dp[i] = (2*dp[i-1])%mod;
    		/* Here c-'a' is index of previous occurrence of current character. We basically remove all 
    		 * counts ending with previous occurrence of current character.
    		 */
    		if(last[c-'a'] != -1)
    			// sum could become negative
    			dp[i] = (dp[i] - dp[last[c-'a']] + mod)%mod;
    		last[c-'a'] = i-1;
    	}
    	// non-empty strings are required -1 for ""
    	return (int)dp[n]%mod - 1;
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "aba";
		System.out.println(new Solution().distinctSubseqII(S));
		System.out.println(new Solution().distinctSubseq1II(S));
	}
}
