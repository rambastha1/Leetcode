package main;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> summaryRanges(int[] nums) {
    	List<String> res = new ArrayList<>();
    	if(nums == null || nums.length == 0)
    		return res;
    	int index = 0, n = nums.length;
    	for(int i = 1;i < n;i++) {
    		if(nums[i] != nums[i-1] + 1) {
    			if(index == i-1)
    				res.add(String.valueOf(nums[index]));
    			else
    				res.add(String.valueOf(nums[index]) + "->" + String.valueOf(nums[i-1]));
    			index = i;
    		}
    	}
    	if(index == n-1)
    		res.add(String.valueOf(nums[index]));
    	else
    		res.add(String.valueOf(nums[index]) + "->" + String.valueOf(nums[n-1]));
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {0,2,3,4,6,8,9};
		System.out.println(new Solution().summaryRanges(nums));
	}
}
