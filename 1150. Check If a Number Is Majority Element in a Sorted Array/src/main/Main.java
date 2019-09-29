package main;

class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
    	int count = 0;
    	for(int num : nums) {
    		count += num == target?1:0;
    	}
    	return count > nums.length/2;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {2,4,5,5,5,5,5,6,6};
		int target = 5;
		System.out.println(new Solution().isMajorityElement(nums, target));
	}
}