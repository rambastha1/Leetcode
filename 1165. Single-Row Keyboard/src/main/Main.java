package main;

import java.util.HashMap;
import java.util.Map;
/* There is a special keyboard with all keys in a single row.
Given a string keyboard of length 26 indicating the layout of the keyboard (indexed from 0 to 25), 
initially your finger is at index 0. To type a character, you have to move your finger to the index of the desired character. 
The time taken to move your finger from index i to index j is |i - j|.
You want to type a string word. Write a function to calculate how much time it takes to type it with one finger.

Example 1:
Input: keyboard = "abcdefghijklmnopqrstuvwxyz", word = "cba"
Output: 4
Explanation: The index moves from 0 to 2 to write 'c' then to 1 to write 'b' then to 0 again to write 'a'.
Total time = 2 + 1 + 1 = 4. 
Example 2:
Input: keyboard = "pqrstuvwxyzabcdefghijklmno", word = "leetcode"
Output: 73

Constraints:
keyboard.length == 26
keyboard contains each English lowercase letter exactly once in some order.
1 <= word.length <= 10^4
word[i] is an English lowercase letter.
 * 
 */
class Solution {
	// can be done using map but this saves memory and time
    public int calculateTime(String keyboard, String word) {
    	
    	int []index = new int[26];
    	for(int i = 0;i < keyboard.length();i++) {
    		char c = keyboard.charAt(i);
    		index[c-'a'] = i;
    	}
    	int ans = index[word.charAt(0)-'a'];
    	int last = ans;
    	for(int i = 1;i < word.length();i++) {
    		int curr = index[word.charAt(i)-'a'];
    		ans += Math.abs(curr - last);
    		last = curr;
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		//String keyboard = "abcdefghijklmnopqrstuvwxyz", word = "cba";
		String keyboard = "pqrstuvwxyzabcdefghijklmno", word = "leetcode";
		System.out.println(new Solution().calculateTime(keyboard, word));
	}
}