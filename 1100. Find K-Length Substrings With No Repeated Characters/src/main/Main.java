package main;

import java.util.HashMap;
import java.util.Map;

/* Given a string S, return the number of substrings of length K with no repeated characters.

 

Example 1:

Input: S = "havefunonleetcode", K = 5
Output: 6
Explanation: 
There are 6 substrings they are : 'havef','avefu','vefun','efuno','etcod','tcode'.
Example 2:

Input: S = "home", K = 5
Output: 0
Explanation: 
Notice K can be larger than the length of S. In this case is not possible to find any substring.
 

Note:

1 <= S.length <= 10^4
All characters of S are lowercase English letters.
1 <= K <= 10^4
 * 
 */

class Solution {
    public int numKLenSubstrNoRepeats(String S, int K) {
    	if(K > S.length())
    		return 0;
    	
    	int start = 0, ans = 0;
    	int n = S.length();
    	// Character -> Index
    	Map<Character, Integer> map = new HashMap<Character, Integer>();
    	
    	for(int end = 0;end < n;end++) {
    		char c = S.charAt(end);
    		while(map.containsKey(c) || map.size() >= K) {
    			map.remove(S.charAt(start++));
    		}
    		map.put(c, end);
    		if(map.size() == K)
    			ans++;
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "havefunonleetcode";
		int K = 5;
		System.out.println(new Solution().numKLenSubstrNoRepeats(S, K));
	}
}