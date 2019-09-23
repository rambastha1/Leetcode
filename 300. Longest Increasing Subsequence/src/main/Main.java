package main;

import java.util.Arrays;

class Solution {
	
	/*
	 * 0(NlgN) approach using DP and Binary Search 
	 */	
	public int ceil(int []nums, int l, int r, int key) {
		while(r - l > 1) {
			int m = l + (r-l)/2;
			if(key <= nums[m])
				r= m;
			else
				l = m;
		}
		return r;
	}
	
    public int lengthOfLIS(int[] nums) {
    	
    	int []LIS = new int[nums.length];
    	int len = 1;
    	LIS[0] = nums[0];
    	for(int i = 1;i < nums.length;i++) {
    		if(nums[i] < LIS[0])
    			LIS[0] = nums[i];
    		else if(nums[i] > LIS[len-1])
    			LIS[len++] = nums[i];
    		else
    			LIS[ceil(LIS, 0, len-1, nums[i])] = nums[i];
    	}
    	return len;
    }
    
    /*
     * 0(NlgN) using Leetcode Solution
     */
    public int lengthOfLIS1(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }
}

public class Main {

	public static void main(String[] args) {
		//int []nums = {10,9,2,5,3,7,101,18};
		//int []nums = {1,3,6,7,9,4,10,5,6};
		int nums[] = { 2, 5, 3, 7, 11, 8, 10, 13, 6 }; 
		System.out.println(new Solution().lengthOfLIS(nums));
	}
}