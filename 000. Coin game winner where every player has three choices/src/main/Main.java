package main;

// https://www.geeksforgeeks.org/coin-game-winner-every-player-three-choices/
/*
 * We can observe that A wins game for n coins only when it loses for coins n-1, n-x and n-y.
 * so that A can 1 pick 1 or x or y in this turn. After this no coins will be coins. A wins 
 */

class Solution {
	boolean findWinner(int n, int x, int y) {
		if(n <= 0)
			return false;
		boolean []dp = new boolean[n+1];
		dp[0] = false;
		dp[1] = true;
		
		// If A losses any of i-1 or i-x or i-y game then he will definitely win game i 
		for(int i = 2;i <= n;i++) {
			if(i-1>= 0 && !dp[i-1] || i-x>=0 && !dp[i-x] || i-y>=0 && !dp[i-y])
				dp[i] = true;
		}
		return dp[n];
	}
}

public class Main {
	public static void main(String[] args) {
		int n = 7;
		int x = 3, y = 4;
		System.out.println(new Solution().findWinner(n, x, y));
	}
}