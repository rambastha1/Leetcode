package main;

class Solution {
    public int minFallingPathSum(int[][] A) {
    	if(A == null || A.length == 0 || A[0].length == 0)
    		return 0;
    	int fall = Integer.MAX_VALUE;
    	for(int i = 1;i < A.length;i++) {
    		for(int j = 0;j < A[i].length;j++) {
    			if(j == 0)
    				A[i][j] = Math.min(A[i][j] + A[i-1][j], A[i][j] + A[i-1][j+1]);
    			else if(j == A[i].length-1)
    				A[i][j] = Math.min(A[i][j] + A[i-1][j], A[i][j] + A[i-1][j-1]);
    			else
    				A[i][j] = Math.min(A[i][j] + A[i-1][j-1], Math.min(A[i][j] + A[i-1][j], A[i][j] + A[i-1][j+1]));
    		}
    	}
    	
    	for(int i = 0;i < A[0].length;i++)
    		fall = Math.min(fall, A[A.length-1][i]);
    	return fall;
    }
}

public class Main {
	public static void main(String[] args) {
		//int [][]A = {{1,2,3}, {4,5,6}, {7,8,9}};
		int [][]A = {{-80,-13,22}, {83,94,-5}, {73,-48,61}};
		System.out.println(new Solution().minFallingPathSum(A));
	}
}