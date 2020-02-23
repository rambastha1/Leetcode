package main;

import java.util.Stack;

class Solution {
    public String reverseParentheses(String s) {
    	int n = s.length();
    	Stack<StringBuilder> stack = new Stack<>();
    	stack.push(new StringBuilder());
    	
    	for(int i = 0;i < n;i++) {
    		char c = s.charAt(i);
    		if(c == '(')
    			stack.push(new StringBuilder());
    		else if(c == ')') {
    			StringBuilder sb = stack.pop();
    			stack.peek().append(sb.reverse().toString());
    		} else 
    			stack.peek().append(c);
    	}
    	return stack.peek().toString();
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "(ed(et(oc))el)";
		System.out.println(new Solution().reverseParentheses(s));
	}
}
