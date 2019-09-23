package main;

// https://leetcode.com/problems/largest-sum-of-averages/discuss/122775/Java-bottom-up-DP-with-Explanation
/* f[i][j]be the largest sum of averages for first i + 1 numbers(A[0], A[1], ... , A[i]) to j groups.
 * f[i][j] consists of two parts: first j-1 groups' averages and the last group' s average.
 * since only last row data is needed 1d array will suffice
 */
class Solution {
	public double largestSumOfAverages1(int[] A, int K) {
        double[] dp = new double[A.length];
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            dp[i] = (double)(sum) / (i + 1);
        }
        // group size 2 to k
        for (int grp_size = 2; grp_size <= K; grp_size++) {
            double[] next = new double[A.length];
            // starting element should be size-1 as say group is 4  starting index should be 3
            for (int i = grp_size - 1; i < A.length; i++) {
                sum = 0;
                next[i] = 0;
                // from end it will be easier to calculate average of last group numbers p to i
                // check link for description
                // index should be from grp-size-1 to i
                for (int p = i; p >= grp_size - 1; p--) {
                    sum += A[p];
                    double val = (double)(sum) / (i - p + 1);
                    // dp[p-1] is sum of averages of first p-1 group 
                    next[i] = Math.max(next[i], val + dp[p - 1]);
                }
            }
            dp = next;
        }
        return dp[A.length - 1];
    }
	
	// Top Down with Memoization
	public double largestSumOfAverages2(int[] A, int K) {
		int N = A.length;
		double[][] memo = new double[N+1][N+1];
		double cur = 0;
		for(int i=0;i<N;i++){
			cur += A[i];
			memo[i+1][1] = cur/(i+1);
		}
		return search(N,K,A,memo);
	}

	public double search(int n,int k, int[] A, double[][] memo){
		if(memo[n][k]>0) return memo[n][k];
		if(n<k) return 0;
		double cur = 0;
		for(int i=n-1;i>0;i--){
			cur += A[i];
			memo[n][k] = Math.max(memo[n][k],search(i,k-1,A,memo)+cur/(n-i));
		}
		return memo[n][k];
	}
	public double largestSumOfAverages(int[] A, int K) {
        // dp[i][j] is the max average sum (MAS) of the array of length j 
		// (the subarray of A, starting from 0), with i partitions
        double[][] dp = new double[K + 1][A.length + 1]; 
        double sum = 0.0;
        // calculate the initial state, the MAS of 1 partition for each subarray
        for (int j = 1; j <= A.length; j++) { 
            sum += A[j - 1];
            dp[1][j] = sum / j;
        }
        // MCM WAY
        for (int i = 2; i <= K; i++) { // itearate from 2 partitions
            for (int j = 1; j <= A.length; j++) {
                // for each dp[i][j], it can be dp[i - 1][k] + the average of (A[k, ..., j - 1])
                // then we iterate k from j - 1 to 0, and get max dp[i][j]
                sum = 0;
                for (int k = j - 1; k >= 0; k--) {
                    sum += A[k];
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + /*avg of j-k*/ sum / (j - k));
                }
            }
        }
        return dp[K][A.length];
    }
}

public class Main {
	public static void main(String[] args) {

	}
}