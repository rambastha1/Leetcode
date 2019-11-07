package main;

/* Given a string s and an integer k, find out if the given string is a K-Palindrome or not.

A string is K-Palindrome if it can be transformed into a palindrome by removing at most k characters from it.

 

Example 1:

Input: s = "abcdeca", k = 2
Output: true
Explanation: Remove 'b' and 'e' characters.
 

Constraints:

1 <= s.length <= 1000
s has only lowercase English letters.
1 <= k <= s.length
 * 
 */

class Solution {
	// count of odd won't work because palindrome needs to be subsequence of s. ordering cannot change
	// find longest palindromic subsequence substract
    public boolean isValidPalindrome(String s, int k) {
    	if(s == null)
    		return false;
    	if(s.length() <= k)
    		return true;
    	
    	int n = s.length();
    	int []dp = new int[n];
    	for(int i = n-1;i >= 0;i--) {
    		dp[i] = 1;
    		int prev = 0;
    		for(int j = i+1;j < n;j++) {
    			int temp = dp[j];
    			if(s.charAt(i) == s.charAt(j))
    				dp[j] = prev + 2;
    			else
    				dp[j] = Math.max(dp[j], dp[j-1]);
    			prev = temp;
    		}
    	}
    	System.out.println(dp[n-1]);
    	return n-dp[n-1] <= k;
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "bacabaaa";
		int k = 2;
		System.out.println(new Solution().isValidPalindrome(s, k));
	}
}