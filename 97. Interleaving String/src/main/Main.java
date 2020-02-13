package main;
// https://leetcode.com/articles/interleaving-strings/
class Solution {
	// time and space 0(n)
	public boolean isInterleave(String s1, String s2, String s3) {
		int m = s1.length(), n = s2.length();
		if(s3.length() != m + n)
			return false;
		boolean []dp = new boolean[n+1];
		for(int i = 0;i <= m;i++) {
			for(int j = 0;j <= n;j++) {
				if(i == 0 && j == 0)
					dp[j] = true;
				else if(j == 0)
					dp[j] = dp[j] && s1.charAt(i-1) == s3.charAt(i+j-1);
				else if(i == 0)
					dp[j] = dp[j-1] && s2.charAt(j-1) == s3.charAt(i+j-1);
				else {
					dp[j] = (dp[j] && s1.charAt(i-1) == s3.charAt(i+j-1)) || (dp[j-1] && s2.charAt(j-1) == s3.charAt(i+j-1)); 
				}
			}
		}
		return dp[n];
	}
	
	
	// time and space 0(m*n)
	public boolean isInterleave1(String s1, String s2, String s3) {
		int m = s1.length(), n = s2.length();
		if(s3.length() != m + n)
			return false;
		boolean [][]dp = new boolean[m+1][n+1];
		
		for(int i = 0;i <= m;i++) {
			for(int j = 0;j <= n;j++) {
				if(i == 0 && j == 0)
					dp[i][j] = true;
				else if(j == 0)
					dp[i][j] = dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1);
				else if(i == 0)
					dp[i][j] = dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1);
				else {
					dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1)) || (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1)); 
				}
			}
		}
		return dp[m][n];
    }
}

public class Main {
	public static void main(String[] args) {
		String s1 = "aa", s2 = "ab", s3 = "aaba";
		System.out.println(new Solution().isInterleave(s1, s2, s3));
		System.out.println(new Solution().isInterleave1(s1, s2, s3));
	}
}
