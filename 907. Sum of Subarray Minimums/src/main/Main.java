package main;

import java.util.Stack;

// https://leetcode.com/problems/sum-of-subarray-minimums/discuss/178876/stack-solution-with-very-detailed-explanation-step-by-step
/* can do using MCM approach time 0(N^2) Space 0(N^2) keep minimum and add at end
 */

class Solution {
    public int sumSubarrayMins(int[] A) {
    	if(A.length == 1)
    		return A[0];
    	int n = A.length;
    	Stack<Integer> stack = new Stack<>();
    	// prev less and next less element index
    	// stores distances from prev and next less element
    	int []left = new int[n], right = new int[n];
    	
    	for(int i = 0;i < n;i++) {
    		left[i] = i+1;
    		right[i] = n-i;
    	}
    	// stack max at top and smallest at bottom
    	for(int i = 0;i < n;i++) {
    		while(!stack.isEmpty() && A[stack.peek()] > A[i])
    			stack.pop();
    		left[i] = stack.isEmpty()?i+1:i-stack.peek();
    		stack.push(i);
    	}
    	
    	// next less element
    	// stack max at top and smallest at bottom
    	stack = new Stack<>();
    	for(int i = 0;i < n;i++) {
    		while(!stack.isEmpty() && A[stack.peek()] > A[i]) {
    			right[stack.peek()] = i - stack.peek();
    			stack.pop();
    		}
    		stack.push(i);
    	}
    	
    	int ans = 0, mod = (1<<9)+7;
    	for(int i = 0;i < n;i++)
    		ans = (ans + A[i] *left[i] * right[i]) % mod;
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {3,1,2,4};
		System.out.println(new Solution().sumSubarrayMins(A));
	}
}
