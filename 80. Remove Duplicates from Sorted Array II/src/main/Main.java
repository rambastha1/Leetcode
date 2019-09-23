package main;

class Solution {
    public int removeDuplicates(int[] nums) {
    	int i = 0;
    	for(int n : nums) {
    		// i<2 will handle cases for nums[i-2] when index < 0 
    		if(i < 2 || n > nums[i-2])
    			nums[i++] = n;
    	}
    	return i;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []nums = {1,1,1,2,2,3};
		//int []nums = {0,0,1,1,1,1,2,3,3};
		int []nums = {1,1,1};
		System.out.println(new Solution().removeDuplicates(nums));
	}
}
