package main;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Solution {
    public int findLUSlength(String[] strs) {
    	Arrays.sort(strs, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.length()-o1.length();
			}
		});
    	// string -> length
    	Map<String, Integer> map = new ConcurrentHashMap<>();
    	for(String str : strs) {
    		if(map.containsKey(str))
    			map.put(str, -1);
    		else {
    			for(String s : map.keySet()) {
    				if(s.compareTo(str) == 0)
    					break;
    				if(issubsequence(s, str))
    					map.put(str, -1);
    			}
    			if(!map.containsKey(str))
    				map.put(str, str.length());
    		}
    	}
    	int ans = -1;
    	for(int len : map.values())
    		ans = Math.max(ans, len);
    	return ans;
    }
    
    private boolean issubsequence(String a, String b) {
    	for(int i = 0, j = 0;i < a.length() && j < b.length();i++) {
    		if(a.charAt(i) == b.charAt(j))
    			j++;
    		if(j == b.length())
    			return true;
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		//String []strs = {"aba", "cdc", "eae"};
		//String []strs = {"aaa", "aaa", "aa"};
		String []strs = {"aabbcc", "aabbcc","cb","abc"};
		System.out.println(new Solution().findLUSlength(strs));
	}
}
