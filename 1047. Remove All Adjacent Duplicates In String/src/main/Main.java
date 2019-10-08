package main;

import java.util.Stack;

class Solution {
	
	public String removeDuplicates(String S) {
		if(S.length() == 1)
    		return S;
		int n = S.length();
		char []res = S.toCharArray();
		int i = 0;
		for(int j = 0;j < n;j++, i++) {
			res[i] = res[j];
			if(i > 0 && res[i] == res[i-1])
				i -= 2;
		}
		return new String(res, 0, i);
	}
	
    public String removeDuplicates1(String S) {
    	if(S.length() == 1)
    		return S;
    	
    	Stack<Character> stack = new Stack<>();
    	stack.push(S.charAt(0));
    	int n = S.length();
    	for(int i = 1;i < n;i++) {
    		char c = S.charAt(i);
    		if(!stack.isEmpty() && c == stack.peek()) {
    			stack.pop();
    		} else {
    			stack.push(c);
    		}
    	}
    	StringBuilder sb = new StringBuilder();
    	while(!stack.isEmpty()) {
    		sb.append(stack.pop());
    	}
    	return sb.reverse().toString();
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "abbaca";
		System.out.println(new Solution().removeDuplicates(S));
	}
}