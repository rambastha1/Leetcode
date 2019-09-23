package main;

/* https://leetcode.com/discuss/interview-question/233724
 * You are given with a string . Your task is to remove atmost two substrings of any length from the given 
 * string such that the remaining string contains vowels('a','e','i','o','u') only. Your aim is the maximise the 
 * length of the remaining string. Output the length of remaining string after removal of atmost two substrings.
 * NOTE: The answer may be 0, i.e. removing the entire string.
 * Sample Input
 * 2
 * earthproblem
 * letsgosomewhere
 * Sample Output
 * 3
 * 2
 */

class Solution {
	
	public int longestString(String s){
		int n = s.length();
		int start = 0, end = n-1;
		while(start < n && isvowel(s.charAt(start)))
			start++;
		
		if(start == n)
			return n;
		while(end >= 0 && isvowel(s.charAt(end)))
			end--;
		// total vowel characters
		int res = n - (end-start+1);
		
		// get vowel by removing maximum consonants
		int sum = 0, longest = 0;
		for(int i = start+1;i <= end;i++) {
			if(isvowel(s.charAt(i)))
				sum++;
			else
				sum = 0;
			longest = Math.max(longest, sum);
		}
		return longest + res;
	}
	
	private boolean isvowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}
}

public class Main {
	public static void main(String[] args) {
		//String s = "earthproblem";
		String s = "letsgosomewhere";
		System.out.println(new Solution().longestString(s));
	}
}
