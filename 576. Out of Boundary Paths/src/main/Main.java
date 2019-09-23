package main;

/* similar to 688, 935
 * https://leetcode.com/articles/out-of-boundary-paths/ 
 */
class Solution {
    
	// Time 0(m*n) Space 0(m*n)
	public int findPaths(int m, int n, int N, int i, int j) {
        int mod = 1000000000 + 7;
        int dp[][] = new int[m][n];
        dp[i][j] = 1;
        int count = 0;
        
        for (int moves = 1; moves <= N; moves++) {
            int[][] temp = new int[m][n];
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    if (x == m - 1)
                        count = (count + dp[x][y]) % mod;
                    if (y == n - 1)
                        count = (count + dp[x][y]) % mod;
                    if (x == 0)
                        count = (count + dp[x][y]) % mod;
                    if (y == 0)
                        count = (count + dp[x][y]) % mod;
                    temp[x][y] = (((x > 0 ? dp[x - 1][y] : 0) + (x < m - 1 ? dp[x + 1][y] : 0)) % mod + 
                    		((y > 0 ? dp[x][y - 1] : 0) + (y < n - 1 ? dp[x][y + 1] : 0)) % mod) % mod;
                }
            }
            dp = temp;
        }
        return count;
    }
}

public class Main {
	public static void main(String[] args) {
		int m = 1, n = 3, N = 3, i = 0, j = 1;
		System.out.println(new Solution().findPaths(m, n, N, i, j));
	}
}
