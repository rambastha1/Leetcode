package main;

class Solution {
    public int rob(int[] nums) {
    	if (nums == null || nums.length == 0) return 0;  
        if (nums.length == 1) return nums[0]; 
        int sum2 = nums[nums.length - 1]; 
        int sum1 = Math.max(nums[nums.length - 1], nums[nums.length - 2]); 
        for (int i = nums.length - 3; i > -1; i--) {
            int sum = Math.max(nums[i] + sum2, sum1);
            sum2 = sum1; 
            sum1 = sum; 
        }
        return sum1; 
    }
}

public class Main {
	public static void main(String[] args) {
		//int []nums = {1,2,3,1};
		int []nums = {2,7,9,3,1};
		System.out.println(new Solution().rob(nums));
	}
}