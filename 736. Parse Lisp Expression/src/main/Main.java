package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/parse-lisp-expression/discuss/113902/A-Clean-Java-Solution
class Solution {
    public int evaluate(String expression) {
    	return eval(expression, new HashMap<>());
    }
    
    private int eval(String exp, Map<String, Integer> parent) {
    	if(exp.charAt(0) != '(') {
    		// just number or symbol
    		if(Character.isDigit(exp.charAt(0)) || exp.charAt(0) == '-')
    			return Integer.parseInt(exp);
    		return parent.get(exp);
    	}
    	
    	// create a new scope, add add all the previous values to it
        Map<String, Integer> map = new HashMap<>();
        map.putAll(parent);
        
        List<String> tokens = tokenize(exp.substring(exp.indexOf(' ')+1, exp.length()-1));
        // ADD
        if(exp.startsWith("(a")) {
        	return eval(tokens.get(0), parent) + eval(tokens.get(1), parent);
        } else if(exp.startsWith("(m")) {
        	return eval(tokens.get(0), parent) * eval(tokens.get(1), parent);
        } else {
        	// let
        	for (int i = 0; i < tokens.size() - 2; i += 2)
                map.put(tokens.get(i), eval(tokens.get(i + 1), map));
            return eval(tokens.get(tokens.size() - 1), map);
        }
    }
    
    private List<String> tokenize(String str) {
    	List<String> res = new ArrayList<>();
    	int p = 0;
    	StringBuilder sb = new StringBuilder();
    	for(char c : str.toCharArray()) {
    		if(c == '(')
    			p++;
    		if(c == ')')
    			p--;
    		if(p == 0 && c == ' ') {
    			res.add(sb.toString());
    			sb = new StringBuilder();
    		} else {
    			sb.append(c);
    		}
    	}
    	if(sb.length() > 0)
    		res.add(sb.toString());
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		String expression = "(let x 1 y 2 x (add x y) (add x y))";
		System.out.println(new Solution().evaluate(expression));
	}
}
