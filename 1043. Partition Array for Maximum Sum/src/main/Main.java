package main;

/*
 * dp[i] is maximum sum till that point
 * it goes backward from i to k indices
 * k = 1 means A[i] is used as it is and sum is calculated using dp[i-1] + A[i]
 * k = 2 means A[i] is currmax or is changed to currmax and sum = dp[i-2] + currmax*k as there are two 
 * number (currmax A[i] is changed thus sum = currmax + currmax or currmax*2).
 * k = 3 means all three numbers are changed to currmax and max sum is stored in dp[i]
 */

class Solution {
	public int maxSumAfterPartitioning(int[] A, int K) {
        int N = A.length, dp[] = new int[N];
        for (int i = 0; i < N; ++i) {
            int curMax = 0;
            for (int k = 1; k <= K && i - k + 1 >= 0; ++k) {
                curMax = Math.max(curMax, A[i - k + 1]);
                dp[i] = Math.max(dp[i], (i >= k ? dp[i - k] : 0) + curMax * k);
            }
        }
        return dp[N - 1];
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {1,15,7,9,2,5,10};
		int K = 3;
		System.out.println(new Solution().maxSumAfterPartitioning(A, K));
	}
}