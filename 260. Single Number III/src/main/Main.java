package main;

/*
 * Let a and b be the two unique numbers
 * XORing all numbers gets you (a xor b)
 * (a xor b) must be non-zero otherwise they are equal
 * If bit_i in (a xor b) is 1, bit_i at a and b are different.
 * Find bit_i using the low bit formula m & -m
 * Partition the numbers into two groups: one group with bit_i == 1 and the other group with bit_i == 0.
 * a is in one group and b is in the other.
 * a is the only single number in its group.
 * b is also the only single number in its group.
 * XORing all numbers in a's group to get a
 * XORing all numbers in b's group to get b
 * Alternatively, XOR (a xor b) with a gets you b.
 */

class Solution {
	//Time 0(N) Space 0(1)
    public int[] singleNumber(int[] nums) {
    	int []res = new int[2];
    	if(nums == null || nums.length == 0)
    		return res;
    	int xor = 0;
    	for(int i = 0;i < nums.length;i++)
    		xor ^= nums[i];
    	
    	xor &= (-xor);
    	for(int num : nums) {
    		if((num & xor) == 0)
    			res[0] ^= num;
    		else
    			res[1] ^= num;
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,2,1,3,2,5};
		int []res = new Solution().singleNumber(nums);
		System.out.println(res[0] + " " + res[1]);
	}
}