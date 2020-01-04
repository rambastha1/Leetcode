package main;

import java.util.HashSet;
import java.util.Set;

/* Given an equation, represented by words on left side and the result on right side.

You need to check if the equation is solvable under the following rules:

Each character is decoded as one digit (0 - 9).
Every pair of different characters they must map to different digits.
Each words[i] and result are decoded as one number without leading zeros.
Sum of numbers on left side (words) will equal to the number on right side (result). 
Return True if the equation is solvable otherwise return False.

 

Example 1:

Input: words = ["SEND","MORE"], result = "MONEY"
Output: true
Explanation: Map 'S'-> 9, 'E'->5, 'N'->6, 'D'->7, 'M'->1, 'O'->0, 'R'->8, 'Y'->'2'
Such that: "SEND" + "MORE" = "MONEY" ,  9567 + 1085 = 10652
Example 2:

Input: words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
Output: true
Explanation: Map 'S'-> 6, 'I'->5, 'X'->0, 'E'->8, 'V'->7, 'N'->2, 'T'->1, 'W'->'3', 'Y'->4
Such that: "SIX" + "SEVEN" + "SEVEN" = "TWENTY" ,  650 + 68782 + 68782 = 138214
Example 3:

Input: words = ["THIS","IS","TOO"], result = "FUNNY"
Output: true
Example 4:

Input: words = ["LEET","CODE"], result = "POINT"
Output: false
 

Constraints:

2 <= words.length <= 5
1 <= words[i].length, results.length <= 7
words[i], result contains only upper case English letters.
Number of different characters used on the expression is at most 10.
 * 
 */

/* Time 0(n!) Space 0(n) all permutation
 * https://leetcode.com/problems/verbal-arithmetic-puzzle/discuss/463953/Java-Fast-Backtracking-Clean-Code-O(n!)-~-300ms
 * it contains only upper case and map to 0-9
 * ASCII A-Z is 65-91 thus array size 91
 * left side add value and right side decrease value
 * say all alphabets are x,y,z...
 * we need to find a,b,c etc... such that
 * ax+by+cz.... = 0
 * store value in count array create set of letters
 * first letter of every word and result cannot be zero
 */
class Solution {
    public boolean isSolvable(String[] words, String result) {
    	Set<Character> set = new HashSet<>();
    	int []count = new int[91];
    	boolean []nz = new boolean[91];
    	
    	for(String word : words) {
    		char []arr = word.toCharArray();
    		for(int i = 0;i < arr.length;i++) {
    			if(i == 0) {
    				nz[arr[i]] = true;
    			}
    			set.add(arr[i]);
    			count[arr[i]] += (int)Math.pow(10, word.length()-i-1);
    		}
    	}
    	
    	char []arr = result.toCharArray();
    	for(int i = 0;i < arr.length;i++) {
    		if(i == 0)
    			nz[arr[i]] = true;
    		set.add(arr[i]);
    		count[arr[i]] -= (int)Math.pow(10, arr.length-i-1);
    	}
    	
    	boolean []used = new boolean[10];
    	char []chars = new char[set.size()];
    	int i = 0;
    	for(char c : set)
    		chars[i++] = c;
    	return backtrack(used, chars, nz, 0, 0, count);
    }
    
    private boolean backtrack(boolean []used, char []chars, boolean []nz, int index, int sum, int []count) {
    	// in the end sum should be 0 as some count[i] are negatives
    	if(index == chars.length)
    		return sum == 0;
    	
    	for(int i = 0;i <= 9;i++) {
    		// 
    		if(!used[i] && (i>0 || !nz[chars[index]])) {
    			used[i] = true;
    			/* i is the value assigning to that letter thus
    			 * sum + count[char] * value in the end it should be 0
    			 * 
    			 */
    			if(backtrack(used, chars, nz, index+1, sum + count[chars[index]]*i, count))
    				return true;
    			used[i] = false;
    		}
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		String []words = {"SEND","MORE"};
		String result = "MONEY";
		System.out.println(new Solution().isSolvable(words, result));
	}
}
