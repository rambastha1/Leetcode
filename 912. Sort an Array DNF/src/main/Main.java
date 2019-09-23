package main;

import java.util.concurrent.ThreadLocalRandom;

class Solution {
    public int[] sortArray(int[] nums) {
    	if(nums == null || nums.length <= 1)
    		return nums;
    	quicksort(nums, 0, nums.length-1);
    	return nums;
    }
    
    //nums[l] is placed at its correct position
    //just like in normal quick sort
    public void quicksort(int []nums, int low, int high) {
    	if(low >= high)
    		return;
    	int l = low, h = high-1;
    	int random = ThreadLocalRandom.current().nextInt(l, high);
    	swap(nums, random, high);
    	int pivot = nums[high];
    	while(l <= h) {
    		if(nums[l] < pivot)
    			l++;
    		else 
    			swap(nums, l, h--);
    	}
    	swap(nums, l, high);
    	quicksort(nums, low, l-1);
    	quicksort(nums, l+1, high);
    }
    
    public void swap(int []nums, int a, int b) {
    	int temp = nums[a];
    	nums[a] = nums[b];
    	nums[b] = temp;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []nums = {5,2,3,1};
		//int []nums1 = {1,2,0,0,0,0,2,2,1,2,0};
		//int []nums = {4, 39, 54, 14, 31, 89, 44, 34, 59, 64, 64, 11, 41};
		int []nums = {4, 9, 4, 4, 1, 9, 4, 4, 9, 4, 4, 1, 4};
		int []res = new Solution().sortArray(nums);
		for(int i : res)
			System.out.print(i + " ");
		System.out.println();
	}
}