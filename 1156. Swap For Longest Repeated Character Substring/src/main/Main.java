package main;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/swap-for-longest-repeated-character-substring/discuss/355875/Java-solution-with-very-detail-explanation-O(n)-Time-beat-100

class Solution {
	
	class State {
		char c;
		int start, end;
		public State(char c, int start, int end) {
			this.c = c;
			this.start = start;
			this.end = end;
		}
	}
	
	// 0(N)
    public int maxRepOpt1(String text) {
    	List<State> list = new ArrayList<>();
    	int start = 0, end = 0;
    	char c = text.charAt(0);
    	int i = 1, n = text.length();
    	
    	int []count = new int[26];
    	// total count of each character 
    	count[c-'a']++;
    	
    	// a-2, b-1, a-2, b-1, a-1 character with start and end position
    	while(i < n) {
    		char curr = text.charAt(i);
    		count[curr-'a']++;
    		
    		if(curr != c) {
    			list.add(new State(c, start, end));
    			c = curr;
    			start = i;
    			end = i;
    		} else
    			end = i;
    		i++;
    	}
    	
    	list.add(new State(c, start, end));
    	int maxlen = 1;
    	
    	// scenario 1, aaabba, find another a to replace b to increase maxlen+1
    	for(i = 0;i < list.size();i++) {
    		State state = list.get(i);
    		int len = state.end - state.start + 1;
    		// this will give whether any character ('a' in this case) are still left
    		if(len < count[state.c - 'a'])
    			len++;
    		maxlen = Math.max(maxlen, len);
    	}
    	
    	// scenario 2, aabaabaa, find another a to replace b
    	for(i = 1;i < list.size()-1;i++) {
    		State prev = list.get(i-1), curr= list.get(i), next = list.get(i+1);
    		int prevlen = prev.end - prev.start + 1;
    		int nextlen = next.end - next.start + 1;
    		// this will give 'b' of length 1, check both sides if character are same, then check if any character still left 
    		if(curr.start == curr.end && prev.c == next.c) {
    			int totallen = prevlen + nextlen;
    			// this will give whether any character ('a' in this case) are still left 
    			if(prevlen + nextlen < count[prev.c-'a'])
    				totallen++;
    			maxlen = Math.max(maxlen, totallen);
    		}
    	}
    	return maxlen;
    }
	
	
	// 0(N^2)
	public int maxRepOpt12(String text) {
    	int []count = new int[26];
    	int n = text.length();
    	for(char c : text.toCharArray())
    		count[c-'a']++;
    	int maxlen = 0;
    	/* for each word count its frequency till diff is 1.
    	 */
    	for(int i = 0;i < n;i++) {
    		char c = text.charAt(i);
    		int j = i, num = 0, diff = 0;
    		while(j < n && (text.charAt(j) == c || diff == 0) && num < count[c-'a']) {
    			if(text.charAt(j) != c)
    				diff++;
    			num++;
    			j++;
    		}
    		maxlen = Math.max(maxlen, num);
    	}
    	return maxlen;
    }
}

public class Main {
	public static void main(String[] args) {
		//String text = "ababa";
		//String text = "aaabaaa";
		//String text = "aaabbaaa";
		String text = "abcdef";
		System.out.println(new Solution().maxRepOpt1(text));
	}
}
