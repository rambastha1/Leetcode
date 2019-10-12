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
    			res += top * Math.min(stack.peek(), a);
    		}
    		stack.push(a);
    	}
    	
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