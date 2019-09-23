package main;

import java.util.Stack;

class Solution {
    public int maxWidthRamp(int[] A) {
    	if(A == null || A.length <= 1)
    		return 0;
    	Stack<Integer> stack = new Stack<>();
    	// stack will have min element at the top
    	for(int i = 0;i < A.length;i++) {
    		if(stack.isEmpty() || A[stack.peek()] > A[i])
    			stack.push(i);
    	}
    	//the rightmost element which is equal or greater than min(stack.peek()) will have maximum width 
    	int ans = 0;
    	// thus i > ans
    	for(int i = A.length - 1;i > ans;i--) {
    		while(!stack.isEmpty() && A[stack.peek()] <= A[i]) {
    			ans = Math.max(ans, i - stack.pop());
    		}
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []A = {6,0,8,2,1,5};
		//int []A = {9,8,1,0,1,9,4,0,4,1};
		//int []A = {2,2,1};
		//int []A = {2,3,1};
		int []A = {3,4,2,1};
		System.out.println(new Solution().maxWidthRamp(A));
	}
}
