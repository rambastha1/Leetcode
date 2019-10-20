package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).

Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. 
If the task is impossible, return -1.

 

Example 1:

Input: source = "abc", target = "abcbc"
Output: 2
Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".
Example 2:

Input: source = "abc", target = "acdbc"
Output: -1
Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d" in target string.
Example 3:

Input: source = "xyz", target = "xzyxz"
Output: 3
Explanation: The target string can be constructed as follows "xz" + "y" + "xz".
 

Constraints:

Both the source and target strings consist of only lowercase English letters from "a"-"z".
The lengths of source and target string are between 1 and 1000.
 * 
 */

// https://leetcode.com/problems/shortest-way-to-form-string/discuss/330938/Accept-is-not-enough-to-get-a-hire.-Interviewee-4-follow-up
// Read all solutions to understand completely
class Solution {
	/* same logic as below store idx[][26] where idx[i][char] stores length of source till that char
	 * every time j == source.length res++ and reset j
	 * if idx[j][target char] 
	 * time 0(M+N)
	 */
	public int shortestWay(String source, String target) {
		char[] cs = source.toCharArray(), ts = target.toCharArray();
		int[][] idx = new int[cs.length][26];
		idx[cs.length - 1][cs[cs.length - 1] - 'a'] = cs.length; 
		for (int i = cs.length - 2; i >= 0; i--) {
			idx[i] = Arrays.copyOf(idx[i + 1],26);
			idx[i][cs[i] - 'a'] = i + 1; 
		}
		int j = 0, res = 1;
		for (int i = 0; i < ts.length; i++) {
			if (j == cs.length) {
				j = 0;
				res++;
			}
			j = idx[j][ts[i] - 'a'];
			// source doesn't contain it
			if (idx[0][ts[i] - 'a'] == 0) 
				return -1;
			// left behind search again
			if (j == 0) {
				res++;
				i--;
			}
		}
		return res;
	}
	
	
	/* put entire binary search list in 2D array
	 * just that for every index j of idx[i][j], it contains next index of occurrence
	 * search for char then index if left behind res++ reset j and search again 
	 * Time 0(M+N)
	 */
	public int shortestWay1(String source, String target) {
		char[] src = source.toCharArray(), dest = target.toCharArray();
		int[][] idx = new int[26][src.length];
		
		for (int i = 0; i < src.length; i++) 
			idx[src[i] - 'a'][i] = i + 1;
		
		for (int i = 0; i < 26; i++) {
			for (int j = src.length - 1, pre = 0; j >= 0; j--) {
				if (idx[i][j] == 0) 
					idx[i][j] = pre;
				else 
					pre = idx[i][j];
			}
		}
		int res = 1, j = 0;
		for (int i = 0; i < dest.length; i++) {
			if (j == src.length) {
				j = 0;
				res++;
			}
			// source doesn't contain this char
			if (idx[dest[i] - 'a'][0] == 0) 
				return -1;
			j = idx[dest[i] - 'a'][j];
			
			//left behind search again
			if (j == 0 ) {
				res++;
				i--;
			}
		}
		return  res;
	}
	
	/* binary Search
	 * store indices of occurrences of chars in source
	 * time 0(lgM * N)
	 */
	public int shortestWay2(String source, String target) {
		char[] src = source.toCharArray(), dest = target.toCharArray();
		int res = 1;
		List<Integer>[] idx = new List[26];
		for (int i = 0; i < 26; i++) 
			idx[i] = new ArrayList<>();
		
		// since source is traverse in ascending order of indices 
		// list[i] will contain indices in sorted manner
		for (int i = 0; i < src.length; i++) 
			idx[src[i] - 'a'].add(i);
		
		int j = 0;
		for (int i = 0; i < dest.length; ) {
			List<Integer> tar = idx[dest[i] - 'a'];
			// if empty source doesn't contain this charater
			if (tar.isEmpty()) 
				return -1;
			// get index
			// search for particular index in list
			int k = Collections.binarySearch(tar,j);
			
			// index is returned when match not found, get this insertion position
			if (k < 0) 
				k = -k - 1;
			// If this position is at end
			// character not found reset j and find this character again as it might be left behind
			if (k == tar.size()) {
				res++;
				j = 0;
			} else {
				// character found get to next character thus i++
				j = tar.get(k) + 1;
				i++;
			}

		}
		return res;
	}
	
    /* two Pointer
     * store in map array the character that source contains
     * every time source pointer reaches end increment result
     * time 0(MN)
     */
	public int shortestWay3(String source, String target) {
		char[] src = source.toCharArray(), dest = target.toCharArray();
		
		boolean[] map = new boolean[26];
		for (int i = 0; i < src.length; i++) 
			map[src[i] - 'a'] = true;
		
		int j = 0, res = 1;
		
		for (int i = 0; i < dest.length; i++,j++) {
			// if req char is not in source return -1
			if (!map[dest[i] - 'a']) 
				return -1;
			while (j < src.length && src[j] != dest[i]) {
				j++;
			}
			/* this means the required char is behind j or j have moved forward that char is behind
			 * thus reset j and search for this char i.e. target[i] again
			 * thus i--
			 */
			if (j == src.length) {
				j = -1;
				res++;
				i--;
			}
		}
		return res;
	}
}

public class Main {
	public static void main(String[] args) {
		String source = "xyz", target = "xyzxz";
		System.out.println(new Solution().shortestWay(source, target));
		System.out.println(new Solution().shortestWay1(source, target));
		System.out.println(new Solution().shortestWay2(source, target));
		System.out.println(new Solution().shortestWay3(source, target));
	}
}