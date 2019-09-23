package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
    	List<String> ans = new ArrayList<>();
    	if(words.length == 0 || words == null || pattern.length() == 0)
    		return ans;
    	for(String word : words) {
    		if(match(word, pattern))
    			ans.add(word);
    	}
    	return ans;
    }
    
    public boolean match(String word, String pattern) {
    	
    	if(word.length() != pattern.length())
    		return false;
    	Map<Character, Character> m1 = new HashMap<>();
    	Map<Character, Character> m2 = new HashMap<>();
    	for(int i = 0;i < word.length();i++) {
    		char w = word.charAt(i);
    		char p = pattern.charAt(i);
    		if(!m1.containsKey(w))
    			m1.put(w, p);
    		if(!m2.containsKey(p))
    			m2.put(p, w);
    		if(m1.get(w) != p || m2.get(p) != w)
    			return false;
    	}
    	return true;
    }
}


public class Main {
	public static void main(String[] args) {
		String []words = {"abc","deq","mee","aqq","dkd","ccc"};
		String pattern = "abb";
		System.out.println(new Solution().findAndReplacePattern(words, pattern));
	}
}