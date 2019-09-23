package main;

class Solution {
    public boolean canPartition(int[] nums) {
    	
    	if(nums == null || nums.length == 0)
    		return false;
    	int sum = 0;
    	for(int num : nums)
    		sum += num;
    	if(sum % 2 != 0)
    		return false;
    	sum /= 2;
    	boolean []dp = new boolean[sum+1];
    	dp[0] = true;
    	for(int i = 0;i < nums.length;i++) {
    		for(int j = sum;j >= nums[i];j--) {
    			dp[j] = dp[j] || dp[j- nums[i]];
    		}
    	}
    	return dp[sum];
    }
}

public class Main {
	public static void main(String[] args) {
		//int []nums = {1, 5, 11, 5};
		int []nums = {2,2,3,5};
		System.out.println(new Solution().canPartition(nums));
	}
}
