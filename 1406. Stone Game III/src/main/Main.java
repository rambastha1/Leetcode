package main;
class Solution {
	// dp[i] is maximum amount one can take starting at index i first
    public String stoneGameIII(int[] stoneValue) {
    	int n = stoneValue.length;
    	int []suffix = new int[n+1], dp = new int[n+1];
    	for(int i = n-1;i >= 0;i--)
    		suffix[i] = suffix[i+1] + stoneValue[i];
    	// first player takes all
    	dp[n-1] = suffix[n-1] - dp[n];
    	for(int i = n-2;i >= 0;i--) {
    		// this player takes stone[i] other takes (total - max starting from i+1)
    		dp[i] = stoneValue[i] + suffix[i+1] - dp[i+1];
    		for(int j = i+1;j < i+3 && j < n;j++) {
    			dp[i] = Math.max(dp[i], suffix[i] - dp[j+1]);
    		}
    	}
    	// checking if this value is more than half of total
    	if(dp[0]*2 > suffix[0])
    		return "Alice";
    	else if(dp[0]*2 < suffix[0])
    		return "Bob";
    	return "Tie";
    }
}

public class Main {
	public static void main(String[] args) {
		int []values = {1,2,3,7};
		System.out.println(new Solution().stoneGameIII(values));
	}
}
