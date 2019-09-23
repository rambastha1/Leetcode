package main;

import java.util.Stack;

/* The key to solve this problem is in recognizing that right ')' parentheses that 
 * are at the left side cannot be closed by left '(' parentheses.
 * if ')' is encountered and stack is empty increment 'unbalance i.e.' one more '(' is 
 * required at the start. For '(' push into stack.
 * Final answer will be number of '(' required at start i.e. unbalanced + stack size i.e.
 * number of ')' required at the end.
 */ 

class Solution {
    
	//Stack based solution
	/*public int minAddToMakeValid(String S) {
    	if(S == null || S.length() == 0)
    		return 0;
    	if(S.length() == 1)
    		return 1;
    	int unbalanced = 0;
    	Stack<Character> stack = new Stack<>();
    	for(int i = 0;i < S.length();i++) {
    		if(S.charAt(i) == '(')
    			stack.push('(');
    		else {
    			if(stack.isEmpty())
    				unbalanced++;
    			else
    				stack.pop();
    		}
    	}
    	return stack.size() + unbalanced;
    }*/
	
	//Using variables
	//Same approach
	public int minAddToMakeValid(String S) {
		int ans = 0, balance = 0;
		for(int i = 0;i < S.length();i++) {
			balance += S.charAt(i) == '('? 1:-1;
			if(balance == -1) {
				ans++;
				//Doing this ensures that balance >= -1
				balance++;
			}
					
		}
		return ans+balance;
	}
}

public class Main {
	public static void main(String[] args) {
		String S = "()))((";
		//String S = ")()";
		System.out.println(new Solution().minAddToMakeValid(S));
	}
}