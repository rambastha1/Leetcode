package main;

class Solution {
    /*
     * Specific to 0,1,2
     * Dutch National Flag
     */
	/*public void sortColors(int[] nums) {
		if(nums.length == 0 || nums.length == 1)
    		return;
    	
    	int l = 0,m = l, h = nums.length -1;
    	while(m <= h) {
    		switch(nums[m]) {
    			case 0 : swap(nums, l++, m++);
    				break;
    			case 1 : m++;
    				break;
    			case 2 : swap(nums, m, h--);
    				break;
    		}
    	} 
	}*/
	
	/*
     * Generic Solution
     * Actual 3 way Quick Sort
     */
	
	public void sortColors(int[] nums) {
    	if(nums.length == 0 || nums.length == 1)
    		return;
    	sortColorsutil(nums, 0, nums.length-1);
    }
    
    public void sortColorsutil(int[] nums, int l, int h) {
    	if(l >= h)
    		return;
    	int m = l +1;
    	int lt = l, ht = h;
    	int p = nums[l];
    	while(m <= ht) {
    		if(nums[m] < p)
    			swap(nums, lt++, m++);
    		else if(nums[m] > p)
    			swap(nums, m, ht--);
    		else
    			m++;
    	}
    	sortColorsutil(nums, l, lt-1);
    	sortColorsutil(nums, ht+1, h);    	
    }
    
    public void swap(int []nums, int i, int j) {
    	int temp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = temp;
    }
    
    public void print(int []nums) {
    	for(int i : nums)
    		System.out.print(i + " ");
    	System.out.println();
    }
}

public class Main {

	public static void main(String[] args) {
		//int []nums = {2,0,2,1,1,0};
		//int []nums = {1, 0, 2};
		//int []nums = {1,1,2,0,1,1,1,2};
		int []nums = {4, 39, 54, 14, 31, 89, 44, 34, 59, 64, 64, 11, 41};
		Solution s = new Solution();
		s.print(nums);
		s.sortColors(nums);
		s.print(nums);
	}
}