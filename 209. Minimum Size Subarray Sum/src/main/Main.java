package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSubArrayLen(int s, int[] nums) {
    	if(nums == null || nums.length == 0)
    		return 0;
    	int sum = 0, ans = Integer.MAX_VALUE, start = 0;
    	
    	for(int end = 0;end < nums.length;end++) {
    		sum += nums[end];
    		while(sum >= s) {
    			ans = Math.min(ans, end-start+1);
    			sum -= nums[start++];
    		}
    	}
    	return ans==Integer.MAX_VALUE?0:ans;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int s = 7;
		int []nums = {2,3,1,2,4,3};*/

		int s = 11;
		int []nums = {1,2,3,4,5};
		System.out.println(new Solution().minSubArrayLen(s, nums));
	}
}
