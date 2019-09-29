package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
	// can be done using map but this saves memory and time
    public int calculateTime(String keyboard, String word) {
    	
    	int []index = new int[26];
    	for(int i = 0;i < keyboard.length();i++) {
    		char c = keyboard.charAt(i);
    		index[c-'a'] = i;
    	}
    	int ans = index[word.charAt(0)-'a'];
    	int last = ans;
    	for(int i = 1;i < word.length();i++) {
    		int curr = index[word.charAt(i)-'a'];
    		ans += Math.abs(curr - last);
    		last = curr;
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		//String keyboard = "abcdefghijklmnopqrstuvwxyz", word = "cba";
		String keyboard = "pqrstuvwxyzabcdefghijklmno", word = "leetcode";
		System.out.println(new Solution().calculateTime(keyboard, word));
	}
}