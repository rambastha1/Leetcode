package main;

// 264. Ugly Number II
// build super ugly number from 1
/* dp[pointers[j]]
 * as pointer of j is pointer[j], its next number should be number at dp * num
 * i.e primes[j]*dp[pointers[j]]
 */
class Solution {
	// Time 0(KN)
    public int nthSuperUglyNumber(int n, int[] primes) {
    	if(n == 0 || primes == null || primes.length == 0)
    		return 0;
    	int k = primes.length;
    	int []pointers = new int[k];
    	int []dp = new int[n];
    	dp[0] = 1;
    	for(int i = 1;i < n;i++) {
    		int min = Integer.MAX_VALUE;
    		for(int j = 0;j < k;j++) {
    			min = Math.min(min, primes[j]*dp[pointers[j]]);
    		}
    		dp[i] = min;
    		for(int j = 0;j < k;j++) {
    			if(dp[i] == primes[j]*dp[pointers[j]])
    				pointers[j]++;
    		}
    	}
    	return dp[n-1];
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 12;
		int []primes = {2,7,13,19};
		System.out.println(new Solution().nthSuperUglyNumber(n, primes));
	}
}
