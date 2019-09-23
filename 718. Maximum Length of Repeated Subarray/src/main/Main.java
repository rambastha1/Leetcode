package main;

/* https://www.geeksforgeeks.org/longest-common-substring-dp-29/
 * The longest common suffix has following optimal substructure property.
 * If last characters match, then we reduce both lengths by 1
 * LCSuff(X, Y, m, n) = LCSuff(X, Y, m-1, n-1) + 1 if X[m-1] = Y[n-1]
 * If last characters do not match, then result is 0, i.e.,
 * LCSuff(X, Y, m, n) = 0 if (X[m-1] != Y[n-1])
 */

class Solution {
	// 1D array
	// can do using storing prev stage value and going j forward
	public int findLength(int[] A, int[] B) {
		if(A.length == 0 || B.length == 0)
    		return 0;
    	int n1 = A.length;
    	int n2 = B.length;
    	int res = 0;
    	int []dp = new int[n2+1];
    	for(int i = 0;i < n1;i++) {
    		//backward because we need previous stage value
    		for(int j = n2-1;j >= 0;j--) {
    			if(A[i] == B[j]) {
    				dp[j+1] = dp[j] + 1;
    				res = Math.max(res, dp[j+1]);
    			} else
    				// longest common substring
                    dp[j+1] = 0;
    		}
    	}
    	return res;
	}
	
	
	//2 D array DP
    /*public int findLength(int[] A, int[] B) {
    	if(A.length == 0 || B.length == 0)
    		return 0;
    	int n1 = A.length;
    	int n2 = B.length;
    	int res = 0;
    	int [][]dp = new int[n1+1][n2+1];
    	for(int i = 0;i <= n1;i++) {
    		for(int j = 0;j <= n2;j++) {
    			if(i == 0 || j == 0)
    				dp[i][j] = 0;
    			else if(A[i-1] == B[j-1]) {
    				dp[i][j] = dp[i-1][j-1] + 1;
    				res = Math.max(res, dp[i][j]);
    			} else
    				dp[i][j] = 0;
    		}
    	}
    	return res;
    }*/
}

public class Main {
	public static void main(String[] args) {
		int []A =  {1,2,3,2,1}, B = {3,2,1,4,7};
		//int []A =  {0,1,1,1,0}, B = {1,1,1,1,1};
		System.out.println(new Solution().findLength(A, B));
	}
}