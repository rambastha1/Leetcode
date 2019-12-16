package main;

import java.util.Arrays;

class Solution {
	/* the question asks to find score of group of words which can be formed using the letters just once 
	 * loop through word by word add score and compare
	 */
	// Time 0(2^n * K) k-> number of words
	// worst case -> iterate over all the characters of all the words
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
    	int []count = new int[26];
    	for(char c : letters)
    		count[c-'a']++;
    	return dfs(words, 0, count, score);
    }
    
    private int dfs(String []words, int index, int []count, int []score) {
    	if(index == words.length)
    		return 0;
    	int res = 0;
    	loop1:
    		for(int i = index;i < words.length;i++) {
    			int []temp = Arrays.copyOf(count, count.length);
    			int curr = 0;
    			for(char c : words[i].toCharArray()) {
    				if(temp[c-'a'] <= 0)
    					continue loop1;
    				temp[c-'a']--;
    				curr += score[c-'a'];
    			}
    			curr += dfs(words, i+1, temp, score);
    			res = Math.max(res, curr);
    		}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		String []words = {"dog","cat","dad","good"};
		char []letters = {'a','a','c','d','d','d','g','o','o'};
		int []score = {1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0};
		System.out.println(new Solution().maxScoreWords(words, letters, score));
	}
}