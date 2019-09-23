package main;

import java.util.Stack;

class Solution {
	
	public boolean validateStackSequences(int[] pushed, int[] popped) {
    	if(pushed == null && popped == null || pushed.length == 0 && popped.length == 0)
    		return true;
    	int push_index = 0, pop_index = 0;
    	Stack<Integer> stack = new Stack<>();    	
    	
    	while(pushed[push_index] != popped[0]) {
    		stack.push(pushed[push_index]);
    		push_index++;
    	}
    	stack.push(pushed[push_index]);
    	
    	while(pop_index < popped.length && push_index < pushed.length) {
    		if(!stack.isEmpty() && stack.peek() == popped[pop_index]) {
        		stack.pop();
        		pop_index++;
    		} else {
    			push_index++;
    			if(push_index < pushed.length)
    				stack.push(pushed[push_index]);
    		}
    	}    	
    	return stack.isEmpty();
    }
}

public class Main {
	public static void main(String[] args) {
		int []pushed = {1,2,3,4,5}, popped = {4,3,5,2,1};
		System.out.println(new Solution().validateStackSequences(pushed, popped));
	}
}