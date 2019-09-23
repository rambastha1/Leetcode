package main;

/* https://leetcode.com/articles/next-permutation/
 * Permutations cannot be formed if number in descending order as it is already largest
 * shift right to form first index where A[index-1] > A[index]
 * find smallest j on [index+1, n] such that A[j] > A[index]
 * swap and reverse [index+1, n] as array in [index+1, n] is descending order, we need to find smallest one 
 */

class Solution {
    public void nextPermutation(int[] nums) {
    	if(nums.length == 1)
    		return;
    	int n = nums.length;
    	int right = n - 2;
    	while(right >= 0 && nums[right] >= nums[right+1])
    		right--;
    	if(right >= 0) {
    		int i = n-1;
    		while(i >= 0 && nums[i] <= nums[right])
    			i--;
    		swap(nums, i, right);
    	}
    	reverse(nums, right+1, n-1);
    	print(nums);
    }
    
    void swap(int []nums, int left, int right) {
    	int temp = nums[left];
    	nums[left] = nums[right];
    	nums[right] = temp;
    }
    
    void reverse(int []nums, int left, int right) {
    	while(left < right) {
    		swap(nums, left++, right--);
    	}
    }
    
    void print(int []nums) {
    	for(int i : nums)
    		System.out.print(i + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,2,3};
		new Solution().nextPermutation(nums);
	}
}
