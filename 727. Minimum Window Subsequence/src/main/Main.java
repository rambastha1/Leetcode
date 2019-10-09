package main;
/* johnson9432 comment
 * https://leetcode.com/problems/minimum-window-subsequence/discuss/109356/JAVA-two-pointer-solution-(12ms-beat-100)-with-explaination
 * 
 * Main idea to start two pointers one at S and other at T
 * reach end of T, store the index i in S where this end is reached
 * start from end of T, move i backward to find if there if substring of smaller length
 * so that T is subsequence of that substring.
 * Minimum length should be answer
 */

class Solution {
    public String minWindow(String S, String T) {
    	if(S.length() == 0 && T.length() == 0)
    		return "";
    	String res = "";
    	int i = 0, tindex = 0, slen = S.length(), tlen = T.length(), len = Integer.MAX_VALUE;
    	while(i < slen) {
    		// if char matches move T pointer
    		if(S.charAt(i) == T.charAt(tindex)) {
    			tindex++;
    			/* 
    			 * If T is subsequence of this substring
    			 * try to move backward to find if there's smaller substring which contains T
    			 */
    			if(tindex == tlen) {
    				int end = i+1;
    				// tindex to point last character
    				tindex--;
    				while(tindex >= 0) {
    					if(S.charAt(i) == T.charAt(tindex))
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
    					res = S.substring(i, end);
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
		String S = "abcdebdde", T = "bde";
		System.out.println(new Solution().minWindow(S, T));
	}
}