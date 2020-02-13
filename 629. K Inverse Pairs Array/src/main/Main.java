package main;

/* for any length i, maximum inversion possible = i*(i-1)/2 i.e decreasing array
 * https://leetcode.com/problems/k-inverse-pairs-array/discuss/104815/Java-DP-O(nk)-solution
 * https://www.geeksforgeeks.org/number-of-permutation-with-k-inversions/
 */

class Solution {
    public int kInversePairs(int n, int k) {
    	if(n == 0 || k > (n*(n-1))/2)
    		return 0;
    	if(k == 0 || k == (n*(n-1))/2)
    		return 1;
    	int mod = (int)1e9 + 7;
    	long [][]dp = new long[n+1][k+1];
    	dp[2][0] =  dp[2][1] = 1;
    	for(int i = 3;i <= n;i++) {
    		dp[i][0] = 1;
    		for(int j = 1;j <= Math.min(k, (i*(i-1))/2);j++) {
    			dp[i][j] = (dp[i-1][j] + dp[i][j-1] + mod)%mod;
    			if(j >= i)
    				dp[i][j] = (dp[i][j] - dp[i-1][j-i] + mod)%mod;
    		}
    	}
    	return (int)(dp[n][k]%mod);
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 3, k = 1;
		System.out.println(new Solution().kInversePairs(n, k));
	}
}
