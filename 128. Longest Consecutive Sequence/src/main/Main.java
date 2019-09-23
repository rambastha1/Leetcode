package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
	
	/*
	 * Time Complexity: 0(n)
	 * At first look, time complexity looks more than O(n). 
	 * If we take a closer look, we can notice that it is O(n) under the assumption that 
	 * hash insert and search take O(1) time. The function S.find() inside the
	 * while loop is called at most twice for every element. For example, consider the case when all array
	 * elements are consecutive. In this case, the outer find is called for every element, 
	 * but we go inside the if condition only for the smallest element. Once we are inside the if condition, 
	 * we call find() one more time for every other element.
	 * https://www.geeksforgeeks.org/longest-consecutive-subsequence/
	 */
	
    public int longestConsecutive(int[] nums) {
    	if(nums == null || nums.length == 0)
    		return 0;
    	if(nums.length == 1)
    		return 1;
    	Map<Integer, Integer> map = new HashMap<>();
    	for(int num : nums)
    		map.put(num, 1);
    	int res = 1;
    	for(int num : nums) {
    		if(!map.containsKey(num-1)) {
    			int i = num+1;
    			for(;;i++) {
    				if(!map.containsKey(i))
    					break;
    			}
    			res = Math.max(res, i-num);
    		}
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {100,4,200,1,3,2};
		System.out.println(new Solution().longestConsecutive(nums));
	}
}