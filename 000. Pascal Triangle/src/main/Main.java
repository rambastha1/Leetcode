package main;

class Solution {
	void pascal(int n) {
		if(n == 0) {
			System.out.println("1");
			return;
		}
		int []dp = new int[n+1];
		dp[0] = 1;
		for(int i = 1;i <= n;i++) {
			int []curr = new int[n+1];
			for(int j = 0;j <= i;j++) {
				if(j == 0)
					curr[j] += dp[j];
				else if(j == i)
					curr[j] += dp[j-1];
				else
					curr[j] += dp[j-1] + dp[j];
			}
			dp = curr;
		}
		print(dp);
	}
	
	void print(int []res) {
		for(int i : res)
			System.out.print(i + " ");
		System.out.println();
	}
}

public class Main {
	public static void main(String[] args) {
		int n = 10;
		new Solution().pascal(n);
	}
}
