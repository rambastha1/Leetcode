package main;

import java.util.Stack;

class Solution {
	/*
	 * The final sum will be a sum of powers of 2, as every core (a substring (), with score 1) will 
	 * have it's score multiplied by 2 for each exterior set of parentheses that contains that core.
	 * 
	 * balance is the number of exterior set of parentheses that contains this core.
	 */
	//Time 0(N) space 0(1)
	public int scoreOfParentheses(String S) {
		int bal = 0, ans = 0;
		for(int i = 0;i < S.length();i++) {
			if(S.charAt(i) == '(')
				bal++;
			else {
				bal--;
				if(S.charAt(i-1) == '(')
					ans += 1 << bal;
			}
		}
		
		return ans;
	}
	
	//Time 0(N) Space 0(N)
	/*public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack();
        stack.push(0); // The score of the current frame

        for(char c : S.toCharArray()) {
        	if(c == '(')
        		stack.push(0);
        	else {
        		int inner = stack.pop();
        		int outer = stack.pop();
        		stack.push(outer + Math.max(2*inner, 1));
        	}
        }
        return stack.pop();
    }*/
}

public class Main {
	public static void main(String[] args) {
		String S = "(()(()))";
		System.out.println(new Solution().scoreOfParentheses(S));
	}
}