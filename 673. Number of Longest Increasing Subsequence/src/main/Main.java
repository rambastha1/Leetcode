package main;
import java.util.Arrays;

// https://leetcode.com/problems/number-of-longest-increasing-subsequence/discuss/107293/JavaC%2B%2B-Simple-dp-solution-with-explanation
class Solution {
	// Time 0(N^2) Space 0(N)
    public int findNumberOfLIS(int[] nums) {
    	if(nums == null || nums.length == 0)
    		return 0;
    	if(nums.length == 1)
    		return 1;
    	int n = nums.length;
    	 //count[i] = number of longest ending in nums[i]
    	int []LIS = new int[n], count = new int[n];
    	Arrays.fill(LIS, 1);
    	Arrays.fill(count, 1);
    	int maxlen = 0, ans = 0;
    	
    	for(int i = 0;i < n;i++) {
    		for(int j = i-1;j >= 0;j--) {
    			if(nums[i] > nums[j]) {
    				if(LIS[i] < LIS[j] + 1) {
    					LIS[i] = LIS[j] + 1;
    					count[i] = count[j];
    				} else if (LIS[i] == LIS[j] + 1)
    					count[i] += count[j];
    			}
    		}
    		
    		if(LIS[i] == maxlen)
    			ans += count[i];
    		if(maxlen < LIS[i]) {
    			maxlen = LIS[i];
    			ans = count[i];
    		}
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []nums = {1,3,5,4,7};
		int []nums = {1,2,4,3,5,4,7,2};
		System.out.println(new Solution().findNumberOfLIS(nums));
	}
}
