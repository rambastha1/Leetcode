package main;

import java.util.Stack;
class Solution {
	// perform all operation at closing bracket
	// Time and Space 0(N) 
    public boolean parseBoolExpr(String expression) {
    	Stack<Character> op = new Stack<>(), val = new Stack<>();
    	for(char c : expression.toCharArray()) {
    		if(c == ',')
    			continue;
    		if(c == '&' || c == '|' || c == '!')
    			op.push(c);
    		else {
    			if(c == ')') {
    				if(val.isEmpty() || op.isEmpty())
    					return false;
    				boolean res = val.peek()=='t'?true:false;
    				
    				if(op.peek() == '!') {
    					val.pop();
    					res = !res;
    				} else {
	    				res = val.pop() == 't'?true:false;
	    				while(!val.isEmpty() && val.peek() != '(') {
	    					boolean temp = val.pop() == 't'?true:false;
	    					if(op.peek() == '&')
	    						res &= temp;
	    					else if(op.peek() == '|')
	    						res |= temp;	
	    				}
    				}
    				val.pop();
    				val.push(res?'t':'f');
    				op.pop();
    			} else 
    				val.push(c);
    		}
    	}
    	if(val.isEmpty())
    		return false;
    	return val.pop()=='t'?true:false;
    }
}

public class Main {
	public static void main(String[] args) {
		String expression = "|(&(t,f,t),!(t))";
		//String expression = "!(f)";
		System.out.println(new Solution().parseBoolExpr(expression));
	}
}