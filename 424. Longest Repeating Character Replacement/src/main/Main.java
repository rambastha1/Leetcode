package main;

/* end-start+1 = size of the current window
 * maxCount = largest count of a single, unique character in the current window
 * The main equation is: end-start+1-maxCount
 * 
 * When end-start+1-maxCount == 0, then then the window is filled with only one character
 * When end-start+1-maxCount > 0, then we have characters in the window that are NOT the character that 
 * occurs the most. end-start+1-maxCount is equal to exactly the # of characters that are NOT the 
 * character that occurs the most in that window. Example: For a window "xxxyz", end-start+1-maxCount 
 * would equal 2. (maxCount is 3 and there are 2 characters here, "y" and "z" that are not "x" in the window.)
 * 
 * We are allowed to have at most k replacements in the window, so when end-start+1-maxCount > k, 
 * then there are more characters in the window than we can replace, and we need to shrink the window.
 * 
 * If we have window with "xxxy" and k = 1, that's fine because end-start+1-maxCount = 1, 
 * which is not > k. maxLength gets updated to 4.
 * 
 * But if we then find a "z" after, like "xxxyz", then we need to shrink the window because now 
 * end-start+1-maxCount = 2, and 2 > 1. The window becomes "xxyz".
 * 
 * maxCount may be invalid at some points, but this doesn't matter, because it was valid earlier in the 
 * string, and all that matters is finding the max window that occurred anywhere in the string. 
 * Additionally, it will expand if and only if enough repeating characters appear in the window to 
 * make it expand. So whenever it expands, it's a valid expansion.
 * 
 */

class Solution {
	// Longest substring with at most K distinct 
    public int characterReplacement(String s, int k) {
    	if(s == null || s.length() == 0)
    		return 0;
    	int []count = new int[26];
    	int start = 0, maxlen = 0, maxcount = 0;
    	for(int end = 0;end < s.length();end++) {
    		count[s.charAt(end)-'A']++;
    		maxcount = Math.max(maxcount, count[s.charAt(end)-'A']);
    		while(end-start+1-maxcount > k) {
    			count[s.charAt(start-'A')]--;
    			start++;
    		}
    		maxlen = Math.max(maxlen, end-start+1);
    	}    	
    	return maxlen;
    }
}

public class Main {
	public static void main(String[] args) {
		/*String s = "AABABBA";
		int k = 1;*/
		
		String s = "ABBB";
		int k = 2;
		
		/*String s = "ABAB";
		int k = 2;*/
		System.out.println(new Solution().characterReplacement(s, k));
	}
}
