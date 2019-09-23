package main;

class Solution {
	public int pivotIndex(int[] nums) {
    	if(nums == null || nums.length <= 1)
    		return -1;
    	int sum = 0;
    	// prefix sum -> count
    	for(int i = 0;i < nums.length;i++)
    		sum += nums[i];
    	int left = 0;
    	for(int i = 0;i < nums.length;i++) {
    		if(left == sum - left - nums[i])
    			return i;
    		left += nums[i];
    	}
    	return -1;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []nums = {1, 7, 3, 6, 5, 6};
		//int []nums = {-1,-1,-1,-1,-1,-1}; // ans should be 0
		int []nums = {-1,-1,-1,0,1,1}; // ans should be 0
		System.out.println(new Solution().pivotIndex(nums));
	}
}