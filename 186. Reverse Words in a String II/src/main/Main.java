package main;

/* Given an input string, reverse the string word by word. A word is defined as a sequence of 
 * non-space characters.
 * The input string does not contain leading or trailing spaces and the words are always 
 * separated by a single space.
 * 
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * Could you do it in-place without allocating extra space?
 */

// To handle multiple spaces with this solution pre-process and change multi space to single space
class Solution {
	public void reverseWords(char[] s) {
		if(s == null || s.length <= 1)
			return;
		reverse(s, 0, s.length-1);
		int start = 0, end = 0;
		for(;end < s.length;end++) {
			if(s[end] == ' ') {
				reverse(s, start, end-1);
				start = end+1;
			}
		}
		if(end == s.length && start != end)
			reverse(s, start, end-1);
		System.out.println(new String(s));
	}
	
	void reverse(char []s, int l, int r) {
		while(l <= r) {
			char temp = s[l];
			s[l++] = s[r];
			s[r--] = temp;
		}
	}
}

public class Main {
	public static void main(String[] args) {
		String s = "the sky is blue";
		new Solution().reverseWords(s.toCharArray());
	}
}
