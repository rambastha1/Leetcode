package main;

class Solution {
	
	/*
	 * Time : 0(N)
	 * Space: 0(N)
	 */
    /*public boolean increasingTriplet(int[] nums) {
    	int []smaller = new int[nums.length];
    	int []greater = new int[nums.length];
    	int n = nums.length, min = 0, max = nums.length-1;
    	smaller[0] = -1;
    	for(int i = 1;i < n;i++) {
    		if(nums[i] < nums[min]) {
    			smaller[i] = -1;
    			min = i;
    		} else
    			smaller[i] = min;
    	}
    	
    	for(int i = n-1;i >= 0;i--) {
    		if(nums[i] > max) {
    			greater[i] = -1;
    			max = i;
    		} else
    			greater[i] = max;
    	}
    	
    	for(int i = 0;i < n;i++) {
    		if(smaller[i] != -1 && greater[i] != -1) {
    			System.out.println(nums[smaller[i]] + " - " + nums[i] + " - " + nums[greater[i]]);
    			return true;
    		}
    	}
    	return false;
    }*/
	/*
	 * Time: 0(N) Constant Space
	 * It doesn't mean the final value of small and big is in the target sequence. 
	 * The idea here is that as long as big is updated, there must be a smaller number prior to big 
	 * since big can only be updated after small is updated. 
	 * Then we know for sure such a sequence exists if we find a number larger than big.
	 * 
	 * start with two largest values, as soon as we find a number bigger than both,
	 * while both have been updated, return true.
	 * 
	 */
	public boolean increasingTriplet(int[] nums) {
		int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
		for(int num : nums) {
			// update small if n is smaller than both
			if(num <= small)
				small = num;
			// update big only if greater than small but smaller than big
			else if(num <= big)
				big = num;
			else
				return true; // return if you find a number bigger than both
		}
		return false;
	}
}

public class Main {

	public static void main(String[] args) {
		int []nums = {12, 11, 10, 5, 6, 2, 30};
		System.out.println(new Solution().increasingTriplet(nums));
	}
}