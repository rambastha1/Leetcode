package main;

/* Given a string str consisting of parentheses (, ) and alphanumeric characters. 
 * Remove minimum number of parenthesis to make the string valid and return any valid result. 
 * In a valid string for every opening/closing parentheses there is a matching closing/opening one.

Example 1:

Input: "ab(a(c)fg)9)"
Output: "ab(a(c)fg)9" or "ab(a(c)fg9)" or "ab(a(cfg)9)"
Example 2:

Input: ")a(b)c()("
Output: "a(b)c()"
Example 3:

Input: ")("
Output: ""
Example 4:

Input: "a(b))"
Output: "a(b)"
Example 5:

Input: "(a(c()b)"
Output: "a(c()b)" or "(ac()b)" or "(a(c)b)"
Example 6:

Input: "(a)b(c)d(e)f)(g)"
Output: "(a)b(c)d(e)f(g)"
 * 
 */

class Solution {
	// Time 0(N) space 0(N)
	public String balanceParens(String str) {
		int n = str.length();
		boolean []invalid = new boolean[n];
		
		int open = 0, close = 0;
		for(int i = 0;i < n;i++) {
			char c = str.charAt(i);
			if(c == '(')
				open++;
			else if(c == ')'){
				if(open > 0)
					open--;
				else
					invalid[i] = true;
			}
		}
		
		for(int i = n-1;i >= 0;i--) {
			char c = str.charAt(i);
			if(c == ')')
				close++;
			else if(c == '(') {
				if(close > 0)
					close--;
				else
					invalid[i] = true;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < n;i++) {
			if(!invalid[i])
				sb.append(str.charAt(i));
		}
		return sb.toString();
	}
}

public class Main {
	public static void main(String[] args) {
		String str = "ab(a(c)fg)9)";
		System.out.println(new Solution().balanceParens(str));
	}
}