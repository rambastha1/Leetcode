package main;

class Solution {
	public int findMaxConsecutiveOnes(int[] nums) {
    	if(nums.length == 0)
    		return 0;
    	int maxlen = Integer.MIN_VALUE;
    	
    	int start = 0, end = 0;
    	while(end < nums.length) {
    		if(nums[end] == 1) {
    			end++;
    		} else {
    			maxlen = Math.max(maxlen, end-start);
    			end++;
    			start = end;
    		}
    	}
        maxlen = Math.max(maxlen, end-start);
        return maxlen;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,1,0,1,1,1};
		System.out.println(new Solution().findMaxConsecutiveOnes(nums));
	}
}