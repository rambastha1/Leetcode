package main;

import java.util.Arrays;

/* https://leetcode.com/problems/find-k-th-smallest-pair-distance/discuss/109082/Approach-the-problem-using-the-%22trial-and-error%22-algorithm
 * main issue is how to find number of pair with difference <= m
 * sort and use sliding window
 */
class Solution {
	// 0(nlgn + nlgD) 
    public int smallestDistancePair(int[] nums, int k) {
    	// 0(nlgn)
    	int n = nums.length;
    	// number -> count
    	Arrays.sort(nums);
    	int l = 0, r = nums[n-1] - nums[0];
    	// 0(nlgd) -> d = differnce
    	while(l <= r) {
    		int m = l + (r-l)/2;
    		int s = 0, count = 0;
    		for(int e = 0;e < n;e++) {
    			while(nums[e] > nums[s] + m)
    				s++;
    			count += e-s;
    		}
    		
    		if(count < k)
    			l = m + 1;
    		else
    			r = m - 1;
    	}
    	return l;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,3,1};
		int k = 0;
		System.out.println(new Solution().smallestDistancePair(nums, k));
	}
}
