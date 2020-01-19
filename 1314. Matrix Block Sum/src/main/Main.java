package main;

import java.util.Arrays;
/* Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of all 
 * elements mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.
 

Example 1:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
Output: [[12,21,16],[27,45,33],[24,39,28]]
Example 2:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
Output: [[45,45,45],[45,45,45],[45,45,45]]
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n, K <= 100
1 <= mat[i][j] <= 100
 * 
 */
class Solution {
	//  304, 307, 308
    public int[][] matrixBlockSum(int[][] mat, int K) {
    	int m = mat.length, n = mat[0].length;
    	int [][]dp = new int[m+1][n+1];
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			dp[i+1][j+1] = mat[i][j] + dp[i+1][j] + dp[i][j+1] - dp[i][j];
    		}
    	}
    	
    	int [][]ans = new int[m][n];
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			int r1 = Math.max(0, i-K), c1 = Math.max(0, j-K), r2 = Math.min(m, i+K+1), c2 = Math.min(n, j+K+1);
    			ans[i][j] = dp[r2][c2] - dp[r2][c1] - dp[r1][c2] + dp[r1][c1];
    		}
    	}
    	for(int []a : ans) 
    		System.out.println(Arrays.toString(a));
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]mat = {{67,64,78},{99,98,38},{82,46,46},{6,52,55},{55,99,45}};
		int K = 3;
		new Solution().matrixBlockSum(mat, K);
	}
}
