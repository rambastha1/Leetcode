package main;

// https://leetcode.com/articles/maximal-square/

/* dp(i,j) represents the side length of the maximum square whose bottom right 
 * corner is the cell with index (i,j) in the original matrix.
 */
class Solution {
	
	// Time 0(M*N) Space 0(N) 
    public int maximalSquare(char[][] matrix) {
    	if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
    		return 0;
    	int m = matrix.length, n = matrix[0].length;
    	int []dp = new int[n+1];
    	int maxlen = 0, prev = 0;
    	for(int i = 1;i <= m;i++) {
    		for(int j = 1;j <= n;j++) {
    			int temp = dp[j];
    			if(matrix[i-1][j-1] == '1') {
    				dp[j] = Math.min(dp[j-1], Math.min(dp[j], prev)) + 1;
    				maxlen = Math.max(maxlen, dp[j]);
    			} else
    				dp[j] = 0;
    			prev = temp;
    		}
    	}
    	return maxlen*maxlen;
    }
    
    // constant space 
    public int maximalSquare1(char[][] matrix) {
    	if(matrix.length == 0) return 0;

    	int m = matrix.length, n = matrix[0].length;
    	int max = Integer.MIN_VALUE;

    	for(int j = 0; j < n && max != 1; ++j) 
    		if(matrix[0][j] == '1') 
    			max = 1; 
    	for(int i = 1; i < m && max != 1; ++i) 
    		if(matrix[i][0] == '1') 
    			max = 1;

    	for(int i = 1; i < m; ++i){
    	    for(int j = 1; j < n; ++j){
    	        if(matrix[i][j] == '1'){
    	            int s = Math.min(matrix[i-1][j] - '0', Math.min(matrix[i - 1][j - 1] - '0', matrix[i][j-1] - '0')) + 1;
    	            matrix[i][j] = (char)('0' + s);
    	            max = Math.max(max, s);
    	        }
    	    }
    	}
    	return max * max;
    }
}

public class Main {
	public static void main(String[] args) {
		char [][]matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},
				{'1','0','0','1','0'}};
		System.out.println(new Solution().maximalSquare(matrix));
		System.out.println(new Solution().maximalSquare1(matrix));
	}
}
