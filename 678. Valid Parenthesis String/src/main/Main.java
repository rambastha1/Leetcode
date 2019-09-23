package main;

// https://leetcode.com/articles/valid-parenthesis-string/ 
// read comment

class Solution {
    public boolean checkValidString(String s) {
    	// two variables for number of '(' and ')'
    	int leftbalance = 0, rightbalance = 0;
    	for(int i = 0;i < s.length();i++) {
    		if(s.charAt(i) == '(' || s.charAt(i) == '*')
    			leftbalance++;
    		else
    			leftbalance--;
    		// this means more ')' than '(' and '*' combined. cannot be valid
    		if(leftbalance < 0)
    			return false;
    	}
    	
    	for(int i = s.length()-1;i >= 0;i--) {
    		if(s.charAt(i) == ')' || s.charAt(i) == '*')
    			rightbalance++;
    		else
    			rightbalance--;
    		// this means more '(' than ')' and '*' combined. cannot be valid
    		if(rightbalance < 0)
    			return false;
    	}
    	return true;
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "(*))";
		System.out.println(new Solution().checkValidString(s));
	}
}
