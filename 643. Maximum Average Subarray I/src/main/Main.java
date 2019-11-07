package main;

class Solution {
	public double findMaxAverage(int[] nums, int k) {
        long sum = 0;
        // sum of first k elements
        for (int i = 0; i < k; i++) 
        	sum += nums[i];
        long max = sum;
        
        // sliding sum of k elements find max sum
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            max = Math.max(max, sum);
        }
        
        return max / 1.0 / k;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,12,-5,-6,50,3};
		int k = 4;
		System.out.println(new Solution().findMaxAverage(nums, k));
	}
}