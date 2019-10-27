package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Example 1:
// without space
Input: pattern = "abab", str = "redblueredblue"
Output: true
Example 2:

Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
Output: true
Example 3:

Input: pattern = "aabb", str = "xyzabcxzyabc"
Output: false
Notes:
You may assume both pattern and str contains only lowercase letters.
 * 
 * https://leetcode.com/problems/word-pattern-ii/discuss/73664/Share-my-Java-backtracking-solution
 */

class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
    	Map<Character, String> map = new HashMap<>();
    	Set<String> set = new HashSet<>();
    	return backtrack(str, pattern, map, set, 0, 0);
    }
    
    private boolean backtrack(String str, String pattern, Map<Character, String> map, Set<String> set, int i, int j) {
    	// Base Case
    	if(i == str.length() && j == pattern.length())
    		return true;
    	if(i == str.length() || j == pattern.length())
    		return false;
    	// get current pattern character
    	char p = pattern.charAt(j);
    	
    	// if the pattern character exists means met this character before
    	if(map.containsKey(p)) {
    		String s = map.get(p);
    		// if string doesn't start with same substring false
    		if(!str.startsWith(s, i)) {
    			return false;
    		}
    		// check for next pattern
    		return backtrack(str, pattern, map, set, i+s.length(), j+1);
    	}
    	
    	// check char by char 
    	for(int k = i;k < str.length();k++) {
    		String s = str.substring(i, k+1);
    		
    		// avoid duplicates
    		if(set.contains(s))
    			continue;
    		
    		// create or update it
    		map.put(p, s);
    		set.add(s);
    		// continue to match the rest
    		if(backtrack(str, pattern, map, set, k+1, j+1)) {
    			return true;
    		}
    		// backtrack
    		map.remove(p);
    		set.remove(s);
    	}
    	// we've tried our best but still no luck
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		String pattern = "abab", str = "redblueredblue";
		System.out.println(new Solution().wordPatternMatch(pattern, str));
	}
}