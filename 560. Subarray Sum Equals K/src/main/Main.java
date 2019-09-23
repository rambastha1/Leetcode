package main;

import java.util.HashMap;
import java.util.Map;

// Logic from 000. Subarray with given sum

class Solution {
    public int subarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0)
        	return 0;
        // sum -> count (inplace of index as in above problem)
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, ans = 0;
        for(int i = 0;i < nums.length;i++) {
        	sum += nums[i];
        	if(sum == k)
        		ans++;        	       
        	ans += map.getOrDefault(sum-k, 0);
        	map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {10, 2, -2, -20, 10}; 
		int k = -10;
		
		/*int []nums = {0,0,0,0,0,0,0,0,0,0}; 
		int k = 0;*/
		
		/*int []nums = {1,2,3};
		int k = 3;*/
		System.out.println(new Solution().subarraySum(nums, k));
	}
}