package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    public String[] uncommonFromSentences(String A, String B) {
    	if(A == null || B == null || A.length() == 0 || B.length() == 0)
    		return new String[] {};
    	
    	Map<String, Integer> map = new HashMap<>();
    	String []str = B.split("\\s");
    	for(String s : str) {
    		if(map.containsKey(s))
    			map.put(s, -1);
    		else
    			map.put(s, 1);
    	}
    	
    	str = A.split("\\s");
    	for(String s : str) {
    		if(map.containsKey(s))
    			map.put(s, -1);
    		else
    			map.put(s, 1);
    	}
    	List<String> list = new ArrayList<>();
    	for(String s : map.keySet()) {
    		if(map.get(s) > 0)
    			list.add(s);
    	}
    	
    	System.out.println(list);
    	String []res = new String[list.size()];
    	for(int i = 0;i < list.size();i++)
    		res[i] = list.get(i);
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		//String A = "this apple is sweet", B = "this apple is sour";
		String A = "s z z z s", B = "s z ejt";
		new Solution().uncommonFromSentences(A, B);
	}
}
