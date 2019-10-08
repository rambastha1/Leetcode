package main;

import java.util.Map;
import java.util.Stack;
import javafx.util.*;

class Solution {
	/* idea: 
        1. use fast pointer to traverse the string (cur)
        2. use slow pointer to remove and replace duplicate
        3. use count[] to record number of cur repeated. 
	 */
	public String removeDuplicates(String s, int k) {
		if(s.length() < k)
			return s;
		int n= s.length(), slow = 0;
		char []arr = s.toCharArray();
		int []count = new int[n];
		
		for(int fast = 0;fast < n;slow++, fast++) {
			arr[slow] = arr[fast];
			if(slow > 0 && arr[slow] == arr[slow-1]) {
				count[slow] = count[slow-1] + 1;
			} else {
				count[slow] = 1;
			}
			
			if(count[slow] == k)
				slow -= k;
		}
		return new String(arr, 0, slow);
	}
	
    /*public String removeDuplicates(String s, int k) {
    	if(s.length() < k)
    		return s;
    	int n = s.length();
    	Stack<Pair<Character, Integer>> stack = new Stack<>();
    	stack.push(new Pair<Character, Integer>(s.charAt(0), 1));
    	
    	for(int i = 1;i < n;i++) {
    		char c = s.charAt(i);
    		if(!stack.isEmpty() && c == stack.peek().getKey()) {
    			if(!stack.isEmpty() && stack.peek().getValue() >= k-1) {
    				int count = 1;
    				while(!stack.isEmpty() && count < k && stack.peek().getKey() == c) {
    					stack.pop();
    					count++;
    				}
    			} else {
    				stack.push(new Pair<Character, Integer>(c, stack.peek().getValue() + 1));
    			}
    		} else {
				stack.push(new Pair<Character, Integer>(c, 1));
			}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	while(!stack.isEmpty()) {
    		sb.append(stack.pop().getKey());
    	}
    	
    	return sb.reverse().toString();
    }*/
}

public class Main {
	public static void main(String[] args) {
		/*String s = "abcd";
		int k = 2;*/
		
		String s = "deeedbbcccbdaa";
		int k = 3;
		System.out.println(new Solution().removeDuplicates(s, k));
	}
}