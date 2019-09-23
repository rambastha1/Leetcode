package main;


class Solution {
	
	// Space 0(N)
	public int longestPalindromeSubseq(String s) {
		if(s == null || s.length() == 0)
    		return 0;
    	int n = s.length();
    	int []dp = new int[n];
    	
    	for(int i = n-1;i >= 0;i--) {
    		dp[i] = 1;
    		// in 2 D array pre is dp[i+1][j-1]
    		// previous case value that needs to be stored
    		int pre = 0;
    		for(int j = i+1;j < n;j++) {
    			int temp = dp[j];
    			if(s.charAt(i) == s.charAt(j))
    				dp[j] = pre + 2;
    			else
    				dp[j] = Math.max(dp[j-1], dp[j]);
    			pre = temp;
    		}    		
    	}
    	return dp[n-1];
	}
	
	// Space 0(N^2)
    /*public int longestPalindromeSubseq(String s) {
    	if(s == null || s.length() == 0)
    		return 0;
    	int n = s.length();
    	int [][]dp = new int[n][n];
    	//Interesting start from bottom row Matrix Chain Multiplication
    	for(int i = n-1;i >= 0;i--) {
    		dp[i][i] = 1;
    		for(int j = i+1;j < n;j++) {
    			if(s.charAt(i) == s.charAt(j))
    				dp[i][j] = dp[i+1][j-1] + 2;
    			else
    				dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
    		}
    	}
    	return dp[0][n-1];
    }*/
}

public class Main {
	public static void main(String[] args) {
		//String s = "bbbab";
		String s = "abcabcabcabc";
		//String s = "cbbd";
		System.out.println(new Solution().longestPalindromeSubseq(s));
	}
}