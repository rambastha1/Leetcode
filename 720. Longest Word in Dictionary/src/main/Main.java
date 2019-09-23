package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
	
	// Time 0(N*maxlen)
    public String longestWord(String[] words) {
    	if(words == null || words.length == 0)
    		return "";
    	
    	Map<String, Integer> map = new HashMap<>();
    	for(String word : words)
    		map.put(word, map.getOrDefault(word, 0)+1);
    	Arrays.sort(words);
    	String ans  = "";
    	for(int i = 0;i < words.length;i++) {
    		String word = words[i];
    		if(map.containsKey(word.substring(0, word.length()-1))) {
    			if(word.length() > ans.length())
    				ans = word;
    		}
    	}    	
    	
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		String []words = {"w","wo","wor","worl", "world"};
		//String []words = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
		System.out.println(new Solution().longestWord(words));
	}
}