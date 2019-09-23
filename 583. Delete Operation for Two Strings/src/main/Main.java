package main;

class Solution {
	
	//Space 0(N)	
	public int minDistance(String word1, String word2) {
    	if(word1 == null || word2 == null)
    		return 0;
    	if(word1.length() == 0)
    		return word2.length();
    	if(word2.length() == 0) return word1.length();
    	int []dp = new int[word2.length()+1];
    	for(int j = 0;j <= word2.length();j++)
    		dp[j] = j;
    	
    	for(int i = 1;i <= word1.length();i++) {
    		// the value in last loop, in 2d solution, it means dp[i-1][j-1]
    		int prev = dp[0];
    		// initial state as in 2D array
    		dp[0] = i;
    		for(int j = 1;j <= word2.length();j++) {
    			// dp[j] will be replaced, which is the state of dp[i-1][j-1] in next loop
    			int temp = dp[j];
    			if(word1.charAt(i-1) == word2.charAt(j-1))
    				dp[j] = prev;
    			else
    				dp[j] = 1 + Math.min(dp[j], dp[j-1]);
    			// It maintains prev as dp[i-1][j-1] in next loop
    			prev = temp;
    		}
    	}
    	return dp[word2.length()];
	}
	
	
	//Space 0(M*N)
    public int minDistance1(String word1, String word2) {
    	if(word1 == null || word2 == null)
    		return 0;
    	if(word1.length() == 0)
    		return word2.length();
    	if(word2.length() == 0) return word1.length();
    	int [][]dp = new int[word1.length()+1][word2.length()+1];
    	for(int i = 0;i < dp.length;i++)
    		dp[i][0] = i;
    	for(int j = 0;j < dp[0].length;j++)
    		dp[0][j] = j;
    	
    	for(int i = 1;i <= word1.length();i++) {
    		for(int j = 1;j <= word2.length();j++) {
    			if(word1.charAt(i-1) == word2.charAt(j-1))
    				dp[i][j] = dp[i-1][j-1];
    			else
    				dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
    		}
    	}
    	return dp[word1.length()][word2.length()];
    }
}

public class Main {
	public static void main(String[] args) {
		//String word1 = "sea", word2 = "eat";
		String word1 = "a", word2 = "b";
		//String word1 = "", word2 = "a";
		System.out.println(new Solution().minDistance(word1, word2));
	}
}
