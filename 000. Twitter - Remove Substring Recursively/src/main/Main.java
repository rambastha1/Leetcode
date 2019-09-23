package main;

// https://leetcode.com/discuss/interview-question/124919/Twitter-or-Remove-Substring-Recursively

/* Given two strings s and t. Find the maximum number of times that one can recursively remove t from s.

Example 1:

Input: s = "aabcbc", t = "abc"
Output: 2
Explanation: We can first remove s[1:3] and s becomes "abc". We can then remove it all.
Example 2:

Input: s = "abababaaba", t = "ababa"
Output: 2
 * 
 */

class Solution {
	public int removeSubstring(String s, String t) {
		return dfs(s, t,0);
	}
	
	private int dfs(String s, String t, int count) {
		if(!s.contains(t))
			return count;
		int maxcount = 0, n = s.length();
		for(int i = 0;i < s.length();i++) {
			if(s.substring(i).contains(t)) {
				maxcount = Math.max(maxcount, dfs(s.substring(0, i) + s.substring(i+t.length(), s.length()), t, count+1)); 
			}
		}
		return maxcount;
	}
}

public class Main {
	public static void main(String[] args) {
		String s = "abababaaba", t = "ababa";
		System.out.println(new Solution().removeSubstring(s, t));
	}
}
