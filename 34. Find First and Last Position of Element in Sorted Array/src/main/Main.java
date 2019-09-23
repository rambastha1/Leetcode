package main;

class Solution {
    public int[] searchRange(int[] nums, int target) {
    	
    	int []arr = new int[2];
    	arr[0] = -1;
    	arr[1] = -1;
    	
    	if(nums.length == 0)
    		return arr;
    	if(nums.length == 1) {
    		if(nums[0] == target) {
    			arr[0] = arr[1] = 0;
    		}
    		return arr;
    	}
    	
    	int l = -1, r = nums.length -1;
    	while(r - l > 1) {
    		int m = l + (r-l)/2;
    		if(nums[m] < target)
    			l = m;
    		else
    			r = m;
    	}
    	if(target == nums[r])
    		arr[0] = r;
    	l = 0; 
    	r = nums.length;
    	
    	while(r-l>1) {
    		int m = l + (r-l)/2;
    		if(nums[m] <= target)
    			l = m;
    		else
    			r = m;
    	}
    	if(nums[l] == target)
    		arr[1] = l;
    	return arr;
    }
}

public class Main {

	public static void main(String[] args) {
		//int []nums = {5,7,7,8,8,10};
		int []nums = {2,2};
		int target = 2;
		int []res = new Solution().searchRange(nums, target);
		System.out.println(res[0] + " " + res[1]);
	}
}