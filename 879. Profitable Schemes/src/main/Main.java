package main;

class Solution {
	/* https://leetcode.com/problems/profitable-schemes/discuss/154617/C%2B%2BJavaPython-DP
	 * dp[i][j] means the count of schemes with i profit and j members.
	 * The dp equation is simple here:
	 * dp[i + p][j + g] += dp[i][j]) if i + p < P
	 * dp[P][j + g] += dp[i][j]) if i + p >= P
	 * 
	 * 
	 */
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
    	int n = group.length;
    	// dp[i][j] is number of schemes using 0-k using j members and i profit
    	int [][]dp = new int[P+1][G+1];
    	dp[0][0] = 1;
    	int mod = (int)1e9 + 7;
    	/* since dp[nx][ny] depends on upper left, traverse backward
    	 * You can imagine we actually need a 3D matrix dp[k][i][j], so every time in the inner two loops, 
    	 * we are doing our updates using the data coming from the old matrix (dp[k-1][i][j]). 
    	 * Since the author's recursive relation tells us that dp[i][j] depends on the element on its 
    	 * upper left (dp[Math.min(i + p, P)][j + g] + dp[i][j]), in order to avoid the first dimension and only use a 2D matrix, 
    	 * we scan backwards in the two inner loops in order to avoid updating the elements we are going to use later in the same loop. 
    	 * Thus we can use a 2D matrix to save some space without the need to create any temp copy of the previous matrix in the 
    	 * inner loop.
    	 */
    	for(int k = 0;k < n;k++) {
    		int g = group[k], p = profit[k];
    		for(int i = P;i >= 0;i--) {
    			// j can only start from G-g then only j+g <= G else condition won't follow 
    			for(int j = G-g;j >= 0;j--) {
    				dp[Math.min(P, p + i)][j+g] = (dp[i][j] + dp[Math.min(P, p + i)][j+g])%mod;
    			}
    		}
    	}
    	int sum = 0;
    	for(int x : dp[P])
    		sum = (sum + x)%mod;
    	return sum;
    }
}

public class Main {
	public static void main(String[] args) {
		int G = 5, P = 3;
		int []group = {2,2}, profit = {2,3};
		System.out.println(new Solution().profitableSchemes(G, P, group, profit));
	}
}
