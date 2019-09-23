package main;

import java.util.ArrayList;
import java.util.List;

class Solution {
	
	/*
	 * 0(lgN) method
	 * Binary Search Method
	 * passing length of array because for last element, it needs to be checked only with prev element
	 * high keeps changing - can't use high for same purposes.
	 * Min in sorted and rotated array
	 */
	
	public int findPeakElement(int[] nums) {
		int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
	}
	
	/*
	 * Find a peak element in a 2D array
	 */
	public int findPeakElement2D(int[][] nums, int m, int n) {
		return findPeakElement2DUtil(nums, m, n, n/2);
	}
	
	public int findPeakElement2DUtil(int[][] nums, int m, int n, int mid_col) {
		int max = findmax(nums, m, n, mid_col);
		if(mid_col == 0 || mid_col == n-1)
			return max;
		
		if(max > nums[max_index][mid_col-1] && max > nums[max_index][mid_col+1])
			return max;
		
		if(max < nums[max_index][mid_col-1])
			return findPeakElement2DUtil(nums, m, n, mid_col - mid_col/2);
		
		return findPeakElement2DUtil(nums, m, n, mid_col + mid_col/2);
	}
	int max_index = 0;
	public int findmax(int [][]nums, int m, int n, int col) {
		int max = Integer.MIN_VALUE;
		for(int i = 0;i < m;i++) {
			if(nums[i][col] > max) {
				max= nums[i][col];
				max_index = i;
			}
			
		}
		return max;
	}
}

public class Main {

	public static void main(String[] args) {
		int []nums = {10,20,15,2,23,90,67};
		//int []nums = {20,10,15,25,29,90,93};
		//int []nums = {1, 3, 20, 4, 1, 0};
		System.out.println(new Solution().findPeakElement(nums));
		
		int arr[][] = {{ 10, 8, 10, 10 }, 
                	   { 14, 13, 12, 11 }, 
                	   { 15, 9, 11, 21 }, 
                	   { 16, 17, 19, 20 } };
		/*int [][]arr = {{16, 3, 8, 3},
				{2, 4, 9, 15},
				{4, 2, 7, 16},
				{9, 1, 2, 17},
				{5, 6, 20, 18}};*/
		System.out.println(new Solution().findPeakElement2D(arr, 4, 4));
	}
}