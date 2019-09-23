package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* General Case
 * https://leetcode.com/problems/4sum/discuss/8609/My-solution-generalized-for-kSums-in-JAVA
 * 
 * https://leetcode.com/problems/4sum/discuss/8565/Lower-bound-n3
 * use map when no duplicates
 */


class Solution {
	// Time 0(N^3) constant space
    public List<List<Integer>> fourSum(int[] nums, int target) {
    	List<List<Integer>> res = new ArrayList<>();
    	if(nums == null || nums.length < 4)
    		return res;
    	int n = nums.length;
    	Arrays.sort(nums);
    	
    	for(int i = 0;i < n-3;i++) {
    		if(nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target)
    			break;
    		if(nums[i] + nums[n-1] + nums[n-2] + nums[n-3] < target)
    			continue;
    		if(i > 0 && nums[i] == nums[i-1])
    			continue;
    		// three sum
    		for(int j = i+1;j < n-2;j++) {
    			if(nums[i] + nums[j] + nums[j+1] + nums[j+2] > target)
        			break;
    			if(j > i+1 && nums[j] == nums[j-1])
    				continue;
    			// two sum
    			int l = j+1, r = n-1;
    			while(l < r) {
    				int sum = nums[i] + nums[j] + nums[l] + nums[r];
    				if(sum == target) {
    					res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
    					l++;
    					r--;
        				while(l < r && nums[l] == nums[l-1])
        					l++;
        				while(l < r && nums[r] == nums[r+1])
        					r--;
    				} else if(sum < target) {
        				l++;
    				} else
        				r--;
    			} 
    		}
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1, 0, -1, 0, -2, 2};
		int target = 0;
		System.out.println(new Solution().fourSum(nums, target));
	}
}
