package main;

import java.util.Arrays;

// https://www.youtube.com/watch?time_continue=8&v=FBQbm26tSzA
// https://leetcode.com/problems/split-array-with-same-average/discuss/120667/C%2B%2B-Solution-with-explanation-early-termination-(Updated-for-new-test-case)

class Solution {
    public boolean splitArraySameAverage(int[] A) {
    	if(A == null || A.length <= 1)
    		return false;
    	
    	int n = A.length;
    	int sum = 0;
    	for(int a : A)
    		sum += a;
    	boolean [][]dp = new boolean[n/2+1][sum+1];
    	dp[0][0] = true;
    	for(int a : A) {
    		for(int i = n/2;i >= 1;i--) {
    			for(int j = sum;j >= a;j--) {
    				dp[i][j] = dp[i][j] || dp[i-1][j - a]; 
    			}
    		}
    	}
    	
    	for(int i = 1;i <= n/2;i++) {
    		if((sum*i)%n == 0 && dp[i][(sum*i)/n])
    			return true;
    	}
    	return false;
    }
	
	/*public boolean splitArraySameAverage(int[] A) {
        int sum = 0;
        for(int num : A){
            sum += num;
        }
        boolean[][] dp = new boolean[sum+1][A.length/2 + 1];
        dp[0][0] = true;
        for(int num : A){
            for(int i = sum; i >= num; i--){
                for(int j = 1; j <= A.length/2; j++){
                    dp[i][j] = dp[i][j] || dp[i-num][j - 1];
                }
            }
        }
        for (int i = 1; i <= A.length/2; ++i) 
            if (sum*i%A.length == 0 && dp[sum*i/A.length][i]) return true;
        return false;
    }*/
}

public class Main {
	public static void main(String[] args) {
		//int []A = {1,2,3,4,5,6,7,8};
		int []A = {2,0,5,6,16,12,15,12,4};
		System.out.println(new Solution().splitArraySameAverage(A));
	}
}