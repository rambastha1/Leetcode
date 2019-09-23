package main;

class Solution {
	
	/*
	 * https://leetcode.com/articles/arithmetic-slices/
	 * There is no need to check of AP of size > 3. dp will store all AP before A[i].
	 * Thus if A[i] is also part of AP of A[i-3], A[i-2] and A[i-1], it will 1 to dp thus from A[i]
	 * there are two APs.
	 * count+= dp will give all the counts.
	 */
	public int numberOfArithmeticSlices(int[] A) {
		int dp = 0, count = 0;
		for(int i = 2;i < A.length;i++) {
			if(A[i] - A[i-1] == A[i-1] - A[i-2]) {
				dp = 1 + dp;
				count += dp;
			} else
				dp = 0;
		}
		return count;
	}
	
	//My solution working Time 0(N^2)
    /*public int numberOfArithmeticSlices(int[] A) {
    	if(A == null || A.length < 3)
    		return 0;
    	int count = 0;
    	int []dp = new int[A.length];
    	dp[0] = dp[1] = 0;
    	for(int i = 2;i < A.length;i++) {
    		int diff = A[i] - A[i-2];
    		for(int j = i-2;j >= 0;j--) {
    			if(A[j+1] - A[j] == diff) {
    				count++;
    			}
    		}
    		//dp[i] = dp[i-1]+count;
    	}
    	return count;
    }
    
    boolean isAP(int []A, int low, int high) {
    	if(low < 0 || high >= A.length || high-low+1 < 3)
    		return false;
    	int diff = A[low+1] - A[low];
    	for(int i = low+2;i <= high;i++)
    		if(A[i] - A[i-1] != diff)
    			return false;
    	return true;
    }*/
}

public class Main {
	public static void main(String[] args) {
		int []A = {1,3,5,7,9};
		System.out.println(new Solution().numberOfArithmeticSlices(A));
	}
}