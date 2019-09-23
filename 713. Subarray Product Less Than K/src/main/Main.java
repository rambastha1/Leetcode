package main;

class Solution {
	public int numSubarrayProductLessThanK(int[] nums, int k) {
		// Imp
        if(k <= 1) return 0;
    	if(nums == null || nums.length == 0)
    		return 0;
    	int n = nums.length, ans = 0, start = 0;
    	int prod = 1;
    	for(int end = 0;end < n;end++) {
    		prod *= nums[end];
    		while(prod >= k) {
    			prod /= nums[start++];
    		}
    		ans += end-start+1;
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {10,5,2,6};
		int k = 100;

		/*int []nums = {1,2,3};
		int k = 0;*/
		System.out.println(new Solution().numSubarrayProductLessThanK(nums, k));
	}
}
