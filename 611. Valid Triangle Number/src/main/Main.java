package main;

import java.util.Arrays;
// https://leetcode.com/articles/valid-triangle-number/
class Solution {
	
	// Time 0(N^2)
	public int triangleNumber(int[] nums) {
		if(nums == null || nums.length < 3)
    		return 0;
    	int n = nums.length, ans = 0;
    	Arrays.sort(nums);
    	/* It is traversed from last as traversing from beginning is same as 
    	 * find first k such that nums[i] + nums[l] < nums[k] 
    	 * same as second solution
    	 * traversing from last eliminates such problem already have fixed k
    	 */
    	for(int i = n-1;i >= 2;i--) {
    		int l = 0, r = i-1;
    		while(l < r) {
    			if(nums[l] + nums[r] > nums[i]) {
    				ans += r-l;
    				r--;
    			} else l++;
    		}
    	}
    	return ans;
	}
	
	
    public int triangleNumber1(int[] nums) {
    	if(nums == null || nums.length < 3)
    		return 0;
    	int n = nums.length, ans = 0;
    	Arrays.sort(nums);
    	/* find index k for which nums[i] + nums[j] >= nums[k]
    	 * At this point shift j to j+1 no need to reinitialize k = j+1 
    	 * as this this first index for which inequality fails
    	 * when fails add length to answer 
    	 */
    	// Time 0(N^2) Space 0(NlgN)
    	for(int i = 0;i < n-2;i++) {
    		int k = i+2;
    		for(int j = i+1;j < n-1 && nums[i] != 0;j++) {
    			while(k < n && nums[i] + nums[j] > nums[k])
    				k++;
    			ans = k-j-1;
    		}
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {2,2,3,4};
		System.out.println(new Solution().triangleNumber(nums));
	}
}