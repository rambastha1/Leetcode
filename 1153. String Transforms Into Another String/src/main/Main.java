package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

// https://leetcode.com/problems/string-transforms-into-another-string/discuss/355374
class Solution {
    public boolean canConvert(String str1, String str2) {
    	if(str1.length() == 1 || str1.compareTo(str2) == 0)
    		return true;
    	// character mapping from str1 -> str2
    	Map<Character, Character> map = new HashMap<>();
    	for(int i = 0;i < str1.length();i++) {
    		char a = str1.charAt(i);
    		char b = str2.charAt(i);
    		if(map.containsKey(a) && map.get(a) != b)
    			return false;
    		map.put(a, b);
    	}
    	/* the mapping may lead to cycle. In that case return false
    	 * no cycle is possible only if str2 uses less than 26 characters
    	 */
    	return new HashSet<Character>(map.values()).size() < 26;
    }
}
public class Main {
	public static void main(String[] args) {
		//String str1 = "aabcc", str2 = "ccdee";
		String str1 = "leetcode", str2 = "codeleet";
		System.out.println(new Solution().canConvert(str1, str2));
	}
}