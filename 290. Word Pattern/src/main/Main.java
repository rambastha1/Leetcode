package main;

import java.util.HashMap;
import java.util.Map;

// find replace string
class Solution {
    public boolean wordPattern(String pattern, String str) {
    	Map<String, String> map = new HashMap<>();
    	String []strs = str.split(" ");
    	if(strs.length != pattern.length())
    		return false;
    	
    	for(int i = 0;i < pattern.length();i++) {
    		String a = String.valueOf(pattern.charAt(i)-'a');
    		String b = strs[i];
    		if(!map.containsKey(a))
    			map.put(a, b);
    		if(!map.containsKey(b))
    			map.put(b, a);
    		if(!map.get(a).equals(b) || !map.get(b).equals(a))
    			return false;
    	}
    	return true;
    }
}

public class Main {
	public static void main(String[] args) {
		String pattern = "abba", str = "dog cat cat dog";
		System.out.println(new Solution().wordPattern(pattern, str));
	}
}