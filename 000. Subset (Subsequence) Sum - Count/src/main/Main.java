package main;
// https://stackoverflow.com/questions/22891076/count-number-of-subsets-with-sum-equal-to-k
// https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/
class Solution {
	// Not working Space 0(K)
	public int count(int []A, int k) {
		if(A == null || A.length == 0)
			return 0;
		int n = A.length;
		int []dp = new int[k+1];
		dp[0] = 1;
		for(int i = 1;i <= n;i++) {
			for(int j = 1;j <= k;j++) {
				/*if(j - A[i-1] == 0)
					dp[j] += 1;*/
				if(j - A[i-1] >= 0 && j - A[i-1] <= k)
					dp[j] += dp[j-1] + dp[j-A[i-1]];
			}
		}		
		return dp[k];
	}
	
	// Working Space 0(N*K)
	public int count1(int []A, int k) {
		if(A == null || A.length == 0)
			return 0;
		int n = A.length;
		int [][]dp = new int[n+1][k+1];
		for(int i = 0;i <= n;i++)
			dp[i][0] = 1;
		
		for(int i = 1;i <= n;i++) {
			for(int j = 1;j <= k;j++) {
				dp[i][j] += dp[i-1][j];
				// second and case for -ve numbers
				if(j - A[i-1] >= 0 && j - A[i-1] <= k)
					dp[i][j] += dp[i-1][j-A[i-1]];
			}
		}
		return dp[n][k];
	}
}

public class Main {
	public static void main(String[] args) {
		int []A = {1,4,-1,10,5};
		int k = 9;
		System.out.println(new Solution().count(A, k));
	}
}
