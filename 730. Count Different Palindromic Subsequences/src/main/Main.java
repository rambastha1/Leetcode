package main;

// https://leetcode.com/problems/count-different-palindromic-subsequences/discuss/112757/Java-solution-using-simple-DP.-O(n2)-run-time-and-O(n2)-space
// https://leetcode.com/problems/count-different-palindromic-subsequences/discuss/109507/Java-96ms-DP-Solution-with-Detailed-Explanation
class Solution {
	// Time 0(n^3) Space 0(n^2)
    public int countPalindromicSubsequences(String S) {
    	long mod = 1000000007;
    	int n = S.length();
    	// dp[i][j] number of palindrome subsequences in [i,j]
    	int [][]dp = new int[n][n];
    	
    	for(int len = 0;len < n;len++) {
    		for(int i = 0, j = len;j < n;i++,j++) {
    			if(len == 0) {
    				dp[i][j] = 1;
    			} else {
    				if(S.charAt(i) == S.charAt(j)) {
    					// inner string is counted twice once alone and once with S[i]innerS[j]
    					dp[i][j] = 2 * dp[i+1][j-1];
	    				int left = i+1, right = j-1;
	    				while(left <= right && S.charAt(left) != S.charAt(i))
	    					left++;
	    				while(left <= right && S.charAt(right) != S.charAt(j))
	    					right--;
				/* consider the string from i to j is "a...a" "a...a"... where there is no character 'a' inside the 
				leftmost and rightmost 'a'
				eg:  "aba" while i = 0 and j = 2:  dp[1][1] = 1 records the palindrome{"b"}, 
				 the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"b"}, 
				 and additional time as {"aba"}. The reason why 2 counted is that we also count {"a", "aa"}. 
				 So totally dp[i][j] record the palindrome: {"a", "b", "aa", "aba"}. 
				 */ 
	    				if(left > right) {
	    					dp[i][j] += 2;
	    				} else if(left == right) {
				/* consider the string from i to j is "a...a...a" where there is only one character 'a' 
				 * inside the leftmost and rightmost 'a'
				 * eg:  "aaa" while i = 0 and j = 2: the dp[i + 1][j - 1] records the palindrome {"a"}.  
	             the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"a"}, 
	             and additional time as {"aaa"}. the reason why 1 counted is that 
	             we also count {"aa"} that the first 'a' come from index i and the second come from index j. 
	             So totally dp[i][j] records {"a", "aa", "aaa"}
	            */
	    					dp[i][j] ++;
	    				} else {
	/* consider the string from i to j is "a...a...a... a" where there are at least two character 'a' 
	 * close to leftmost and rightmost 'a'
	 * eg: "aacaa" while i = 0 and j = 4: the dp[i + 1][j - 1] records the palindrome {"a",  "c", "aa", "aca"}. 
      the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"a",  "c", "aa", "aca"}, 
      and additional time as {"aaa",  "aca", "aaaa", "aacaa"}.  Now there is duplicate :  {"aca"}, 
      which is removed by deduce dp[low + 1][high - 1]. So totally dp[i][j] record {"a",  "c", "aa", "aca", "aaa", "aaaa", "aacaa"}
      */
	    					dp[i][j] -= dp[left+1][right-1];
	    				}
	    			} else {
	    				dp[i][j] = dp[i][j-1] + dp[i+1][j] - dp[i+1][j-1];
	    			}
    			}
    			dp[i][j] = (int)((dp[i][j] + mod)%mod);
    		}
    	}
    	return dp[0][n-1];
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "aaaa";
		System.out.println(new Solution().countPalindromicSubsequences(S));
	}
}
