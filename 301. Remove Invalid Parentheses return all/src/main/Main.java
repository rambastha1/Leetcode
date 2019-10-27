package main;

import java.util.ArrayList;
import java.util.List;


// https://leetcode.com/problems/remove-invalid-parentheses/discuss/75027/Easy-Short-Concise-and-Fast-Java-DFS-3-ms-solution
class Solution {
    public List<String> removeInvalidParentheses(String s) {
    	List<String> res = new ArrayList<>();
    	dfs(s, res, 0, 0, '(', ')');
    	return res;
    }
    /* istart is index jstart -> index of character removed in case of excess
     * 
     */
    private void dfs(String s, List<String> res, int istart, int jstart, char open, char close) {
    	// count open and close bracket
    	int count = 0, i = istart;
    	// stop when number of ')' > '('
    	while(i < s.length() && count >= 0) {
    		// using variable open because for reveresd string open is ')'
    		if(s.charAt(i) == open)
    			count++;
    		if(s.charAt(i) == close)
    			count--;
    		i++;
    	}
    		
		// No invalid closed parenthesis detected. Now check opposite direction, or reverse back to original direction.
		if(count >= 0) {
			String reverse = new StringBuilder(s).reverse().toString();
			if(open == '(')
	    		dfs(reverse, res, 0, 0, ')', '(');
	    	else
	    		// the string is valid from both front and back i.e. equal number of '(' and ')'
	    		res.add(reverse);
		} else {
			// 'i-1' is the index of abnormal ')' which makes count < 0
			i--;
			// count = 0 till i check for rest of string
			// Try removing one at each position, skipping duplicates
			//remove one from prefix first occurrence then dfs rest of string 
			for(int j = jstart;j <= i;j++) {
				if(s.charAt(j) == close && (j == jstart || s.charAt(j-1) != close)) {
					// Recursion: iStart = i since we now have valid # closed parenthesis thru i. jStart = j prevents duplicates
					dfs(s.substring(0, j) + s.substring(j+1, s.length()), res, i, j, open, close);
				}
			}
		}
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "(a)())()";
		System.out.println(new Solution().removeInvalidParentheses(s));
	}
}