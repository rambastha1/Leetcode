package main;
/* sum will exist from maximum to sum of array
 * Time 0(NlgN)
 * https://leetcode.com/problems/split-array-largest-sum/discuss/89817/Clear-Explanation%3A-8ms-Binary-Search-Java
 */
class Solution {
    public int splitArray(int[] nums, int m) {
    	int n = nums.length;
    	int sum = 0;
    	if(m > n)
    		return sum;
    	int max = Integer.MIN_VALUE;
    	for(int a : nums) {
    		sum += a;
    		max = Math.max(max, a);
    	}
    	// it is minimum of maximum sum to divide an array in m partitions
    	int l = max, r = sum;
    	while(l <= r) {
    		int mid = l + (r-l)/2;
    		int curr = 0, partition = 0;
    		for(int a : nums) {
    			curr += a;
    			if(curr > mid) {
    				partition++;
    				// this element will count in next partition
    				curr = a;
    			}
    		}
    		// if partition is more than required -> increase sum
    		// to get maximum sum -> increase when equal
    		if(partition >= m)
    			l = mid + 1;
    		else
    			r = mid - 1;
    	}
    	return l;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {7,2,5,10,8};
		int m = 2;
		System.out.println(new Solution().splitArray(nums, m));
	}
}
