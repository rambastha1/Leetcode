package main;

import java.util.Stack;

class Solution {
	
	// Constant Space
	// emulating original array as stack
	/* 40  30 35 80 100
	 * 40  30 35 80 100
	 * 40  35 30 80 100
	 * 80  35 30 80 100
	 * 100 35 30 80 100 
	 */
	public boolean verifyPreorder(int[] preorder) {
		// k -> top of stack
		int min = Integer.MIN_VALUE, k = -1;
		for(int pre : preorder) {
			if(pre < min) return false;
			while(k>=0 && pre > preorder[k])
				min = preorder[k--];
			preorder[++k] = pre;
		}		
		return true;
	}
	
	// Space 0(N)
	boolean isbst(int []pre) {
		if(pre == null || pre.length == 0)
			return false;
		if(pre.length == 1)
			return true;
		
		Stack<Integer> stack = new Stack<>();
		int root = Integer.MIN_VALUE;
		for(int i = 0;i < pre.length;i++) {
			if(pre[i] < root)
				return false;
			while(!stack.isEmpty() && stack.peek() < pre[i]) {
				root = stack.pop();
			}
			stack.push(pre[i]);
		}		
		return true;
	}
}

public class Main {
	public static void main(String[] args) {
		int []pre = {40, 30, 35, 80, 100};
		System.out.println(new Solution().isbst(pre));
	}
}
