package main;

import java.util.Stack;

class Solution {
    public int calculate(String s) {
    	if(s.length() == 0)
    		return 0;
    	Stack<Integer> val_stack = new Stack<>();
    	Stack<Character> sign_stack = new Stack<>();
    	char sign = '+';
    	int i = 0, n = s.length();
    	while(i < n) {
    		char c = s.charAt(i);
    		if(c == ' ') {
    			i++;
    			continue;
    		} if(Character.isDigit(c)) {
    			int j = i;
    			while(j < n && Character.isDigit(s.charAt(j)))
    				j++;
    			int num = Integer.parseInt(s.substring(i, j));
    			i = j-1;
    			val_stack.push(evaluate(val_stack, num, sign));
    		} else if(c == '(') {
    			val_stack.push(Integer.MIN_VALUE);
    			sign_stack.push(sign);
    			sign = '+';
    		} else if(c == ')') {
    			int num = 0;
    			while(!val_stack.isEmpty() && val_stack.peek() != Integer.MIN_VALUE) {
    				num += val_stack.pop();
    			}
    			// for min value
    			val_stack.pop();
    			char Sign = sign_stack.pop();
    			val_stack.push(evaluate(val_stack, num, Sign));
    		} else
    			sign = c;
    		i++;
    	}
    	
    	int ans = 0;
    	while(!val_stack.isEmpty() && val_stack.peek() != Integer.MIN_VALUE)
    		ans += val_stack.pop();
    	return ans;
    }
    
    private int evaluate(Stack<Integer> stack, int num, char sign) {
    	if(sign == '+')
    		return num;
    	return -num;
    }
}

public class Main {
	public static void main(String[] args) {
		//String s = "(1+(4+5+2)-3)+(6+8)";
		String s = "1 + 1 ";
		System.out.println(new Solution().calculate(s));
	}
}
