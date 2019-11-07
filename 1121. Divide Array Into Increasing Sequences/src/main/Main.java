package main;

/* Given a non-decreasing array of positive integers nums and an integer K, find out if this array can be divided into one 
 * or more disjoint increasing subsequences of length at least K.

 

Example 1:

Input: nums = [1,2,2,3,3,4,4], K = 3
Output: true
Explanation: 
The array can be divided into the two subsequences [1,2,3,4] and [2,3,4] with lengths at least 3 each.
Example 2:

Input: nums = [5,6,6,7,8], K = 3
Output: false
Explanation: 
There is no way to divide the array using the conditions required.
 

Note:

1 <= nums.length <= 10^5
1 <= K <= nums.length
1 <= nums[i] <= 10^5
 * 
 */

class Solution {
	/* find the maximum frequency of a number
	 * number of frequency means number of groups
	 * if each group is of size K, total size of all groups = K* max frequency
	 * length of array should be greater or equal to this size meaning we have enough elements to make k groups
	 * https://leetcode.com/problems/divide-array-into-increasing-sequences/discuss/334111/JavaC%2B%2BPython-One-Pass-O(1)-Space-and-Prove
	 */
    public boolean canDivideIntoSubsequences(int[] nums, int K) {
    	if(nums == null)
    		return false;
    	int freq = 1, max = 1, n = nums.length;
    	for(int i = 1;i < n;i++) {
    		freq = nums[i-1] < nums[i]?1:freq + 1;
    		max = Math.max(max, freq);
    	}
    	return max*K <= n;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int []nums = {1,2,2,3,3,4,4};
		int K = 3;*/
		int []nums = {5,6,6,7,8};
		int K = 3;
		System.out.println(new Solution().canDivideIntoSubsequences(nums, K));
	}
}