package main;

import java.util.ArrayDeque;
import java.util.Deque;

// remove elements from last if its larger and k > 0
// https://leetcode.com/problems/remove-k-digits/discuss/88708/Straightforward-Java-Solution-Using-Stack
// ngn comment
class Solution {
    public String removeKdigits(String num, int k) {
    	if(num.length() == k)
    		return "0";
    	Deque<Character> deque = new ArrayDeque<>();
    	for(char c : num.toCharArray()) {
    		while(k > 0 && !deque.isEmpty() && c < deque.peekLast()) {
    			deque.removeLast();
    			k--;
    		}
    		deque.offerLast(c);
    	}
    	
    	while(k > 0) {
    		deque.pollLast();
    		k--;
    	}
    	
    	while(!deque.isEmpty() && deque.peekFirst() == '0')
    		deque.pollFirst();
    	if(deque.isEmpty())
    		return "0";
    	
    	StringBuilder sb = new StringBuilder();
    	while(!deque.isEmpty()) {
    		sb.append(deque.pollFirst());
    	}
    	return sb.toString();
    }
}

public class Main {
	public static void main(String[] args) {
		String num = "1432219";
		int k = 3;
		System.out.println(new Solution().removeKdigits(num, k));
	}
}
