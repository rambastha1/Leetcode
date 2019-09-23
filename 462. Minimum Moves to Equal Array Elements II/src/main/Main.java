package main;

import java.util.Arrays;

class Solution {
	
    public int minMoves2(int[] nums) {
    	int n = nums.length;
    	Arrays.sort(nums);
    	//int median = (n%2 == 0)?(nums[n/2-1] + nums[n/2])/2 : nums[n/2];
    	int l = 0, r = n-1;
    	int moves = 0;
    	while(l < r) {
    		//This is gap of two halves.. this gap can be directly calculated 
    		//moves += Math.abs(median - nums[l]) + Math.abs(median - nums[r]);
    		moves += Math.abs(nums[r] - nums[l]);
    		l++;
    		r--;
    	}
    	return moves;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,2};
		System.out.println(new Solution().minMoves2(nums));
	}
}
