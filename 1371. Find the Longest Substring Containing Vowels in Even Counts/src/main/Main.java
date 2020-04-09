package main;

import java.util.HashMap;
import java.util.Map;
/* cur records the count of "aeiou"
cur & 1 = the records of a % 2
cur & 2 = the records of e % 2
cur & 4 = the records of i % 2
cur & 8 = the records of o % 2
cur & 16 = the records of u % 2
seen note the index of first occurrence of cur

Note that we don't really need the exact count number,
we only need to know if it's odd or even.

If it's one of aeiou,
'aeiou'.find(c) can find the index of vowel,
cur ^= 1 << 'aeiou'.find(c) will toggle the count of vowel.

But for no vowel characters,
'aeiou'.find(c) will return -1,
that's reason that we do 1 << ('aeiou'.find(c) + 1) >> 1.
https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/discuss/531840/JavaC%2B%2BPython-One-Pass
 * 
 */
class Solution {
    public int findTheLongestSubstring(String s) {
    	// mapping -> index
    	Map<Integer, Integer> map = new HashMap<>();
    	/*
    	 * int is 32 bit and s contains only lower case
    	 * 00....uoiea(consonants)
    	 * 
    	 */
    	map.put(0, -1);
    	int curr = 0, ans = 0;
    	for(int i = 0;i < s.length();i++) {
    		char c = s.charAt(i);
    		curr ^= (1 << ("aeiou".indexOf(c) + 1)) >> 1;
    		map.putIfAbsent(curr, i);
    		ans = Math.max(ans, i - map.get(curr));
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "eleetminicoworoep";
		System.out.println(new Solution().findTheLongestSubstring(s));
	}
}
