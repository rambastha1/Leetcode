package main;

class Solution {
    public int rob(int[] nums) {
    	if(nums == null || nums.length == 0)
    		return 0;
    	if(nums.length == 1)
    		return nums[0];
    	if(nums.length == 2)
    		return Math.max(nums[0], nums[1]);
    	int len = nums.length;
    	int []res = new int[len];
    	res[0] = nums[0];
    	res[1] = Math.max(nums[0], nums[1]);
    	for(int i = 2;i < len;i++) {
    		if(nums[i] + res[i-2] > res[i-1])
    			res[i] = nums[i] + res[i-2];
    		else
    			res[i] = res[i-1];
    	}
    	return res[len-1];
    }
}

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
