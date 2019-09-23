package main;

class Solution {
	public int bitonic(int []nums, int target) {
		int low = 0, high = nums.length-1;
		int bitonic = bitonicpoint(nums);
		if(target > nums[bitonic])
			return -1;
		else if(target == nums[bitonic])
			return bitonic;
		else {
			int index = ascendingBsearch(nums, 0, bitonic-1, target);
			if(index != -1)
				return index;
			return descendingBsearch(nums, bitonic+1, high, target);
		}
	}
	
	int ascendingBsearch(int []nums, int low, int high, int key) {
		while(low <= high) {
			int mid = low + (high - low)/2;
			if(nums[mid] == key)
				return mid;
			else if(nums[mid] < key)
				low = mid+1;
			else 
				high = mid-1;
		}
		return -1;
	}
	
	int descendingBsearch(int []nums, int low, int high, int key) {
		while(low <= high) {
			int mid = low + (high - low)/2;
			if(nums[mid] == key)
				return mid;
			else if(nums[mid] < key)
				high = mid-1;
			else 
				low = mid+1;
		}
		return -1;
	}
	
	int bitonicpoint(int []nums) {
		int low = 0, high = nums.length-1;
		while(low <= high) {
			int mid = low + (high - low)/2;
			if(nums[mid] > nums[mid+1] && nums[mid] > nums[mid-1])
				return mid;
			else if(nums[mid] > nums[mid-1] && nums[mid] < nums[mid+1])
				low = mid+1;
			else if(nums[mid] < nums[mid-1] && nums[mid] > nums[mid+1])
				high = mid-1;
		}
		return -1;
	}
}

public class Main {
	public static void main(String[] args) {
		int []nums = {-8, 1, 2, 3, 4, 5, -2, -3};
		int target = -2;
		System.out.println(new Solution().bitonic(nums, target));
	}
}