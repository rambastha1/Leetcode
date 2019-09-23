package main;

import java.util.ArrayList;
import java.util.List;

/* https://leetcode.com/articles/palindrome-permutation-ii/
 * Given a string s, return all the palindromic permutations (without duplicates) of it. 
 * Return an empty list if no palindromic permutation could be form.
 * 
 * Example 1:
 * 
 * Input: "aabb"
 * Output: ["abba", "baab"]
 * Example 2:
 * 
 * Input: "abc"
 * Output: []
 */

/* After successfully checking whether the string is palindrome permutable, build half string
 * put character with odd count in middle, and add reverse of string to make it palindrome
 * while creating half string count[c] will also get half
 */

class Solution {
	public List<String> generatePalindromes(String s) {
		List<String> res = new ArrayList<>();
		if(s == null || s.length() == 0)
			return res;
		if(s.length() == 1) {
            res.add(Character.toString(s.charAt(0)));
            return res;
        }
		
		int []count = new int[128];
		if(!canpermute(s, count))
			return res;
		
		// build half string
		String mid = "";
		int len = 0;
		for(int i = 0;i < 128;i++) {
			if(count[i] > 0) {
				// odd
				if((count[i] & 1) == 1) {
					mid = "" + (char)i;
					count[i]--;
				}
				count[i] /= 2;
				len++;
			}
		}
		
		dfs(mid, len, count, res, "");
		return res;
	}
	
	void dfs(String mid, int len, int []count, List<String> res, String s) {
		if(s.length() == len) {
			StringBuilder sb = new StringBuilder(s).reverse();
			res.add(s + mid + sb.toString());
			return;
		}
		
		for(int i = 0;i < 128;i++) {
			if(count[i] > 0) {
				count[i]--;
				dfs(mid, len, count, res, s+(char)i);
				count[i]++;
			}
		}
	}
	
	boolean canpermute(String s, int []count) {
		int num = 0;
		for(char c : s.toCharArray()) {
			count[c]++;
			if(count[c]%2 != 0)
				num++;
			else
				num--;
		}
		return num <= 1;
	}
	
}

public class Main {
	public static void main(String[] args) {
		String s = "aabb";
		System.out.println(new Solution().generatePalindromes(s));
	}
}
