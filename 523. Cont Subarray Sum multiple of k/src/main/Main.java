package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
	
	public boolean checkSubarraySum(int[] nums, int k) {
		if(nums == null || nums.length == 0)
			return false;
		int n = nums.length;
		if(k == 1)
			return n >= 2;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == 0 && nums[i + 1] == 0) return true;
		}

		// At this point, k can't be "0" any longer.
		if (k == 0) return false;
		// sum( of remainders) -> index
		Map<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		for(int i = 0;i < n;i++) {
			if(k != 0)
				sum = (sum + nums[i]%k)%k;
			if((sum == 0 && i>=1) || (map.containsKey(k - sum) && i-map.get(k-sum)>=2))
				return true;
			map.put(sum, i);
		}
		return true;
	}
}

public class Main {
	public static void main(String[] args) {
		/*int []nums = {23, 2, 4, 6, 7};
		int k=6;*/
		/*int []nums = {23, 2, 6, 4, 7};
		int k=6;*/
		int []nums = {0,0};
		int k=0;
		System.out.println(new Solution().checkSubarraySum(nums, k));
	}
}
