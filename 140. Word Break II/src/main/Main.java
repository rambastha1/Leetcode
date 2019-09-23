package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
	public List<String> wordBreak(String s, List<String> wordDict) {
		Map<String, List<String>> map = new HashMap<>();
		return Util(s, wordDict, map);		
	}
	
	/*
	 * s.substring(int begin_index)
	 * This will first find "apple" in dictionary, recursively call for prior string
	 * then at "pen" call for string prior to "pen"
	 * then "pineapple"
	 * for "pen" it will also call for "applepen"
	 * then again "apple" call for string prior to "apple"
	 * then for "pine". At pine s.length becomes 0 thus returns empty list for curr
	 * thereby adding "pine" to res. map.put("pine", ["pine"])
	 * It will return to "apple", it has curr list ["pine"] 
	 * res becomes ["pine apple"]
	 * then return to "pineapple" curr is [""]
	 * res becomes ["pineapple"]
	 * for "pen" curr has two elements is ["pine apple"] and ["pineapple"]
	 * res becomes ["pine apple pen"] and ["pineapple pen"]
	 * There is also one for s.substring(0,12)
	 * for this res is ["pine applepen"]
	 * Finally for last apple append "apple" to these three curr list   
	 */
	
	// Time 0((worddict length) ^ (min(str, length of str)))
	public List<String> Util(String s, List<String> wordDict, Map<String, List<String>> map) {
		if(map.containsKey(s))
			return map.get(s);
		
		List<String> res = new ArrayList<>();
		if(s.length() == 0) {
			
			/* Very important line
			 * If this line is commented, final result is empty list
			 * It is because for all base cases, res is empty, thus the if and else 
			 * condition will fail.
			 */
			res.add("");
			return res;
		}
		
		for(int i = s.length();i >= 0;i--) {
			String str = s.substring(i);
			if(wordDict.contains(str)) {
				List<String> curr = Util(s.substring(0, i), wordDict, map);
				for(String string : curr) {
					if(string.equals(""))
						res.add(str);
					else
						res.add(string + " " + str);
				}
			}
		}
		map.put(s, res);
		return res;
	}
}

public class Main {
	public static void main(String[] args) {
		String s = "pineapplepenapple";
		List<String> wordDict = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
		System.out.println(new Solution().wordBreak(s, wordDict));
	}
}