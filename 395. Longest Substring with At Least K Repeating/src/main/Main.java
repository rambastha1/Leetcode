package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* For Sliding Window Approach
 * O(n) solution. 
 * In the while loop, either i or j will increase by 1 in each iteration, so it costs at most 2*n time. 
 * In total, it will cost 56 * n time, which is O(n).
 *
 * how do we explore all possible solutions (substrings that satisfy given constraints) ?
 * this post's solution explores this way
 * find all sub-strings which have "h=1" unique character(s) and each char in substr repeats at least "k" times
 * find all sub-strings which have "h=2" unique character(s) and each char in substr repeats at least "k" times
 * find all sub-strings which have "h=26" unique character(s) and each char in substr repeats at least "k" times
 * and we're done! at h = 26, we're done. 
 * Take max of all the above valid substrings (by tracking with --max-- variable) -- that'll be our answer.
 * 
 * Details: (for a fixed h)
 * basically, we want to find a window (i to j) which has "h" unique chars and if all h occur at least K times, 
 * we have a candidate solution
 * 
 * [Rules for window Expansion] 
 * so expand (j++) as long as the num of unique characters between 'i' to 'j' are h or less (unique <= h)
 * during expansion, also track chars that are "noLessThanK" (which occur at least k) end of the loop 
 * update max (max len of valid window which have h unique chars and all h have at least k occurrences)
 * 
 * once we see  unique = h + 1 -- , we just expanded our window with (h+1)th unique char, 
 * so we should start to shrink now.
 * 
 * [Rules to window Shrink window] 
 * shrink as long as we have unique chars > h 
 * (update counts, unique, NoLessThanK along the way)
 */

class Solution {
    
	
	
	/*
	 * Sliding Window Approach
	 * The algorithm explanation: find sub strings with only 1 kind of letter that repeated 
	 * at least k times. Then find the sub string with 2 kind of letters that 
	 * repeated at least k times. ... find sub string with all 26 kind of letters that 
	 * repeated at least k times.
	 * 
	 * For h=1:26, we are going to use sliding window (left i, right j) 
	 * to find the "longest window which contains exactly h unique characters and for each character, 
	 * there are at least K repeating ones". For example, when h=3, K=5, we are going to 
	 * find the longest window contains exactly 3 unique characters and each repeating 5 times.
	 * 
	 * Time 0(N*26) = 0(N)
	 */
	
	public int longestSubstring(String s, int k) {
    	char []str = s.toCharArray();
    	int []counts = new int[26];
    	int h, start, end, char_index, max = 0, unique_char_counter, occur_atleast_k;
    	
    	
    	for(h = 1;h <= 26;h++) {
    		Arrays.fill(counts, 0);
    		start = 0;end = 0; unique_char_counter = 0;occur_atleast_k = 0;
    		while(end < str.length) {
    			char_index = str[end] - 'a';
    			if(unique_char_counter <= h) {
    				if(counts[char_index] == 0)
    					unique_char_counter++;
    				counts[char_index]++;
    				
    				if(counts[char_index] == k)
    					occur_atleast_k++;
    				end++;
    			} else {
    				if(counts[char_index] == k)
    					occur_atleast_k--;
    				counts[char_index]--;
    				if(counts[char_index] == 0)
    					unique_char_counter--;
    				start++;
    			}
    			if(unique_char_counter == h && unique_char_counter == occur_atleast_k)
    				max = Math.max(end-start, max);
    		}
    	}
    	return max;
    }
}

public class Main {

	public static void main(String[] args) {
		String s = "aaabb";
		int k = 3;
		System.out.println(new Solution().longestSubstring(s, k));
	}
}