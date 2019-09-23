package main;

import java.util.ArrayList;
import java.util.List;

class Solution {
    
	public List<String> generateParenthesis(int n) {
    	List<String> res = new ArrayList<>();
    	util("", res, 0, 0, n);
    	return res;
    }
    
    void util(String s, List<String> res, int open, int close, int n) {
    	if(s.length() == n*2) {
    		res.add(s);
    		return;
    	}
    	//open is count of '(' and close ')'
    	if(open < n)
    		util(s+"(", res, open+1, close, n);
    	if(close < open)
    		util(s+")", res, open, close+1, n);    		
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 3;
		System.out.println(new Solution().generateParenthesis(n));
	}
}