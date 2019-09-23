package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// https://leetcode.com/problems/substring-with-concatenation-of-all-words/discuss/13658/Easy-Two-Map-Solution-(C%2B%2BJava)
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
    	List<Integer> res = new ArrayList<>();
    	if(s == null || words == null || s.length() == 0 || words.length == 0)
    		return res;
    		
    	Map<String, Integer> map = new HashMap<>();
    	for(String word : words)
    		map.put(word, map.getOrDefault(word, 0) + 1);
    	int m = s.length(), n = words.length, len = words[0].length();
    	for(int i = 0;i < m - n*len+1;i++) {
    		String str = s.substring(i, i+n*len);
    		if(isvalid(str, map, len))
    			res.add(i);
    	}
    	return res;
    }
    
    private boolean isvalid(String str, Map<String, Integer> map, int len) {
    	Map<String, Integer> seen = new HashMap<>();
    	for(int i = 0;i < str.length();i+= len) {
    		String s = str.substring(i, i+len);
    		seen.put(s, seen.getOrDefault(s, 0) + 1);
    		if(!map.containsKey(s) || seen.get(s) > map.get(s))
    			return false;
    	}
    	return true;
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "barfoothefoobarman";
		String []words = {"foo","bar"};
		System.out.println(new Solution().findSubstring(s, words));
	}
}