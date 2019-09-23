package main;

// https://leetcode.com/problems/swap-adjacent-in-lr-string/discuss/113789/Simple-Java-one-pass-O(n)-solution-with-explaination
class Solution {
    public boolean canTransform(String start, String end) {
    	if(!start.replace("X","").equals(end.replace("X", "")))
    		return false;
    	int s = 0, e = 0;
    	int len1 = start.length(), len2 = end.length();
    	while(s < len1 && e < len2) {
    		 // get the non-X positions of 2 strings
    		while(s < len1 && start.charAt(s)=='X')
    			s++;
    		while(e < len2 && end.charAt(e)=='X')
    			e++;
    		//if both of the pointers reach the end the strings are transformable
    		if(s == len1 && e == len2)
    			return true;
    		// if only one of the pointer reach the end they are not transformable
    		if(s == len1 || e == len2)
    			return false;
    		
    		// both char must be same
    		if(start.charAt(s) != end.charAt(e))
    			return false;
// if the character is 'L', it can only be moved to the left. p1 should be greater or equal to p2.
    		if(start.charAt(s)== 'L' && s < e)
    			return false;
// if the character is 'R', it can only be moved to the right. p2 should be greater or equal to p1.
    		if(start.charAt(s) == 'R' && s > e)
    			return false;
    		s++;
    		e++;
    	}
    	return true;
    }
}

public class Main {
	public static void main(String[] args) {
		String start = "RXXLRXRXL", end = "XRLXXRRLX";
		System.out.println(new Solution().canTransform(start, end));
	}
}
