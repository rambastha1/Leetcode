package main;

// Problem 583. Delete Operation for Two Strings
// https://leetcode.com/problems/edit-distance/discuss/25849/Java-DP-solution-O(nm) 
// User Zzz_
class Solution {
	
	// Space 0(word2.length())
    public int minDistance(String word1, String word2) {
    	if(word1 == null || word2 == null)
    		return 0;
    	if(word1.length() == 0) return word2.length();
    	if(word2.length() == 0) return word1.length();
    	
    	int []dp = new int[word2.length()+1];
    	for(int j = 0;j <= word2.length();j++)
    		dp[j] = j;

    	for(int i = 1;i <= word1.length();i++) {
    		int prev = dp[0];
    		dp[0] = i;
    		for(int j = 1;j <= word2.length();j++) {
    			int temp = dp[j];
    			if(word1.charAt(i-1) == word2.charAt(j-1))
    				dp[j] = prev;
    			else
    				dp[j] = 1 + Math.min(dp[j], Math.min(dp[j-1], prev));
    			prev = temp;
    		}
    	}
    	return dp[word2.length()];
    }
}

public class Main {
	public static void main(String[] args) {
		String word1 = "horse", word2 = "ros";
		System.out.println(new Solution().minDistance(word1, word2));
	}
}
