package main;
import java.util.concurrent.ThreadLocalRandom;

class Solution {	
	public int findKthLargest(int[] nums, int k) {
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
    	int random = ThreadLocalRandom.current().nextInt(l, r+1);
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
    
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}

class Main {	
	public static void main(String[] args) throws java.lang.Exception {
		int []nums = {3,2,3,1,2,4,5,5,6};
		int k = 4;
		System.out.println(new Solution().findKthLargest(nums, k));
	}
}