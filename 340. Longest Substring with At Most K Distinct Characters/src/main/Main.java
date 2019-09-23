package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * For example, Given s = “eceba” and k = 2,
 * T is "ece" which its length is 3.
 */

/*
 * The problem is very similar to the Leetcode question 3 (Longest Substring Without Repeating Characters). 
 * The key idea of the solution is to use two pointers to maintain a "sliding window". 
 * We also use a HashMap to store all the characters in the window. 
 * Each time we move forward the "start" pointer and increase the size of the sliding window until 
 * the size of the window is greater than K. 
 * Then we can easily calculate the size of the window which contains at most K distinct characters. 
 * The next step is to shrink the window by moving forward the "end" pointer. 
 * In order to get the maximum window size, we must move the minimum steps of the end pointer. 
 * So each step we move the end pointer, we need to update the map and remove the character out of the 
 * sliding window. The stop condition is that when the window size is again equal to the K, 
 * which means the window contains K distinct characters. 
 * That's the minimum steps we need to move forward the end pointer. 
 * The only trick here is we need to check at the last that if the start pointer is out of boundary, 
 * we still need to check if the largest window size. 
 * That's a common trick for major string w/ two pointer problems.
 */

class Solution {
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if(s == null || s.length() <= 0 || k < 0)
			return 0;
		
		Map<Character, Integer> map = new HashMap<>();
		int end = 0, start = 0, maxlen = 0;
		for(end = 0;end < s.length();end++) {
			if(map.containsKey(s.charAt(end)))
				map.put(s.charAt(end), map.get(s.charAt(end)) + 1);
			else
				map.put(s.charAt(end), 1);
			
			//see its end - start why? why not end - start + 1?
			if(map.size() > k)
				maxlen = Math.max(maxlen, end - start);
			
			/*
			 * this loop is necessary because map.get(s.charAt(start)) can be greater than 1
			 * In this case, it will keep deceasing the value of this key, but the map.size > k
			 * Thus move start so that map.size <= k
			 */
			
			while(map.size() > k) {
				//System.out.println(map);
				if(map.get(s.charAt(start)) == 1)
					map.remove(s.charAt(start));
				else
					map.put(s.charAt(start), map.get(s.charAt(start))-1);
				start++;
			}
			//System.out.println(map);
		}
		System.out.println("start: " + start + "\tend: " + end);
		
		//what is the need for this
		//which test case
		if(start < s.length())
			maxlen = Math.max(maxlen, end - start);
		return maxlen;
	}
}

public class Main {
	public static void main(String[] args) {
		String s = "ecebe";
		int k = 2;
		System.out.println(new Solution().lengthOfLongestSubstringKDistinct(s, k));
	}
}