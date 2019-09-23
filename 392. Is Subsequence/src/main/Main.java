package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Follow up:
 * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one 
 * to see if T has its subsequence. In this scenario, how would you change your code?
 * https://leetcode.com/problems/is-subsequence/discuss/87302/Binary-search-solution-for-follow-up-with-detailed-comments
 */

class Solution {
	
	// Time 0(s.len * lg(t.len))
	// For follow up question
	public boolean isSubsequence(String s, String t) {
		List<Integer> []count = new ArrayList[26];
		for(int i = 0;i < t.length();i++) {
			if(count[t.charAt(i)-'a'] == null)
				count[t.charAt(i)-'a'] = new ArrayList<>();
			count[t.charAt(i)-'a'].add(i);
		}
		
		//prev defines index of t at which latest s[i] is found
		int prev = 0;
		for(int i = 0;i < s.length();i++) {
			// Character not present Cannot be subsequence
			if(count[s.charAt(i)-'a'] == null) return false;
			/*
			 * this makes use of the number returned by java inbuilt binary search
			 * It searches for prev in list of count[s.charAt(i)-'a']
			 * searches for index(prev) most probably it won't be there as it is returned by other list
			 * Thus binary search returns a value..This value is used to get ceil of current index     
			 */
			int j = Collections.binarySearch(count[s.charAt(i)-'a'], prev);
			// makes use of value returned by binary search when element is not found
			if(j < 0) 
				j = -j - 1;
			if(j == count[s.charAt(i)-'a'].size()) return false;
			// We need ceil value of latest found index thus +1
			prev = count[s.charAt(i)-'a'].get(j) + 1;
		}
		return true;
	}
	
	// For regular question
	// Time 0(s.length + t.length)
	/*public boolean isSubsequence(String s, String t) {
    	if(s == null || t == null)
    		return false;
    	if(s.length() == 0)
    		return true;
        if(t.length() == 0)
            return false;
    	
    	int i = 0, j = 0;
    	while(i < t.length() && j < s.length()) {
    		if(s.charAt(j) == t.charAt(i))
    			j++;
    		if(j == s.length())
    			return true;
    		i++;
    	}
    	return false;
    }*/
	
	// 0ms solution
	/*public boolean isSubsequence(String s, String t) {
		int index = -1;
		for(char c : s.toCharArray()) {
			index = t.indexOf(c, index+1);
			if(index == -1)
				return false;
		}
		return true;
	}*/
}

public class Main {
	public static void main(String[] args) {
		String s = "abc", t = "bahbgdca";
		//String s = "axc", t = "ahbgdc";
		System.out.println(new Solution().isSubsequence(s, t));
	}
}