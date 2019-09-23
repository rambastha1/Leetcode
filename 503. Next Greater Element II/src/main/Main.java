package main;

import java.util.Arrays;
import java.util.Stack;

/*
 * The first typical way to solve circular array problems is to extend the original array to twice length, 
 * 2nd half has the same element as first half. Then everything become simple
 * 
 * The only difference here is that we use stack to keep the indexes of the decreasing subsequence
 */

class Solution {
    public int[] nextGreaterElements(int[] nums) {
    	/*if(nums == null || nums.length == 0)
    		return new int[0];
    	if(nums.length == 1) {
    		int []res = {-1};
    		return res;
    	}*/
    	int n = nums.length;
    	int []res = new int[n];
    	/*
    	 * Fill res with -1 so that for max element where no greate element exist, element should be -1
    	 * Initialize with 0, output of that index will be 0
    	 */
    	Arrays.fill(res, -1);
    	Stack<Integer> stack = new Stack<>();
    	for(int i = 0;i < n*2;i++) {
    		int num = nums[i%n];
    		while(!stack.isEmpty() && nums[stack.peek()] < num) {
    			int index = stack.pop();
    			res[index%n] = num;
    		}
    		if(i < n)
    			stack.push(i);
    	}
    	return res;    	
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,2,3,4,3};
		//int []nums = {5,4,3,2,1};
		//int []nums = {100,1,11,1,120,111,123,1,-1,-100};

		int []res = new Solution().nextGreaterElements(nums);
		for(int i : res)
			System.out.print(i + " ");
		System.out.println();
	}
}