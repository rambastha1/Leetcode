package main;

import java.util.Arrays;
import java.util.List;

class Solution {
	public String findLongestWord(String s, List<String> d) {
    	if(s == null || d == null || s.length() == 0 || d.size() == 0)
    		return "";
    	String ans = "";
    	for(int i = 0;i < d.size();i++) {
    		if(issubsequence(s, d.get(i))) {
    			if(d.get(i).length() > ans.length() || 
    					(d.get(i).length() == ans.length() && d.get(i).compareTo(ans) < 0))
    				ans = d.get(i);
    		}
    	}
    	return ans;
    }
	
	boolean issubsequence(String s, String d) {
		if(s.length() == 0 && d.length() == 0)
			return true;
		int j = 0;
		for(int i = 0;i < s.length();i++) {
			if(s.charAt(i) == d.charAt(j))
				j++;
			if(j == d.length())
				return true;
		}
		return false;
	}
}

public class Main {
	public static void main(String[] args) {
		String s = "abpcplea";
		List<String> d = Arrays.asList("ale","apple","monkey","plea");
		System.out.println(new Solution().findLongestWord(s, d));
	}
}