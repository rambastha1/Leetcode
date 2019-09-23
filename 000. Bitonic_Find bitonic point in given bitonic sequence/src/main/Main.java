package main;

/*
 * https://www.geeksforgeeks.org/find-bitonic-point-given-bitonic-sequence/
 * 
 * It is same as Find the maximum element in an array which is first increasing and then decreasing
 * https://www.geeksforgeeks.org/find-the-maximum-element-in-an-array-which-is-first-increasing-and
 * -then-decreasing/
 */

class Solution {
	public int bitonic(int []nums) {
		int low = 0, high = nums.length-1;
		while(low <= high) {
			int mid = low + (high-low)/2;
			if(nums[mid] > nums[mid+1] && nums[mid] > nums[mid-1])
				return mid;
			else if(nums[mid] > nums[mid-1] && nums[mid] < nums[mid+1])
				low = mid;
			else if(nums[mid] < nums[mid-1] && nums[mid] > nums[mid+1])
				high = mid;
		}
		return -1;
	}
}

public class Main {
	public static void main(String[] args) {
		int []nums = {6, 7, 8, 11, 9, 5, 2, 1};
		//int []nums = {-3, -2, 4, 6, 10, 8, 7, 1};
		System.out.println(new Solution().bitonic(nums));
	}
}