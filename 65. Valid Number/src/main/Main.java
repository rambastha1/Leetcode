package main;
/* numberafterE is required because 1e3 is true but 1e is not once e is seen there must be digit after that
 * https://leetcode.com/problems/valid-number/discuss/23738/Clear-Java-solution-with-ifs
 */
class Solution {
	public boolean isNumber(String s) {
    	s = s.trim();
    	int n = s.length();
    	char []arr = s.toCharArray();
    	
    	boolean numberseen = false, eseen = false, numberafterE = true, pointseen = false;
    	for(int i = 0;i < n;i++) {
    		char c = s.charAt(i);
    		if(c >= '0' && c <= '9') {
    			numberseen = true;
    			numberafterE = true;
    		} else if(c == '.') {
    			if(pointseen || eseen)
    				return false;
    			pointseen = true;
    		} else if(c == 'e') {
    			if(eseen || !numberseen)
    				return false;
    			eseen = true;
    			numberafterE = false;
    		} else if(c == '+' || c == '-') {
    			if(i != 0 && s.charAt(i-1) != 'e')
    				return false;
    		} else 
    			return false;
    	}
    	return numberseen && numberafterE;
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "95a54e53";
		System.out.println(new Solution().isNumber(s));
	}
}
