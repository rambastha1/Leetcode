package main;
// https://www.geeksforgeeks.org/minimum-cost-polygon-triangulation/

// time 0(N^3)
class Solution {
    public int minScoreTriangulation(int[] A) {
    	if(A == null || A.length < 3)
    		return 0;
    	if(A.length == 3)
    		return A[0]*A[1]*A[2];
    	int n = A.length;
    	int [][]dp = new int[n][n];
    	for(int gap = 0;gap < n;gap++) {
    		for(int i = 0, j= gap;j < n;i++,j++) {
    			if(j < i+2)
    				dp[i][j] = 0;
    			else {
					dp[i][j] = Integer.MAX_VALUE;
					for(int k = i+1;k < j;k++) {
						int value = dp[i][k] + dp[k][j] + A[i]*A[k]*A[j];
						dp[i][j] = Math.min(dp[i][j], value);
					}
    			}
    		}
    	}
    	return dp[0][n-1];
    }
}

public class Main {
	public static void main(String[] args) {
		//int []A = {1,2,3};
		//int []A = {3,7,4,5};
		int []A = {1,3,1,4,1,5};
		System.out.println(new Solution().minScoreTriangulation(A));
	}
}
