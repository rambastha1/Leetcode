package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
	
	/*
	 * Using Map Sliding Window
	 * Instead of using a set to tell if a character exists or not, 
	 * we could define a mapping of the characters to its index. 
	 * Then we can skip the characters immediately when we found a repeated character.
	 * The reason is that if s[j] have a duplicate in the range [i, j) with index j', 
	 * we don't need to increase 'i' little by little. 
	 * We can skip all the elements in the range [i, j'] and let 'i' to be j'+1 directly.
	 * Time : 0(N) : n - length of string
	 * Space : 0(min(m,n)) m - number of charset
	 * 
	 * https://leetcode.com/articles/longest-substring-without-repeating-characters/
	 */
	
    public int lengthOfLongestSubstring(String s) {
    	if(s == null || s.length() == 0)
    		return 0;
    	if(s.length() == 1)
    		return 1;
    	int ans = 0;
    	Map<Character, Integer> map = new HashMap<>();    	
    	for(int start = 0, end = 0;end < s.length();end++) {
    		if(map.containsKey(s.charAt(end))) {
    			start = Math.max(map.get(s.charAt(end)), start) + 1;
    		}
    		ans = Math.max(ans, end-start+1);
    		map.put(s.charAt(end), end); 
    	}
    	/*Map<Integer, Integer> sortedMap = 
    		     map.entrySet().stream()
    		    .sorted(Entry.comparingByValue())
    		    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
    		                              (e1, e2) -> {return e1-e2;}, LinkedHashMap::new));*/
    	return ans;
    }
	
	
	
	/*
	 * (Assuming ASCII 128)
	 * The previous implements all have no assumption on the charset of the string s.
	 * If we know that the charset is rather small, we can replace the Map with an integer array as 
	 * direct access table.
	 * Commonly used tables are:
	 * int[26] for Letters 'a' - 'z' or 'A' - 'Z'
	 * int[128] for ASCII
	 * int[256] for Extended ASCII
	 * Same approach using map, but use array in place of hashmap
	 * Time : 0(N) : n - length of string
	 * Space : 0(m) m - number of charset 
	 */
	
	public int length(String s) {
		int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
	}
	
	/*
	 *  We use HashSet to store the characters in current window [i, j)(j=i initially). 
	 *  Then we slide the index j to the right. If it is not in the HashSet, we slide j further. 
	 *  Doing so until s[j] is already in the HashSet. 
	 *  At this point, we found the maximum size of substrings without duplicate characters start 
	 *  with index ii. If we do this for all ii, we get our answer.
	 */
	
	/*public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }*/
}

public class Main {
	public static void main(String[] args) {
		//String s = "GEEKSFORGEEKS";
		//String s = "ABDEFGABEF";
		String s = "dvdf";
		System.out.println(new Solution().lengthOfLongestSubstring(s));
	}
}