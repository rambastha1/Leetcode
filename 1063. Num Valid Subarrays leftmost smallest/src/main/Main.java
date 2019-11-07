package main;

import java.util.Stack;

/* Given an array A of integers, return the number of non-empty continuous subarrays that satisfy the following condition:

The leftmost element of the subarray is not larger than other elements in the subarray.

 

Example 1:

Input: [1,4,2,5,3]
Output: 11
Explanation: There are 11 valid subarrays: [1],[4],[2],[5],[3],[1,4],[2,5],[1,4,2],[2,5,3],[1,4,2,5],[1,4,2,5,3].
Example 2:

Input: [3,2,1]
Output: 3
Explanation: The 3 valid subarrays are: [3],[2],[1].
Example 3:

Input: [2,2,2]
Output: 6
Explanation: There are 6 valid subarrays: [2],[2],[2],[2,2],[2,2],[2,2,2].
 

Note:

1 <= A.length <= 50000
0 <= A[i] <= 100000
 * 
 */

class Solution {
	// Time 0(N) Space 0(N)
	/* any time nums[top] > nums[i] means subarray starting from top won't work
	 * thus pop it.. add number of subarrays from top to i i.e i-top to answer
	 * In the end whatever remains in stack will add n - stack.pop() to answer 
	 * as that will be number of valid subarrays
	 */
	public int validSubarrays(int[] nums) {
    	int n = nums.length;
    	Stack<Integer> stack = new Stack<>();
    	int count = 0;
    	
    	for(int i = 0;i < n;i++) {
    		while(!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
    			int index = stack.pop();
    			count += i - index;
    		}
    		stack.push(i);
    	}
    	
    	while(!stack.isEmpty()) {
    		count += n - stack.pop();
    	}
    	return count;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,4,2,5,3};
		System.out.println(new Solution().validSubarrays(nums));
	}
}