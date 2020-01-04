package main;

// https://leetcode.com/problems/number-of-music-playlists/discuss/180338/DP-solution-that-is-Easy-to-understand
/*
 * dp[i][j] -> minimum ways to listen to i songs using j unique songs
 * Time ans Space 0(LN)
 */
class Solution {
	public int numMusicPlaylists(int N, int L, int K) {
    	long [][]dp = new long[L+1][N+1];
    	dp[0][0] = 1;
    	int mod = (int)1e9 + 7;
    	for(int i = 1;i <= L;i++) {
    		for(int j = 1;j <= N;j++) {
    			dp[i][j] = (dp[i-1][j-1]*(N-j+1))%mod;
    			if(j > K)
    				dp[i][j] = (dp[i][j] + (dp[i-1][j]*(j-K))%mod)%mod;
    		}
    	}
    	return (int)dp[L][N]%mod;
    }
}

public class Main {
	public static void main(String[] args) {
		int N = 3, L = 3, K = 1;
		System.out.println(new Solution().numMusicPlaylists(N, L, K));
	}
}
