package main;

/* simialr to 727 but here ordering is not important
 * 
 */
class Solution {
    public String minWindow(String s, String t) {
    	if(s.length() == 0 && t.length() == 0)
    		return "";
    	String res = "";
    	int len = s.length();
    	
    	int []count = new int[256];
    	for(char c : t.toCharArray())
    		count[c]++;
    	
    	int i = 0, tindex = 0;
    	while(i < s.length()) {
    		// if char matches move T pointer
    		if(s.charAt(i) == t.charAt(tindex)) {
    			tindex++;
    			/* 
    			 * If T is subsequence of this substring
    			 * try to move backward to find if there's smaller substring which contains T
    			 */
    			if(tindex == t.length()) {
    				int end = i+1;
    				// tindex to point last character
    				tindex--;
    				while(tindex >= 0) {
    					if(s.charAt(i) == t.charAt(tindex))
    						tindex--;
    					i--;
    				}
    				// i to point to first character in T
    				i++;
    				// tindex to point to 0
    				tindex++;
    				// check if this substring is smaller
    				if(end - i < len) {
    					len = end - i;
    					res = s.substring(i, end);
    				}
    					
    			}
    		}
    		i++;
    	}
    	return len == Integer.MAX_VALUE?"":res;
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "ADOBECODEBANC", t = "ABC";
		System.out.println(new Solution().minWindow(s, t));
	}
}