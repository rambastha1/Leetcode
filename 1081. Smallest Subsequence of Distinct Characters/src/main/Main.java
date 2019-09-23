package main;

import java.util.Stack;
// https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/discuss/308210/JavaPython-Stack-Solution-O(N)

/* if eg "cdaabcc"
 * after pushing cd no need to pop c as cdab < dabc
 * ans we find char < stack.peek() it means all element beneath top are less than peek
 * i.e lexicographically smaller
 */
class Solution {
    public String smallestSubsequence(String text) {
    	Stack<Integer> stack = new Stack<>();
    	int []last = new int[26];
    	boolean []seen = new boolean[26];
    	for(int i = 0;i < text.length();i++)
    		last[text.charAt(i)-'a'] = i;
    	
    	for(int i = 0;i < text.length();i++) {
    		int index = text.charAt(i)-'a';
    		if(seen[index]) continue;
    		
    		while(!stack.isEmpty() && stack.peek() > index && i < last[stack.peek()]) {
    			seen[stack.pop()] = false;
    		}
    		
    		stack.push(index);
    		seen[index] = true;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for(int i : stack)
    		sb.append((char)('a'+i));
    	return sb.toString();
    }
}

public class Main {
	public static void main(String[] args) {
		String text = "cdadabcc";
		System.out.println(new Solution().smallestSubsequence(text));
	}
}
