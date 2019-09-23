package main;

class Solution {
    public int[] productExceptSelf(int[] nums) {
    	double EPS = 1e-9;
    	double sum = 0;
    	for(int i = 0;i < nums.length;i++)
    		sum += Math.log10(nums[i]);
    	
    	for(int i = 0;i < nums.length;i++) {
    		nums[i] = (int)(EPS + Math.pow(10.00, sum - Math.log10(nums[i])));
    	}
    	return nums;
    }
}

public class Main {

	public static void main(String[] args) {
		//int []nums = {1,0};
		int []nums = {10, 3, 5, 6, 2};
		nums = new Solution().productExceptSelf(nums);
		for(int i : nums)
			System.out.print(i + " ");
		System.out.println();
	}
}