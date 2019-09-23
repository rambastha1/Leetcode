package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findPairs(int[] nums, int k) {
    	if(nums == null || nums.length <= 1)
    		return 0;
    	int n = nums.length;
    	// number -> count
    	Map<Integer, Integer> map = new HashMap<>();
    	for(int num : nums)
    		map.put(num, map.getOrDefault(num, 0) + 1);
    	
    	int count = 0;
    	for(int num : map.keySet()) {
    		if(k == 0) {
    			if(map.get(num) >= 2)
    				count++;
    		} else {
    			if(map.containsKey(num+k))
    				count++;
    		}
    	}
    	return count;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {3, 1, 4, 1, 5};
		int k = 2;
		System.out.println(new Solution().findPairs(nums, k));
	}
}