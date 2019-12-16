package main;

// 221
/* find maximal square till i,j add it to answer
 * https://leetcode.com/problems/count-square-submatrices-with-all-ones/discuss/441306/Python-DP-solution
 */
class Solution {
    public int countSquares(int[][] matrix) {
    	int ans = 0, m = matrix.length, n = matrix[0].length;
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			if(i > 0 && j > 0 && matrix[i][j] == 1) {
    				// 1 because this 1*1 submatrix is also included
    				matrix[i][j] = 1 + Math.min(matrix[i-1][j-1], Math.min(matrix[i-1][j], matrix[i][j-1]));
    			}
    			// this will add all 1*1 in first row and first column as well
    			ans += matrix[i][j];
    		}
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]matrix = {{0,1,1,1}, {1,1,1,1}, {0,1,1,1}};
		System.out.println(new Solution().countSquares(matrix));
	}
}
