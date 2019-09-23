package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {        
    	Map<String, List<String>> res = new HashMap<>();
    	int []count = new int[26];
    	
    	for(String s : strs) {
    		Arrays.fill(count, 0);
    		for(char c : s.toCharArray())
    			count[c - 'a']++;
    		
    		StringBuilder sb = new StringBuilder();
    		for(int i = 0;i < 26;i++)
    			sb.append(String.valueOf(count[i]));
    		String str = sb.toString();
    		if(!res.containsKey(str))
    			res.put(str, new ArrayList<>());
    		res.get(str).add(s);
    	}
    	return new ArrayList<>(res.values());
    }
}

public class Main {
	public static void main(String[] args) {
		String []strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
		List<List<String>> res = new Solution().groupAnagrams(strs);
		for(List list : res) {
			for(int i = 0;i < list.size();i++)
				System.out.print(list.get(i) + " ");
			System.out.println();
		}		
	}
}