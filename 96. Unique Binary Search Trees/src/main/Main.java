package main;

class Solution {
	
	// Catalan Number 0(N)
	public int numTrees(int n) {
    	if(n == 0 || n == 1)
    		return n;
    	int []dp = new int[n+1];
    	dp[0] = dp[1] = 1;
    	for(int i = 2;i <= n;i++) {
    		for(int j = 0;j < i;j++) {
    			dp[i] += dp[j] * dp[i-j-1];
    		}
    	}
    	return dp[n];
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 3;
		System.out.println(new Solution().numTrees(n));
	}
}