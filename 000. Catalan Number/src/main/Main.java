package main;

/*
 * C(i+1) = Summation of C(i) * C(n-i)
 */

class Solution {
	public int catalan(int n) {
		if(n == 0 || n == 1)
			return 1;
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
		//int n = 3;
		for(int i = 0;i <= 10;i++)
			System.out.print(new Solution().catalan(i) + " ");
		System.out.println();
	}
}