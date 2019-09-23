package main;

class Solution {
	
	public int wiggleMaxLength(int[] nums) {
		if(nums == null || nums.length == 0)
    		return 0;
    	if(nums.length == 1)
    		return 1;
    	int n = nums.length, ans = 1, index = 0, sign = -1;
    	
    	for(int i = 1;i < n;i++) {
    		if(nums[i] == nums[index])
    			continue;
    		if(sign == -1) {
    			ans++;
    			sign = nums[i]>nums[index]?1:0;
    		} else {
    			if(nums[i] > nums[index] && sign == 1 || nums[i] < nums[index] && sign == 0) {
    				index = i;
    				continue;
    			}
    			
    			if((nums[i] > nums[index] && sign == 0) || (nums[i] < nums[index] && sign == 1)) {
    				ans++;
    				sign ^= 1;
    			}
    		}
    		index = i;
    	}
    	return ans;
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
	}
}
