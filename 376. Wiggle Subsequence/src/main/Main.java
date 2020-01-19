package main;

class Solution {
	// 978
	public int wiggleMaxLength(int[] nums) {
		if(nums == null || nums.length == 0)
    		return 0;
    	if(nums.length == 1)
    		return 1;
    	int n = nums.length, up = 1, down = 1;
    	
    	for(int i = 1;i < n;i++) {
    		if(nums[i] > nums[i-1])
    			up = down +1;
    		else if(nums[i] < nums[i-1])
    			down = up+1;
    	}
    	return Math.max(up, down);
	}
	
	// Time 0(N^2) Space 0(N)
    public int wiggleMaxLength1(int[] nums) {
    	if(nums == null || nums.length == 0)
    		return 0;
    	if(nums.length == 1)
    		return 1;
    	int n = nums.length, ans = 1;
    	int [][]dp = new int[n][2];
    	dp[0][0] = 1;
    	dp[0][1] = -1;
    	for(int i = 1;i < n;i++) {
    		for(int j = i-1;j >= 0;j--) {
    			if(j == 0) {
    				if(nums[i] > nums[j] && dp[i][0] < dp[0][0] + 1) {
    					dp[i][0] = dp[j][0] + 1;
    					dp[i][1] = 1;
    				} else if(nums[i] < nums[j] && dp[i][0] < dp[0][0] + 1) {
    					dp[i][0] = dp[j][0] + 1;
    					dp[i][1] = 0;
    				}
    			} else {
    				if(((nums[i] > nums[j] && dp[j][1] == 0) ||(nums[i] < nums[j] && dp[j][1] == 1)) && dp[i][0] < dp[j][0] + 1) {
    					dp[i][0] = 1 + dp[j][0];
    					dp[i][1] = 1^dp[j][1];
    				}
    			}
    		}
    		ans = Math.max(ans, dp[i][0]);
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []nums = {1,7,4,9,2,5};
		int []nums = {1,17,5,10,13,15,10,5,16,8};
		System.out.println(new Solution().wiggleMaxLength(nums));
		System.out.println(new Solution().wiggleMaxLength1(nums));
	}
}
