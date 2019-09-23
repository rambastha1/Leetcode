package main;

// https://leetcode.com/problems/single-number-ii/discuss/43295/Detailed-explanation-and-generalization-of-the-bitwise-operation-method-for-single-numbers

class Solution {
    
	// Same thing of count array can be done using a variable
	// Space Optimized
	
	public int singleNumber(int[] nums) {
		int sum = 0, ans = 0;
		for(int i = 0;i < 32;i++) {
			sum = 0;
    		for(int num : nums) {
    			sum += (num >> i) & 1;
    		}
    		sum %= 3;
    		ans |= (sum << i);
		}
		return ans;
	}
	
	// Time 0(N) constant space 0(32)
	/*public int singleNumber(int[] nums) {
    	int []count = new int[32];
    	for(int i = 0;i < 32;i++) {
    		for(int num : nums) {
    			count[i] += (num >> i) & 1;
    		}
    		count[i] %= 3;
    	}
    	//print(count);
    	int num = 0;
    	for(int i = 0;i < 32;i++)
    		num |= (count[i] << i);
    	return num;
    }*/
    
    void print(int []count) {
    	for(int i : count)
    		System.out.print(i + " ");
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,0,1,0,1,0,99};
		System.out.println(new Solution().singleNumber(nums));
	}
}