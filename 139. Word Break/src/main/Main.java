package main;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class Solution {
	
	/*
	 * DP approach
	 * O(N * maxlen) solution
	 * Here maxlen is maximum length of strings in dict
	 * we take maxlen because when we iterate j from i-1 to 0, we only need to take
	 * those string whose length is less than maxlen or it won't be in dictionary
	 * Thus it reduces number of iterations.
	 */
	
	public boolean wordBreak(String s, List<String> wordDict) {
		int n = s.length();
        if (n == 0) return wordDict.size() == 0;
        Set<String> dict = new HashSet<>();
        int maxLen = 0;
        for (String word : wordDict) {
            dict.add(word);
            maxLen = Math.max(maxLen, word.length());
        }
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        
        /* dp[i] says whether substring from last true to this 'i' exists in dictionary
         * thus dp[0] is true because there is no substring before index 0
         * i runs equal to n because in s.substring(j, i) it ""excludes"" i
         * thus in order to take last character of input string into consideration, 
         * it runs n times
         * the input string may or may not have that much length
         * last entry of dp array tells whether whole string in parts or full is present in
         * the dictionary 
         */
        
        for (int i = 1; i <= n; i++) {
        	//this finds substring which exists in dictionary
            for (int j = i - 1; j >= 0 && i - j <= maxLen; j--) {
            	/* dp[j] is checked because if s.substring(0,j) is not in dict
            	 * it doesn't make sense to check for s.substring(j, i) 
            	 */
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        //print(dp);
        return dp[n];
	}
	
    /*
	 * recursive Approach
	 * 0(N^2) Time Complexity
	 * 
	public boolean wordBreak(String s, List<String> wordDict) {
    	if(s.length() == 0)
    		return true;
    	for(int i = 1;i <= s.length();i++) {
    		if(contains(wordDict, s.substring(0, i)) && wordBreak(s.substring(i), wordDict))
    			return true;
    	}
    	return false;
    }
    
    public boolean contains(List<String> wordDict, String word) {
    	for(String str : wordDict)
    		if(str.compareTo(word) == 0)
    			return true;
    	return false;
    }*/
	
	
	void print(boolean []dp) {
		for(boolean b : dp)
			System.out.print(b + " ");
		System.out.println();
	}
    
}

public class Main {

	public static void main(String[] args) {
		/*List<String> wordDict = Arrays.asList("mobile","samsung","sam","sung", "man","mango","icecream",
				"and", "go", "i","like","ice","cream");*/
		List<String> wordDict = Arrays.asList("i", "am", "rakesh", "ambastha");
		String s = "rakeshami";
		System.out.println(new Solution().wordBreak(s, wordDict));	
	}
}