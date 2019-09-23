package main;

import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		String str = "]";
		System.out.println(isvalid(str));		
	}
	
	public static boolean isvalid(String s) {
		
		Stack<Character> stack = new Stack<>();
		for(char c : s.toCharArray()) {
			
			if(c == '(')
				stack.push(')');
			else if(c == '{')
				stack.push('}');
			else if(c == '[')
				stack.push(']');
			else if(stack.isEmpty() || stack.pop() != c)
				return false;				
		}
		return stack.isEmpty();
	}
}
