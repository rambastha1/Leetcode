package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean containsDuplicate(int[] nums) {
    	if(nums == null || nums.length <= 1)
    		return false;
    	Map<Integer, Integer> map = new HashMap<>();
    	for(int i = 0;i < nums.length;i++) {
    		if(map.containsKey(nums[i]))
    			return true;
    		map.put(nums[i], i);
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []nums = {1,1,1,3,3,4,3,2,4,2};
		int []nums = {1,2,3,1};
		System.out.println(new Solution().containsDuplicate(nums));
	}
}
