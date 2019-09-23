package main;

class NumMatrix {
	int m, n;
	int [][]dp;
    public NumMatrix(int[][] matrix) {
    	if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
    		return;
        this.m = matrix.length;
        this.n = matrix[0].length;
        dp = new int[m][n];
        dp[0][0] = matrix[0][0];
        
        for(int j = 1;j < n;j++)
        	dp[0][j] = matrix[0][j] + dp[0][j-1];
        
        for(int i = 1;i < m;i++)
        	dp[i][0] = matrix[i][0] + dp[i-1][0];
        
        for(int i = 1;i < m;i++) {
        	for(int j = 1;j < n;j++) {
        		dp[i][j] = matrix[i][j] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1]; 
        	}
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
    	return dp[row2][col2] - dp[row2][col1-1] - dp[row1-1][col2] + dp[row1-1][col1-1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */

public class Main {
	public static void main(String[] args) {
		int [][]matrix = {{3,0,1,4,2}, {5,6,3,2,1}, {1,2,0,1,5}, {4,1,0,1,7}, {1,0,3,0,5}};
		NumMatrix n = new NumMatrix(matrix);
		System.out.println(n.sumRegion(2,1,4,3));
		System.out.println(n.sumRegion(1,1,2,2));
		System.out.println(n.sumRegion(1,2,2,4));
	}
}
