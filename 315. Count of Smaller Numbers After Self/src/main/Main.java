package main;
import java.util.*;

/*
 * This problem is extension of count number of inversions
 */

class Solution {	
	public List<Integer> countSmaller(int[] nums) {
    	List<Integer> res = new ArrayList<>();
    	if(nums == null || nums.length == 0)
    		return res;
    	if(nums.length == 1) {
    		res.add(0);
    		return res;
    	}
    	
    	// Min is used for negative numbers
    	int min = Integer.MAX_VALUE;
    	int max = Integer.MIN_VALUE;
    	
    	for(int i = 0;i < nums.length;i++)
    		min = Math.min(min, nums[i]);
    	
    	int []nums2 = new int[nums.length];
    	for(int i = 0;i < nums2.length;i++) {
    		nums2[i] = nums[i] - min + 1;
    		max = Math.max(max, nums2[i]);
    	}
    	
    	int []bit = new int[max+1];
    	for(int i = nums2.length-1;i >= 0;i--) {
    		res.add(0, sum(bit, nums2[i]-1));
    		update(bit, nums2[i]);
    	}
    	return res;
    }
	
	void update(int []bit, int index) {
		while(index < bit.length) {
			bit[index]++;;
			index += index & (-index);
		}
	}
	
	int sum(int []bit, int index) {
		int sum = 0;
		while(index > 0) {
			sum += bit[index];
			index -= index & (-index);
		}
		return sum;
	}
}

public class Main {
	public static void main(String[] args) {
		int []nums = {5,2,6,1};
		//int []nums = {12, 1, 2, 3, 0, 11, 4};
		System.out.println(new Solution().countSmaller(nums));
	}
}