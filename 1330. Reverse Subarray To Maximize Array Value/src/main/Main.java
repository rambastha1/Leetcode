package main;

import java.util.Arrays;

class Solution {
    public int maxValueAfterReverse(int[] nums) {
    	int n = nums.length;
    	if(n == 1)
    		return 0;
    	int []dp = new int[n];
    	Arrays.fill(dp, Integer.MIN_VALUE);
    	dp[0] = 0;
    	
    	for(int len = 1;len < n;len++) {
    		for(int i = 0, j = len;j < n;i++, j++) {
    			for(int k = i;k <= j;k++) {
    				
    			}
    		}
    	}
    	return 0;
    }
    
    
}

public class Main {
	public static void main(String[] args) {
		int []nums = {2,3,1,5,4};
		System.out.println(new Solution().maxValueAfterReverse(nums));
	}
}
