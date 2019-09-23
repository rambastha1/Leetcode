package main;

class Solution {
	
	//using 1D array
	public int maxUncrossedLines(int[] A, int[] B) {
    	int []dp = new int[B.length+1];
    	for(int i = 1;i <= A.length;i++) {
    		int pre = 0;
    		for(int j = 1;j <= B.length;j++) {
    			int temp = dp[j];
    			if(A[i-1] == B[j-1])
    				dp[j] = 1+ pre;
    			else
    				dp[j] = Math.max(dp[j], dp[j-1]);
    			pre = temp;
    		}
    	}    	
    	return dp[B.length];
    }
	
	
	//Using 2D array
	/*public int maxUncrossedLines(int[] A, int[] B) {
    	int [][]dp = new int[A.length+1][B.length+1];
    	for(int i = 1;i <= A.length;i++) {
    		for(int j = 1;j <= B.length;j++) {
    			if(A[i-1] == B[j-1])
    				dp[i][j] = 1+ dp[i-1][j-1];
    			else
    				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
    		}
    	}    	
    	return dp[A.length][B.length];
    }*/
}

public class Main {
	public static void main(String[] args) {
		int []A = {2,5,1,2,5};
		int []B = {10,5,2,1,5,2};
		System.out.println(new Solution().maxUncrossedLines(A, B));
	}
}