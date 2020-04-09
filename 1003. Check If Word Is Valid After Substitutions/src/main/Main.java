package main;

import java.util.Stack;

class Solution {
    public boolean isValid(String S) {
    	int n = S.length();
    	Stack<Character> stack = new Stack<>();
    	for(char c : S.toCharArray()) {
    		if(c == 'c') {
    			if(stack.isEmpty() || stack.pop() != 'b')
    				return false;
    			if(stack.isEmpty() || stack.pop() != 'a')
    				return false;
    		} else
    			stack.push(c);
    	}
    	return stack.isEmpty();
    }
    
    public boolean isValid1(String S) {
        int n = S.length();
        StringBuilder sb = new StringBuilder(S);
        while(sb.toString().contains("abc")) {
            int index = sb.indexOf("abc");
            sb = new StringBuilder(sb.substring(0, index) + sb.substring(index+3));
        }
        return sb.length() == 0;
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "abcabcababcc";
		System.out.println(new Solution().isValid(S));
		System.out.println(new Solution().isValid1(S));
	}
}
