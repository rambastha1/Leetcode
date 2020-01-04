package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */

interface Master {
	public int guess(String word);
}
// https://leetcode.com/problems/guess-the-word/discuss/133862/Random-Guess-and-Minimax-Guess-with-Comparison
/* Find the word with minimum number of mis-match among the words in the array
 * call guess and keep only those words which has same number of match with this word
 * and repeat the process
 */
class Solution {
    public void findSecretWord(String[] wordlist, Master master) {
    	// 10 because maximum allowed trial is 10
    	for(int i = 0, x = 0;i < 10 && x < 6;i++) {
    		// word -> count if completely mis-match
    		Map<String, Integer> map = new HashMap<>();
    		for(String w1 : wordlist) {
    			for(String w2 : wordlist) {
    				if(match(w1, w2) == 0) {
    					map.put(w1, map.getOrDefault(w1, 0) + 1);
    				}
    			}
    		}
    		// pick word with maximum number of matching among all words in wordlist
    		// 1000 because max length given
    		Pair<String, Integer> min = new Pair<String, Integer>("", 1000);
    		for(String w : wordlist) {
    			if(map.getOrDefault(w, 0) < min.getValue()) {
    				min = new Pair<String, Integer>(w, map.getOrDefault(w, 0));
    			}
    		}
    		// check with that number and keep all matching
    		x = master.guess(min.getKey());
    		List<String> list = new ArrayList<>();
    		for(String w : wordlist) {
    			if(match(min.getKey(), w) == x)
    				list.add(w);
    		}
    		wordlist = list.toArray(new String[0]);
    	}
    }
    
    private int match(String a, String b) {
    	int count = 0;
    	for(int i = 0;i < a.length();i++) {
    		if(a.charAt(i) == b.charAt(i))
    			count++;
    	}
    	return count;
    }
}

public class Main {
	public static void main(String[] args) {

	}
}
