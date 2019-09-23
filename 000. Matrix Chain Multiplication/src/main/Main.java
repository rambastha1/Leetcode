package main;

/*
 * Good Video in DS folder
 * 
 */

class Solution {
	int matrix(int []matrix) {
    	if(matrix == null || matrix.length < 2)
    		return 0;
    	int n = matrix.length;
    	int [][]dp = new int[n][n];
    	for(int gap = 2;gap < n;gap++) {
    		for(int i = 0, j = gap;j < n;i++,j++) {
    			if(j < i+1)
    				dp[i][j] = 0;
    			else {
    				dp[i][j] = Integer.MAX_VALUE;
    				for(int k = i+1;k < j;k++) {
    					int value = dp[i][k] + dp[k][j] + matrix[i]*matrix[k]*matrix[j];
    					dp[i][j] = Math.min(dp[i][j], value);
    				}
    			}
    		}
    	}
    	return dp[0][n-1];
    }
	
	void print(int [][]order) {
		for(int i = 0;i < order.length;i++) {
			for(int j = 0;j < order[0].length;j++)
				System.out.print(order[i][j] + "\t");
			System.out.println();
		}
	}
}

public class Main {
	public static void main(String[] args) {
		int []matrix = {40, 20, 30, 10, 30};
		System.out.println(new Solution().matrix(matrix));
	}
}