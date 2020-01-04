package Main;

import java.util.Stack;

// https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/339959/One-Pass-O(N)-Time-and-Space
class Solution {
    public int mctFromLeafValues(int[] arr) {
    	Stack<Integer> stack = new Stack<>();
    	// to avoid null pointer exception of stack, MAX is pushed
    	stack.push(Integer.MAX_VALUE);
    	
    	int res = 0;
    	for(int a : arr) {
    		while(!stack.isEmpty() && stack.peek() <= a) {
    			int top = stack.pop();
    			// stack.peek is first min to left and a is first max to the right
    			// top is removed
    			/*
    			 * Given an array A, choose two neighbors in the array a and b,
we can remove the smaller one min(a,b) and the cost is a * b.
What is the minimum cost to remove the whole array until only one left?

To remove a number a, it needs a cost a * b, where b >= a.
So a has to be removed by a bigger number.
We want minimize this cost, so we need to minimize b.

b has two candidates, the first bigger number on the left,
the first bigger number on the right.

The cost to remove a is a * min(left, right).
    			 */
    			res += top * Math.min(stack.peek(), a);
    		}
    		stack.push(a);
    	}
    	
    	// keep removing 
    	while(stack.size() > 2) {
    		res += stack.pop() * stack.peek();
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []arr = {6,2,4};
		System.out.println(new Solution().mctFromLeafValues(arr));
	}
}