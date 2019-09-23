package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
    	List<Integer> res = new ArrayList<>();
    	if(nums == null || nums.length == 0)
    		return res;
    	if(nums.length == 1) {
    		res.add(nums[0]);
    		return res;
    	}
    	int n = nums.length, num = Integer.MIN_VALUE, index = 0;
    	int []dp = new int[n];
    	Arrays.fill(dp, 1);
    	Arrays.sort(nums);
    	for(int i = 1;i < n;i++) {
    		for(int j = i-1;j >= 0;j--) {
    			if(nums[i]%nums[j] == 0) {
    				dp[i] = Math.max(dp[i], 1+dp[j]);
    				if(dp[i] > num) {
    					num = dp[i];
    					index = i;
    				}
    			}
    		}
    	}
    	num = nums[index];
    	res.add(0, num);
    	for(int i = index-1;i >= 0;i--) {
    		if(num%nums[i] == 0 && dp[index] == dp[i] + 1) {
    			res.add(0, nums[i]);
    			index = i;
    			num = nums[i];
    		}
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {2,3,8,9,27};
		System.out.println(new Solution().largestDivisibleSubset(nums));
	}
}
