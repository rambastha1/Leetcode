package main;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/swap-for-longest-repeated-character-substring/discuss/355922/C%2B%2B-2-approaches
class Solution {
	// 0(N) time
	public int maxRepOpt1(String text) {
		List<Integer>[]arr = new ArrayList[26];
		for(int i = 0;i < 26;i++)
			arr[i] = new ArrayList<>();
		int n = text.length();
		for(int i = 0;i < n;i++) {
			char c = text.charAt(i);
			arr[c-'a'].add(i);
		}
		
		int res = 1;
		for(int i = 0;i < 26;i++) {
			int count = 1, count1 = 0, max = 0;
			for(int j = 1;j < arr[i].size();j++) {
				if(arr[i].get(j) == arr[i].get(j-1) + 1)
					count++;
				else {
					count1 = arr[i].get(j) == arr[i].get(j-1) + 2?count:0;
					count = 1;
				}
				max = Math.max(max, count + count1);
			}
			res = Math.max(res, max + (arr[i].size() > max?1:0));
		}
		return res;
	}
	
	// 0(N^2)
	public int maxRepOpt12(String text) {
    	int []count = new int[26];
    	int n = text.length();
    	for(char c : text.toCharArray())
    		count[c-'a']++;
    	int maxlen = 0;
    	/* for each word count its frequency till diff is 1.
    	 */
    	for(int i = 0;i < n;i++) {
    		char c = text.charAt(i);
    		int j = i, num = 0, diff = 0;
    		while(j < n && (text.charAt(j) == c || diff == 0) && num < count[c-'a']) {
    			if(text.charAt(j) != c)
    				diff++;
    			num++;
    			j++;
    		}
    		maxlen = Math.max(maxlen, num);
    	}
    	return maxlen;
    }
}

public class Main {
	public static void main(String[] args) {
		//String text = "ababa";
		//String text = "aaabaaa";
		//String text = "aaabbaaa";
		String text = "abcdef";
		System.out.println(new Solution().maxRepOpt1(text));
	}
}
