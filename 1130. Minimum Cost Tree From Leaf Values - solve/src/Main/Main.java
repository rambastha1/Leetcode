package Main;

import java.util.Stack;

// https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/339959/One-Pass-O(N)-Time-and-Space
class Solution {
    public int mctFromLeafValues(int[] arr) {
    	Stack<Integer> stack = new Stack<>();
    	// to avoid null pointer exception of stack, MAX is pushed
    	stack.push(Integer.MAX_VALUE);
    	
    	int res = 0;
    	for(int ele : arr) {
    		while(!stack.isEmpty() && stack.peek() <= ele) {
    			int a = stack.pop();
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
    			/* trying to remove a with minimum cost.. cost is a*b ie. greater element on left side of a or on right side
    			 * to minimize cost minimize b, thus stack.peek() is greater element on left side of a
    			 * ele -> greater element at right side of a
    			 * minimum greater =  Math.min(stack.peek(), ele)
    			 */
    			res += a * Math.min(stack.peek(), ele);
    		}
    		stack.push(ele);
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