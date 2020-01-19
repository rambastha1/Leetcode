package main;

/* Given a non-empty string, encode the string such that its encoded length is the shortest.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.

Note:

k will be a positive integer and encoded string will not be empty or have extra space.
You may assume that the input string contains only lower case English letters. The string's length is at most 160.
If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, 
return any of them is fine.
 

Example 1:

Input: "aaa"
Output: "aaa"
Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.
 

Example 2:

Input: "aaaaa"
Output: "5[a]"
Explanation: "5[a]" is shorter than "aaaaa" by 1 character.
 

Example 3:

Input: "aaaaaaaaaa"
Output: "10[a]"
Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".
 * 
 * https://leetcode.com/problems/encode-string-with-shortest-length/discuss/313545/Java-DP-20-ms-faster-than-86.49-39.5-MB-less-than-86.90
 */

class Solution {
	// Time 0(N^3)
    public String encode(String s) {
    	int n = s.length();
    	String [][]dp = new String[n][n];
    	
    	for(int gap = 0;gap < n;gap++) {
    		for(int i = 0, j = gap;j < n;i++,j++) {
    			
    			// adds dp[i][i] single characters to diagonal
    			// initialize with string with actual length
    			dp[i][j] = s.substring(i, j + 1);
    			for(int k = i;k < j;k++) {
    				if(dp[i][k].length() + dp[k+1][j].length() < dp[i][j].length()) {
    					dp[i][j] = dp[i][k] + dp[k+1][j];
    				}
    			}
    			/* this is to make sure that encoded string has smaller length than actual string
    			 * create encoded strings
    			 */
    			String str = s.substring(i, j+1);
    			int index = (str+str).indexOf(str, 1);
    			
    			// will eliminate single characters like 'a'
    			if(index < str.length()) {
    				str = String.valueOf(str.length()/index)+ "[" + dp[i][i+index-1] + "]";
    			}
    			
    			// if encoded string is smaller than actual string
    			if(str.length() < dp[i][j].length()) {
    				dp[i][j] = str;
    			}
    		}
    	}
    	return dp[0][n-1];
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "aabcaabcd";
		//String s = "abbbabbbcabbbabbbc";
		System.out.println(new Solution().encode(s));
	}
}