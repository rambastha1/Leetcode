package main;

import java.util.Stack;

/* https://www.geeksforgeeks.org/length-of-the-longest-valid-substring/
 * dp[i] is length of longest valid parentheses
 * parentheses end had to be ')' thus if s[i] == '(' continue
 * ...() is length 2, suppose index of ')' is i, then its length would be dp[i-2] + 2
 * ...)) if dp[i-1] is valid parentheses then start would be dp[i-1] - 1
 * thus dp[i] = dp[i-1] + 2 + dp[i- dp[i-1] - 2] 
 * i-dp[i-2]-2 would be ')'
 */
class Solution {
    public int longestValidParentheses(String s) {
    	int n = s.length(), longest = 0;
    	if(n < 2)
    		return 0;
    	int []dp = new int[n];
    	for(int i = 1;i < n;i++) {
    		char c = s.charAt(i);
    		if(c == ')' && (i-dp[i-1]-1 >= 0) && s.charAt(i-dp[i-1]-1) == '(') {
    			dp[i] = dp[i-1] + 2 + (i-dp[i-1]-2 >= 0?dp[i-dp[i-1]-2]:0);
    		}
    		longest = Math.max(longest, dp[i]);
    	}
    	return longest;
    }
    
    
    /* Scan the string from beginning to end.
If current character is '(',
push its index to the stack. If current character is ')' and the
character at the index of the top of stack is '(', we just find a
matching pair so pop from the stack. Otherwise, we push the index of
')' to the stack.
After the scan is done, the stack will only
contain the indices of characters which cannot be matched. Then
let's use the opposite side - substring between adjacent indices
should be valid parentheses.
If the stack is empty, the whole input
string is valid. Otherwise, we can scan the stack to get longest
valid substring as described in step 3.
     * 
     */
    public int longestValidParentheses1(String s) {
    	int n = s.length(), longest = 0;
    	if(n < 2)
    		return 0;
    	Stack<Integer> stack = new Stack<>();
    	stack.push(-1);
    	for(int i = 0;i < n;i++) {
    		if(s.charAt(i) == '(')
    			stack.push(i);
    		else {
    			stack.pop();
    			
    			if(!stack.isEmpty())
    				longest = Math.max(longest, i - stack.peek());
    			else
    				stack.push(i);
    		}
    	}
    	return longest;
    }
}

public class Main {
	public static void main(String[] args) {
		String s = ")()())";
		System.out.println(new Solution().longestValidParentheses(s));
		System.out.println(new Solution().longestValidParentheses1(s));
	}
}
