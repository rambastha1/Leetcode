package main;

import java.util.Random;

/* http://www.zrzahid.com/wiggle-sort/
 * https://leetcode.com/problems/wiggle-sort-ii/discuss/77682/Step-by-step-explanation-of-index-mapping-in-Java
 * 
 */

class Solution {
	public void wiggleSort(int[] nums) {
    	int n = nums.length;
    	int median = kthsmallest(nums, (n+1)/2);
    	int l = 0, m = 0, r = n-1;
    	while(m <= r) {
    		if(nums[get(nums, m)] > median)
    			swap(nums, get(nums, m++), get(nums, l++));
    		else if(nums[get(nums, m)] < median)
    			swap(nums, get(nums, m), get(nums, r--));
    		else
    			m++;
    	}
    	print(nums);
    }
    
    private int get(int []nums, int i) {
    	return (1 + 2*i) % (nums.length | 1);
    }
    
    private void print(int []nums) {
    	for(int i : nums)
    		System.out.print(i + " ");
    	System.out.println();
    }
     
    
    public int kthsmallest(int[] nums, int k) {
		int l = 0, r= nums.length-1;
		while(l <= r) {
    		int mid = partition(nums, l, r);
    		if(mid == k-1)
    			return nums[mid];
    		else if(mid > k-1)
    			r = mid - 1;
    		else
    			l = mid + 1;
    	}
    	return -1;
    }	
    
	int partition(int []nums, int l, int r) {
    	int random = java.util.concurrent.ThreadLocalRandom.current().nextInt(l, r+1);
    	swap(nums, random, r);
    	int pivot = nums[r];
    	int i = l-1;
    	for(int j = l;j < r;j++) {
    		if(nums[j] >= pivot)
    			swap(nums, ++i, j);
    	}
    	swap(nums, ++i, r);
    	return i;
    }
    
    private void swap(int []nums, int a, int b) {
    	int temp = nums[a];
    	nums[a] = nums[b];
    	nums[b] = temp;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []nums = {1, 5, 1, 1, 6, 4};
		//int []nums = {1, 3, 2, 2, 3, 1};
		int []nums = {1,1,2,2,2,1};
		new Solution().wiggleSort(nums);
	}
}
