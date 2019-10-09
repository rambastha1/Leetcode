package main;

import java.util.HashMap;
import java.util.Map;

// https://www.geeksforgeeks.org/longest-sub-array-sum-k/

class Solution {
	public int maxSubArrayLen(int[] nums, int k) {
		if(nums == null || nums.length == 0)
			return 0;
		
		int curr = 0, ans = 0;
		//sum-> start index
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0;i < nums.length;i++) {
			curr += nums[i];
			if(curr == k)
				ans = i+1;
			if(map.containsKey(curr-k))
				ans = Math.max(ans, i - map.get(curr-k));
			if(!map.containsKey(curr))
				map.put(curr, i);
		}
		return ans;
	}
}

public class Main {
	public static void main(String[] args) {
		/*int []A = {10,5,2,7,1,9};
		int k = 15;*/
		
		/*int []A = {1, -1, 5, -2, 3};
		int k = 3;*/
		
		int []nums = {-2, -1, 2, 1};
		int k = 1;
		
		/*int []A = {-5, 8, -14, 2, 4, 12};
		int k = -5;*/
		System.out.println(new Solution().maxSubArrayLen(nums, k));
	}
}