package main;

/* replace "-" with "+-" and split by "+"
 * lhs contains coefficient of x and rhs numbers
 */
class Solution {
    public String solveEquation(String equation) {
    	String []eq = equation.split("=");
    	int lhs = 0, rhs = 0;
    	String []le = eq[0].replaceAll("-", "+-").split("\\+");
    	String []re = eq[1].replaceAll("-", "+-").split("\\+");
    	
    	for(String l : le) {
    		if(l.length() == 0) continue;
    		if(l.indexOf("x") >= 0)
    			lhs += Integer.parseInt(coefficient(l));
    		else
    			rhs -= Integer.parseInt(l);
    	}
    	
    	for(String r : re) {
    		if(r.length() == 0) continue;
    		if(r.indexOf("x") >= 0)
    			lhs -= Integer.parseInt(coefficient(r));
    		else
    			rhs += Integer.parseInt(r);
    	}
    	
    	if(lhs == 0) {
    		if(rhs == 0)
    			return "Infinite solutions";
    		else
    			return "No solution";
    	}
    	return "x=" + rhs/lhs;
    }
    
    public String coefficient(String str) {
    	if(str.length() == 1)
    		return "1";
    	if(str.length() == 2 && str.charAt(0)=='-')
    		return "-1";
    	return str.substring(0, str.indexOf("x"));
    }
}

public class Main {
	public static void main(String[] args) {
		String equation = "x+5-3+x=6+x-2";
		System.out.println(new Solution().solveEquation(equation));
	}
}
