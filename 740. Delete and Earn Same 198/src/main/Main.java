package main;

class Solution {
    public int deleteAndEarn(int[] nums) {
    	if(nums == null || nums.length == 0)
    		return 0;
    	int max = Integer.MIN_VALUE;
    	for(int num : nums)
    		max = Math.max(max, num);
    	int []dp = new int[max+1], count = new int[max+1];
    	for(int num : nums)
    		count[num]++;
    	
    	dp[1] = count[1];
    	for(int i = 2;i < count.length;i++) {
    		if(count[i] == 0)
    			dp[i] = dp[i-1];
    		else
    			dp[i] = Math.max(i*count[i] + dp[i-2], dp[i-1]);
    	}
    	return dp[max];
    }
}

public class Main {
	public static void main(String[] args) {
		//int []nums = {2, 3, 4};
		int []nums = {1,1,1,2,4,5,5,5,6};
		//int []nums = {2, 2, 3, 3, 3, 4};
		System.out.println(new Solution().deleteAndEarn(nums));
	}
}