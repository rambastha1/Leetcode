package main;

class Solution {
    public int jump(int[] nums) {
    	
    	/*
    	 * the farthest index you can reach with the any combination of steps in [i, end]
    	 * curEnd - the end index of a single step you take
    	 * 
    	 * update the farthest with the max between the current farthest and 
    	 * i+nums[i] - the index you end up in if you take the maximum amount of steps at this i
		 * if current i is the "end", then that means you have taken one step, 
		 * so we increment our result by 1. We also need a new "end" for our next step, 
		 * so we assign the current "farthest" to end
    	 */
    	
    	int result = 0, curEnd = 0, curFarthest = 0;
    	for (int i = 0; i < nums.length - 1; i++) {
    		curFarthest = Math.max(curFarthest, i + nums[i]);
    		if (i == curEnd) {
    			result++;
    			curEnd = curFarthest;
    		}
    	}
    	return result;
    }
}

public class Main {

	public static void main(String[] args) {
		//int []nums = {2,3,1,1,4};
		int []nums = {2,0,1,0,4};
		System.out.println(new Solution().jump(nums));
	}
}