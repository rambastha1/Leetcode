package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* https://leetcode.com/discuss/interview-question/370112
 * Given a string s and an int k, return all unique substrings of s of size k with k distinct characters.
Example 1:
Input: s = "abcabc", k = 3
Output: ["abc", "bca", "cab"]
Example 2:

Input: s = "abacab", k = 3
Output: ["bac", "cab"]
Example 3:

Input: s = "awaglknagawunagwkwagl", k = 4
Output: ["wagl", "aglk", "glkn", "lkna", "knag", "gawu", "awun", "wuna", "unag", "nagw", "agwk", "kwag"]
Explanation: 
Substrings in order are: "wagl", "aglk", "glkn", "lkna", "knag", "gawu", "awun", "wuna", "unag", "nagw", "agwk", "kwag", "wagl" 
"wagl" is repeated twice, but is included in the output once.
Constraints:

The input string consists of only lowercase English letters [a-z]
0 ≤ k ≤ 26
 * 
 */

class Solution {
	public List<String> substringK(String s, int k){
		Set<String> set = new HashSet<>();
		Map<Character, Integer> map = new HashMap<>();
		
		int start = 0, n = s.length();
		for(int end = 0;end < n;end++) {
			while(map.containsKey(s.charAt(end))) {
				map.remove(s.charAt(start++));
			}
			
			map.put(s.charAt(end), 1);
			
			while(map.size() > k)
				map.remove(s.charAt(start++));
			
			if(end-start == k-1) {
				set.add(s.substring(start, end+1));
			}
		}
		return new ArrayList<>(set);
	}
}

public class Main {
	public static void main(String[] args) {
		/*String s = "abcabc";
		int k = 3;*/
		/*String s = "abacab";
		int k = 3;*/
		String s = "awaglknagawunagwkwagl";
		int k = 4;
		System.out.println(new Solution().substringK(s, k));
	}
}
