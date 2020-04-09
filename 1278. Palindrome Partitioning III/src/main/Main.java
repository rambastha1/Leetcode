package main;
// https://leetcode.com/problems/palindrome-partitioning-iii/discuss/498677/Step-by-Step-solution-DP-(Java)
/*
 * There are a lot of these types of questions in DP Hard. If you learn to recognize the basic structure, 
 * you can come up with an answer fairly quickly.
Couple words that give away the DP nature of the problem

Palindrome
Minimum
If we break the question apart we get:

How many changes do I need to perform to make S.substring(i, j) to a palindrome.
How many ways can I split the string from i to j into groups and what are their costs.
What is the minimum combination of these groups.
 */
class Solution {
    public int palindromePartition(String s, int k) {
    	int n = s.length();
    	// number of changes required to change s.substring(i, j+1) into palindrome
    	int [][]topal = new int[n][n];
    	// reverse saves time
    	for(int i = n-1;i >= 0;i--) {
    		for(int j = i+1;j < n;j++) {
    			topal[i][j] = changes(s, i, j);
    		}
    	}
    	
    	// minimum number of changes required to partition into i and string ending at j
    	int [][]dp = new int[k+1][n];
    	for(int i = 0;i < n;i++)
    		dp[1][i] = topal[0][i];
    	
    	
    	for(int p = 2;p <= k;p++) {
    		for(int end = p-1;end < n;end++) {
    			int min = n;
    			for(int start = end-1;start >= 0;start--) {
    				min = Math.min(min, dp[p-1][start] + topal[start+1][end]);
    			}
    			dp[p][end] = min;
    		}
    	}
    	return dp[k][n-1];
    }
    
    private int changes(String S, int s, int e) {
    	int count = 0;
    	while(s < e) {
    		if(S.charAt(s++) != S.charAt(e--))
    			count++;
    	}
    	return count;
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "abc";
		int k = 2;
		System.out.println(new Solution().palindromePartition(s, k));
	}
}
