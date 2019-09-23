package main;

// https://leetcode.com/problems/domino-and-tromino-tiling/discuss/116581/Detail-and-explanation-of-O(n)-solution-why-dpn2*dn-1%2Bdpn-3

class Solution {
	
	public int numTilings(int N) {
        int a = 0, b = 1, c = 1, c2, mod = 1000000007;
        while (--N > 0) {
            c2 = (c * 2 % mod + a) % mod;
            a = b;
            b = c;
            c = c2;
        }
        return c;
    }
	
	public int numTilings1(int N) {
        if (N <= 2) return N;
        if (N == 3) return 5;
        
        int mod = 1000000007;
        int[] dp = new int[N+1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        for (int i = 4; i <= N; i++) {
            dp[i] = ((2 * dp[i-1] % mod) + (dp[i-3] % mod)) % mod;
        }        
        return dp[N];
    }
}

public class Main {
	public static void main(String[] args) {
		int N = 3;
		System.out.println(new Solution().numTilings(N));
	}
}
