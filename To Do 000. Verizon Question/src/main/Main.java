package main;

import java.util.Stack;

class Solution {
	public int solution(String s) {
		if(s.length() == 0 || s.length() == 1)
			return 0;
		Stack<Character> stack = new Stack<>();
		char []c = s.toCharArray();
		int ans = 0;
		for(int i = 0;i < c.length;i++) {
			if(c[i] == '(')
				stack.push(')');
			else if(c[i] == ')') {
				if(stack.isEmpty() ||stack.pop() != c[i])
					return ans;
			} else {
				c[i] = '(';
				ans += solution(String.valueOf(c));
				c[i] = ')';
				ans += solution(String.valueOf(c));
			}
		}
		if(!stack.isEmpty())
			return ans;
		return ++ans;
	}
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
