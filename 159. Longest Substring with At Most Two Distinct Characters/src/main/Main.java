package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
    	int maxlen = 1, k = 2;
    	// Character -> count
    	Map<Character, Integer> map = new HashMap<>();
    	int start = 0, n = s.length();
    	for(int end = 0;end < n;end++) {
    		map.put(s.charAt(end), map.getOrDefault(s.charAt(end), 0)+1);
    		if(map.size() > k) {
    			map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
    			if(map.get(s.charAt(start)) == 0)
    				map.remove(s.charAt(start));
    			start++;
    		}
    		
    		maxlen = Math.max(maxlen, end-start+1);
    	}
    	return maxlen;
    }
}

public class Main {
	public static void main(String[] args) {
		//String s = "eceba";
		String s = "ccaabbb";
		System.out.println(new Solution().lengthOfLongestSubstringTwoDistinct(s));
	}
}
