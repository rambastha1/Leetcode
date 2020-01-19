package main;

import java.util.Arrays;

/* similar to 727 but here ordering is not important
 * i was facing problem in how to increment and decrement count values in sliding window to each whether t is there in s 
 * https://leetcode.com/problems/minimum-window-substring/discuss/26971/Three-O(N)-concise-implemetation-according-to-leetcode-oj-discuss
 */
class Solution {
    public String minWindow(String s, String t) {
    	if(s.length() == 0 && t.length() == 0)
    		return "";
    	
    	if(s.compareTo(t) == 0)
    		return s;
    	String res = "";
    	int m = s.length(), n = t.length(), counter = 0, minlen = Integer.MAX_VALUE;
    	int start_index = 0;
    	
    	int []count = new int[128];
    	for(char c : t.toCharArray())
    		count[c]++;
    	int start = 0, end = 0;
    	for(end = 0;end < m;end++) {
    		count[s.charAt(end)]--;
    		if(count[s.charAt(end)] >= 0)
    			counter++;
    		while(counter == n) {
    			if(end - start + 1 < minlen) {
    				minlen = end - start + 1;
    				start_index = start;
    			}
    			count[s.charAt(start)]++;
    			if(count[s.charAt(start)] > 0)
    				counter--;
    			start++;
    		}
    	}
    	if(minlen == Integer.MAX_VALUE)
    		return "";
    	return s.substring(start_index, start_index + minlen);
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "acbbaca", t = "aba";
		System.out.println(new Solution().minWindow(s, t));
	}
}