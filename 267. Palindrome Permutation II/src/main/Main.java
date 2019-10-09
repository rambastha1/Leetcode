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
 * https://leetcode.com/problems/palindrome-permutation-ii/discuss/69696/AC-Java-solution-with-explanation
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
		StringBuilder sb = new StringBuilder();
		List<Character> list = new ArrayList<>();
		/* odd count character need to be at center, even can be added one at start other at end to make 
		 * palindrome
		 */
		for(int i = 0;i < 128;i++) {
			if(count[i] > 0) {
				// odd
				if(count[i]%2 == 1) {
					sb.append((char)i);
					count[i]--;
				} 
			}
		}
		// sb contains all characters with odd count these need to be at center
		dfs(res, count, s.length(), sb.toString());
		return res;
	}
	
	void dfs(List<String> res, int []count, int len, String s) {
		if(s.length() == len) {
			res.add(s);
			return;
		}
		
		for(int i = 0;i < 128;i++) {
			if(count[i] > 0) {
				// two because one added at start and another at end to make palindrome
				count[i] -= 2;
				dfs(res, count, len, (char)i + s + (char)i);
				count[i] += 2;
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
