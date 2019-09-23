package main;

class Solution {

	public int combinationSum4(int[] nums, int target) {
    	int []dp = new int[target+1];
    	dp[0] = 1;
    	for(int col = 1;col < dp.length;col++) {
    		for(int row = 0;row < nums.length;row++) {
    			if(col - nums[row] >= 0)
    				dp[col] += dp[col-nums[row]];
    		}
    	}
    	return dp[dp.length-1];
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,2,3};
		int target = 4;
		System.out.println(new Solution().combinationSum4(nums, target));
	}
}