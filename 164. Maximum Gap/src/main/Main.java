package main;

import java.util.Arrays;

/* trivial sort and compare adjacent
 * 
 * bucket sort
 * https://leetcode.com/articles/maximum-gap/
 * https://leetcode.com/problems/maximum-gap/discuss/50643/bucket-sort-JAVA-solution-with-explanation-O(N)-time-and-space
 * we choose buckets based on gaps
 * 
 * We put n-2 elements in n-1 buckets. That's the key. At least one bucket is empty, so the max gap can't come from a single bucket. 
 * It can only come from two adjacent buckets, or one bucket and (max or min).
 * 
 * If we set the bucket size clever(relatively small), we can ensure that the max gap cannot be in same bucket. 
 * In worst case each successive numbers have same gap. For example, we have 1, 3, 5 the gap and max gap is (5 - 1) / 2. 
 * Largest - Smallest, two gaps.
 * Based on this, we only need to compare max number in this bucket and min number in next bucket to get the relatively 
 * large gap and find out which two bucket have the largest gap.
 * Suppose there are N elements in the array, the min value is min and the max value is max. 
 * Then the maximum gap will be no smaller than ceiling[(max - min ) / (N - 1)].
 * 
 * 
 */
class Solution {
	// 0(n)
    public int maximumGap(int[] nums) {
    	
    	int n = nums.length;
    	if(n < 2)
            return 0;
        if(n == 2)
            return Math.abs(nums[0] - nums[1]);
        
    	int max = nums[0], min = nums[0];
    	
    	for(int i : nums) {
    		max = Math.max(max, i);
    		min = Math.min(min, i);
    	}
    	// the minimum possible gap, ceiling of the integer division
    	int gap = (int)Math.ceil((double)(max-min)/(n-1));
    	int []minbucket = new int[n-1];
    	Arrays.fill(minbucket, Integer.MAX_VALUE);
    	int []maxbucket = new int[n-1];
    	Arrays.fill(maxbucket, Integer.MIN_VALUE);
    	
    	for(int num : nums) {
    		if(num == min || num == max)
    			continue;
    		int index = (num - min)/gap;
    		minbucket[index] = Math.min(minbucket[index], num);
    		maxbucket[index] = Math.max(maxbucket[index], num);
     	}
    	
    	int prev = min;
    	int ans = 0;
    	for(int i = 0;i < n-1;i++) {
    		// empty bucket
    		if(minbucket[i] == Integer.MAX_VALUE || maxbucket[i] == Integer.MIN_VALUE)
    			continue;
    		ans = Math.max(ans, minbucket[i] - prev);
    		prev = maxbucket[i];
    	}
    	// update the final max value gap
    	ans = Math.max(ans, max - prev);
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,1,1,1,5,5,5,5};
		System.out.println(new Solution().maximumGap(nums));
	}
}
