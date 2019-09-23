package main;

class Solution {
    public int rob(int[] nums) {
    	if(nums == null || nums.length == 0)
    		return 0;
    	if(nums.length == 1)
    		return nums[0];
    	return Math.max(robUtil(nums, 0, nums.length-2), robUtil(nums, 1, nums.length-1));
    }
    
    public int robUtil(int[] nums, int low, int high) {
    	if(high == low)
    		return nums[low];
    	if(high - low == 1)
    		return Math.max(nums[low], nums[high]);
    	int []res = new int[nums.length];
    	res[low] = nums[low];
    	res[low+1] = Math.max(nums[low], nums[low+1]);
    	for(int i = low+2;i <= high;i++) {
    		if(nums[i] + res[i-2] >= res[i-1])
    			res[i] = nums[i] + res[i-2];
    		else 
    			res[i] = res[i-1];
    	}
    	return res[high];
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {2,7,9,3,1};
		System.out.println(new Solution().rob(nums));
	}
}