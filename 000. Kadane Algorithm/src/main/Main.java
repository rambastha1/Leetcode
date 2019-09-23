package main;

class Solution {
	int kadane(int []nums) {
		int sum = nums[0];
		int max = nums[0];
		for(int i = 1;i < nums.length;i++) {
			sum = Math.max(nums[i], nums[i] + sum);
			max = Math.max(max, sum);
		}		
		return max;
	}
}

public class Main {
	public static void main(String[] args) {
		int []nums = {-2,-3,4,-1,-2,1,5,-3};
		System.out.println(new Solution().kadane(nums));
	}
}