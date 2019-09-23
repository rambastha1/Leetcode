package main;


/*
 * For each bit position 1-32 in a 32-bit integer, we count the number of integers in the array 
 * which have that bit set. 
 * Then, if there are n integers in the array and k of them have a particular bit set and (n-k) do not, 
 * then that bit contributes k*(n-k) hamming distance to the total.
 * 
 * Every Number has n-k hamming distance
 */

class Solution {
	 
    public int totalHammingDistance(int[] nums) {
    	int sum = 0;
    	if(nums == null || nums.length <= 1)
    		return 0;
    	for(int i = 0;i < 32;i++) {
    		int bitset = 0;
    		for(int j : nums)
    			bitset += (j >> i) & 1;
    		sum += bitset * (nums.length - bitset);
    	}
    	return sum;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {4, 14, 2};
		System.out.println(new Solution().totalHammingDistance(nums));
	}
}