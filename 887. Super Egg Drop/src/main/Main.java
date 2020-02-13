package main;

/* When we drop an egg from a floor x, there can be two cases (1) The egg breaks (2) The egg doesn’t break.

1) If the egg breaks after dropping from xth floor, then we only need to check for floors lower than x with 
remaining eggs; so the problem reduces to x-1 floors and n-1 eggs
2) If the egg doesn’t break after dropping from the xth floor, then we only need to check for floors higher than x; 
so the problem reduces to k-x floors and n eggs.
 * 
 * https://leetcode.com/articles/super-egg-drop/
 * dp[i-1][x-1] is increasing function and dp[i][j-x] is decreasing -> maximum at intersection use binary search
 * thus keep decreasing dp[i-1][x-1] till it matches dp[i][j-x] 
 */

class Solution {
	// 0(KN) time and space
	// https://leetcode.com/problems/super-egg-drop/discuss/159079/Python-DP-from-kn2-to-knlogn-to-kn
	public int superEggDrop(int K, int N) {
		if(K == 1)
    		return N;
    	if(N == 1)
    		return 1;
    	int [][]dp = new int[K+1][N+1];
    	for(int i = 1;i <= N;i++)
    		dp[1][i] = i;
    	
    	for(int i = 2;i <= K;i++) {
    		int x = 1;
    		for(int j = 1;j <= N;j++) {
    			if(j == 1) {
    				dp[i][j] = 1;
    				continue;
    			}
    			
    			while(x <= j && dp[i][j-x] > dp[i-1][x-1])
    				x++;
    			dp[i][j] = 1 + dp[i-1][x-1];
    		}
    	}
    	return dp[K][N];
	}
	
	// https://leetcode.com/problems/super-egg-drop/discuss/159055/Java-DP-solution-from-O(KN2)-to-O(KNlogN)
	// 0(KN lgN)
	public int superEggDrop1(int K, int N) {
		if(K == 1)
    		return N;
    	if(N == 1)
    		return 1;
    	int [][]dp = new int[K+1][N+1];
    	for(int i = 1;i <= N;i++)
    		dp[1][i] = i;
    	
    	for(int i = 2;i <= K;i++) {
    		for(int j = 1;j <= N;j++) {
    			if(j == 1) {
    				dp[i][j] = 1;
    				continue;
    			}
    			int min = Integer.MAX_VALUE;
    			int l = 1, r = j;
    			while(l <= r) {
    				int m = l + (r-l)/2;
    				min = Math.min(min, 1 + Math.max(dp[i-1][m-1], dp[i][j-m]));
    				if(dp[i-1][m-1] == dp[i][j-m])
    					break;
    				if(dp[i-1][m-1] < dp[i][j-m])
    					l = m+1;
    				else
    					r = m-1;
    			}
    			dp[i][j] = min;
    		}
    	}
    	return dp[K][N];
	}
	
	// TLE 0(KN^2)
	// https://www.geeksforgeeks.org/egg-dropping-puzzle-dp-11/
    public int superEggDrop2(int K, int N) {
    	if(K == 1)
    		return N;
    	if(N == 1)
    		return 1;
    	int [][]dp = new int[K+1][N+1];
    	for(int i = 1;i <= N;i++)
    		dp[1][i] = i;
    	
    	for(int i = 2;i <= K;i++) {
    		for(int j = 1;j <= N;j++) {
    			if(j == 1) {
    				dp[i][j] = 1;
    				continue;
    			}
    			dp[i][j] = Integer.MAX_VALUE;
    			for(int x = 1;x <= j;x++) {
    				int val = 1 + Math.max(dp[i-1][x-1], dp[i][j-x]);
    				dp[i][j] = Math.min(dp[i][j], val);
    			}
    		}
    	}
    	
    	return dp[K][N];
    }
}

public class Main {
	public static void main(String[] args) {
		int K = 3, N = 14;
		System.out.println(new Solution().superEggDrop(K, N));
		System.out.println(new Solution().superEggDrop1(K, N));
		System.out.println(new Solution().superEggDrop2(K, N));
	}
}
