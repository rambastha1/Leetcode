package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". 
 * We can keep "shifting" which forms the sequence:
 * 
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same 
 * shifting sequence.

 * For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
 * Return:

 * [ ["abc","bcd","xyz"],
     ["az","ba"],
     ["acef"],
     ["a","z"]]
 * 
 * Note: For the return value, each inner list's elements must follow the lexicographic order.
 */

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
    	Map<String, List<String>> map = new HashMap<>();
    	List<List<String>> res = new ArrayList<>();
    	
    	if(strings == null || strings.length == 0)
    		return res;
    	
    	for(String str : strings) {
    		if(str.length() == 1) {
    			if(!map.containsKey("1")) {
    				map.put("1", new ArrayList<>());
    			}
    			map.get("1").add(str);
    			map.put("1", map.get("1"));
    		} else {
    			StringBuilder sb = new StringBuilder();
    			for(int i = 1;i < str.length();i++) {
    				sb.append((str.charAt(i)+26 - str.charAt(i-1))%26);
    			}
    			String key = sb.toString();
    			if(!map.containsKey(key)) {
    				map.put(key, new ArrayList<>());
    			}
    			map.get(key).add(str);
    			map.put(key, map.get(sb.toString()));
    		}
    	}
    	
    	for(List<String> list : map.values()) {
    		Collections.sort(list);
    		res.add(list);
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		String []strings = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
		System.out.println(new Solution().groupStrings(strings));
	}
}