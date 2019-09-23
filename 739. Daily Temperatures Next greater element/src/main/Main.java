package main;

import java.util.Stack;

class Solution {
	
	public int[] dailyTemperatures(int[] T) {
    	int []res = new int[T.length];
    	if(T == null || T.length <= 1)
    		return res;
    	
    	Stack<Integer> stack = new Stack<>();
    	for(int i = 0;i < T.length;i++) {
    		while(!stack.isEmpty() && T[i] > T[stack.peek()]) {
    			int index = stack.pop();
    			res[index] = i - index;
    		}
    		stack.push(i);
    	}    	
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []T = {73, 74, 75, 71, 69, 72, 76, 73};
		int []res = new Solution().dailyTemperatures(T);
		for(int i : res)
			System.out.print(i + " ");
		System.out.println();
	}
}