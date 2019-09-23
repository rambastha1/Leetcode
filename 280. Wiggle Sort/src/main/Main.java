package main;

/*
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 */

class Solution {
    public void wiggleSort(int[] nums) {
    	boolean flag = true;
    	for(int i = 0;i < nums.length-1;i++) {
    		if(flag && nums[i] > nums[i+1])
    			swap(nums, i, i+1);
    		else if(!flag && nums[i] < nums[i+1])
    			swap(nums, i, i+1);
    		flag = !flag;
    	}
    }
    
    void swap(int []nums, int a, int b) {
    	int temp = nums[a];
    	nums[a] = nums[b];
    	nums[b] = temp;
    }
}


public class Main {
	public static void main(String[] args) {
		int []nums = {3,5,2,1,6,4};
		for(int i : nums)
			System.out.print(i + " ");
		System.out.println();
		new Solution().wiggleSort(nums);
		for(int i : nums)
			System.out.print(i + " ");
		System.out.println();
	}
}