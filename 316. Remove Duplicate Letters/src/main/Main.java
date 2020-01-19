package main;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
	public String removeDuplicateLetters(String s) {
    	int n = s.length();
    	if(n == 1)
    		return s;
    	Deque<Character> d = new ArrayDeque<>();
    	int []end = new int[26];
    	boolean []visited = new boolean[26];
    	
    	for(int i = 0;i < n;i++)
    		end[s.charAt(i) - 'a'] = i;
    	
    	for(int i = 0;i < n;i++) {
    		char c = s.charAt(i);
            if(visited[c-'a'])
    			continue;
    		while(!d.isEmpty() && c < d.peekLast() && end[d.peekLast()-'a'] > i) {
    			visited[d.peekLast()-'a'] = false;
    			d.pollLast();
    		}
            d.offerLast(c);
            visited[c-'a'] = true;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	while(!d.isEmpty())
    		sb.append(d.pollFirst());
    	return sb.toString();
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "abacb";
		System.out.println(new Solution().removeDuplicateLetters(s));
	}
}
