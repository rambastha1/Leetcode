package main;

/* Given a string s and a list of strings dict, you need to add a closed pair of 
 * bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, 
 * you need to wrap them together by only one pair of closed bold tag. Also, if two substrings 
 * wrapped by bold tags are consecutive, you need to combine them.
 * 
 * Example 1:
 * Input: 
 * s = "abcxyz123"
 * dict = ["abc","123"]
 * Output:
 * "<b>abc</b>xyz<b>123</b>"
 * 
 * Example 2:
 * Input: 
 * s = "aaabbcc"
 * dict = ["aaa","aab","bc"]
 * Output:
 * "<b>aaabbc</b>c"
 * 
 * Note:
 * The given dict won't contain duplicates, and its length won't exceed 100.
 * All the strings in input have length in range [1, 1000].
 * 
 */
// Use a boolean array to mark if character at each position is bold or not.
// mark every character true if present 
// Jump game I and II
// Time 0(N) space 0(N)

class Solution {
	public String addBoldTag(String s, String[] dict) {
		int n = s.length();
		boolean [] bold = new boolean[n];
		int end = 0;
		for(int i = 0;i < n;i++) {
			for(String word : dict) {
				if(s.startsWith(word, i)) // use this better than substring
				//if(s.substring(i, i+word.length()).compareTo(word) == 0) gives outof index
					end = Math.max(end, i + word.length());
			}
			bold[i] = end > i;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < n;i++) {
			if(!bold[i]) {
				sb.append(s.charAt(i));
				continue;
			}
			
			int j = i;
			while(j < n && bold[j])
				j++;
			sb.append("<b>" + s.substring(i, j) + "</b>");
			i = j-1;
		}
		return sb.toString();
	}
}

public class Main {
	public static void main(String[] args) {
		/*String s = "aaabbcc";
		String []dict = {"aaa","aab","bc"};*/
		
		String s = "abcxyz123";
		String []dict = {"abc","123"};
		System.out.println(new Solution().addBoldTag(s, dict));
	}
}
