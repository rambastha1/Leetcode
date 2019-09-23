package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/discuss/372385/Java-Bit-manipulation-%2B-Map-Solution-90ms
class Solution {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
    	Map<Integer, Integer> map = new HashMap<>();
    	for(String w : words) {
    		int mask = 0;
    		for(int i = 0;i < w.length();i++)
    			mask |= 1 << (w.charAt(i) - 'a');
    		map.put(mask, map.getOrDefault(mask, 0)+1);
    	}
    	
    	List<Integer> res = new ArrayList<>();
    	for (String str : puzzles) {
    		int mask = 0;
    		for(int i = 0;i < str.length();i++) {
    			mask |= 1 << (str.charAt(i)-'a');
    		}
    		
    		int count = 0, sub = mask;
    		int first = 1 << (str.charAt(0)-'a');
    		while(true) {
    			/* irrespective of all other 1's if first(that) bit is 1 and this mask is in map, add count 
    			 */
    			if((sub & first) == first && map.containsKey(sub))
    				count += map.get(sub);
    			if(sub == 0)
    				break;
    			// this will give next lower sub
    			sub = (sub-1)&mask;
    		}
    		res.add(count);
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		String []words = {"aaaa","asas","able","ability","actt","actor","access"}, 
				puzzles = {"aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"};
		System.out.println(new Solution().findNumOfValidWords(words, puzzles));
	}
}