package main;
/* https://leetcode.com/problems/stone-game-ii/discuss/345247/C%2B%2B-DP-(Tabulation)
 * dp[i][j] is the maximum number of stones Alex can get when starting at index i with M = j
sufsum[i] is the total number of stones from index i to the end
The dp matrix for Lee is the same. And the stragegy for Alex is to choose an optimal X to minimize the number of 
stones Lee can get when starting at index (i + X) with M = max(X,j). Here we have the recurrence formula

dp[i][j] = max(dp[i][j], sufsum[i] - dp[i + X][max(j, X)]) where 1<= X <= 2j;
 */
class Solution {
    public int stoneGameII(int[] piles) {
    	int n = piles.length;
    	int []suf = new int[n+1];
    	// maximum number of stones Alex can get starting at index i and M = j 
    	int [][]dp = new int[n+1][n+1];
    	for(int i = n-1;i >= 0;i--)
    		suf[i] = suf[i+1] + piles[i];
    	
    	for(int i = 0;i <= n;i++)
    		dp[i][n] = suf[i];
    	
    	for(int i = n-1;i >= 0;i--) {
    		for(int j = n-1;j >= 1;j--) {
    			for(int X = 1;i + X <= n && X <= 2*j;X++) {
    				dp[i][j] = Math.max(dp[i][j], suf[i] - dp[i+X][Math.max(j, X)]);
    			}
    		}
    	}
    	return dp[0][1];
    }
}

public class Main {
	public static void main(String[] args) {
		int []piles = {2,7,9,4,4};
		System.out.println(new Solution().stoneGameII(piles));
	}
}
