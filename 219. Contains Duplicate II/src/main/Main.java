package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
    	// nums[i] -> i
    	Map<Integer, Integer>  map = new HashMap<>();
    	int n = nums.length;
    	for(int i = 0;i < n;i++) {
    		if(map.containsKey(nums[i]) && i- map.get(nums[i]) <= k)
    			return true;
    		map.put(nums[i], i);
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int []nums = {1,2,3,1,2,3};
		int k = 2;*/
		int []nums = {1,2,3,1};
		int k = 3;
		System.out.println(new Solution().containsNearbyDuplicate(nums, k));
	}
}
