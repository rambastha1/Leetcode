package main;

import java.util.Stack;

/* Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . 
The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

Some examples:

"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12
https://leetcode.com/problems/basic-calculator-iii/discuss/113590/Java-O(n)-using-two-stack
 * 
 */
class Solution {
	public int calculate(String s) {
		if(s == null || s.length() == 0)
			return 0;
		
		char sign = '+';
		Stack<Long> stack = new Stack<>();
		Stack<Character> cstack = new Stack<>();
		
		for(int i = 0;i < s.length();i++) {
			char c = s.charAt(i);
			
			if(c == ' ')
				continue;
			
			if(Character.isDigit(c)) {
				long num = 0;
				//get digit
				while(i < s.length() && Character.isDigit(s.charAt(i))) {
					num = num*10 + s.charAt(i) - '0';
					i++;
				}
				// this is important as pointer moves forward and checks false
				i--;
				
				stack.push(evaluate(num, stack, sign));
			} else if (c == '(') {
				// used as delimiter of level
				stack.push(Long.MAX_VALUE);
				cstack.push(sign);
				sign = '+';
			} else if(c == ')') {
				long num = 0;
				// same as end
				while(stack.peek() != Long.MAX_VALUE) {
					num += stack.pop();
				}
				// pop Long.MAX
				stack.pop();
				char operator = cstack.pop();
				stack.push(evaluate(num, stack, operator));
			} else {
				sign = c;
			}
		}
		
		int res = 0;
		// only '+' or '-'(-num) are left
		while(!stack.isEmpty()) {
			res += stack.pop();
		}
		return res;
	}
	
	private Long evaluate(long res, Stack<Long> stack, char sign) {
		if(sign == '+')
			return res;
		if(sign == '-')
			return -res;
		if(sign == '*')
			return stack.pop()*res;
		return stack.pop()/res;
	}
}

public class Main {
	public static void main(String[] args) {
		String s = "1 + 1";
		System.out.println(new Solution().calculate(s));
	}
}