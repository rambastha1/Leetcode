package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
	
	/*
	 * Time 0(N^2)
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		int target =0;
		List<List<Integer>> res = new ArrayList();
		Arrays.sort(nums);   //Time: O(nlogn)
		for (int i=0; i<nums.length-2; i++) {  //Time: O(n^2)
			if (i>0 && nums[i]==nums[i-1] ) 
				continue;   //skip dup
			twoSum(nums, i+1, nums.length-1, target-nums[i], res);
		}  
		return res;
	}
	
	private void twoSum(int[] nums, int left, int right, int target, List<List<Integer>> res ) {
		int tmp = nums[left-1], sum=0;
		int small=0, large=0;
		while (left < right) {
			sum = nums[left]+nums[right];
			small = nums[left];
			large = nums[right];
			if (sum==target) {
				/*
				 * This line is amazing. it adds nums[i], nums[left] and nums[right] 
				 * plus increment left and decrement right as well
				 */
				res.add(Arrays.asList(tmp, nums[left++], nums[right--]));
				//This is done to avoid duplicate entries as values are same
				while (left < right && nums[left]==small) 
					left++;
				//This is done to avoid duplicate entries as values are same
				while (left < right && nums[right]==large) 
					right--;
			} else if (sum < target) {
				left ++; 
			} else {
				right--;
			}
		}
		return;
	}
	
}

public class Main {
	public static void main(String[] args) {
		int []nums = {-1, 0, 1, 2, -1, -4};
		System.out.println(new Solution().threeSum(nums));
	}
}