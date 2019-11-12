package main;

/* Given a string S, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it, and return the new string.

 

Example 1:

Input: "leetcodeisacommunityforcoders"
Output: "ltcdscmmntyfrcdrs"
Example 2:

Input: "aeiou"
Output: ""
 

Note:

S consists of lowercase English letters only.
1 <= S.length <= 1000
 * 
 */

class Solution {
    public String removeVowels(String S) {
    	StringBuilder sb = new StringBuilder();
    	for(char c : S.toCharArray()) {
    		if(isvowel(c))
    			continue;
    		sb.append(c);
    	}
    	return sb.toString();
    }
    
    private boolean isvowel(char c) {
    	return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "leetcodeisacommunityforcoders";
		System.out.println(new Solution().removeVowels(S));
	}
}