package main;

class Solution {
	
	/*
	 * 0(N) solution with one traversal
	 * https://www.youtube.com/watch?v=vtJvbRlHqTA 
	 */
	
	public int maxProduct(int[] nums) {
		int len = nums.length;
		int prev_max_prod = nums[0], prev_min_prod = nums[0];
		int curr_max_prod = nums[0], curr_min_prod = nums[0];
		int ans = nums[0];
		
		for(int i = 1;i < len;i++) {
			/*
			 * case 1: nums[i] > 0
			 * case 2: nums[i] < 0
			 * case 3: nums[i] is starting point of product, used when nums[i-1] = 0
			 */			
			curr_max_prod = Math.max(Math.max(prev_max_prod*nums[i], prev_min_prod*nums[i]), nums[i]);
			curr_min_prod = Math.min(Math.min(prev_min_prod*nums[i], prev_max_prod*nums[i]), nums[i]);
			ans = Math.max(ans, curr_max_prod);
			prev_max_prod = curr_max_prod;
			prev_min_prod = curr_min_prod;
		}
		return ans; 
	}
}

public class Main {
	public static void main(String[] args) {
		//int [] nums = {2,3,-2,4};
		int [] nums = {-2,3,-4};
		//int [] nums = {-2,0,-1};
		//int [] nums = {0,-4,0,-2};
		System.out.println(new Solution().maxProduct(nums));
	}
}