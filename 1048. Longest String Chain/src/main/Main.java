package main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Solution {
	/*
	 * We can put words into map upfront and check maximum with value
	 */
	// Time 0 (N*Maxlen) Space 0(N)
	
	public int longestStrChain(String[] words) {
    	Map<String, Integer> map = new HashMap<>();
    	Arrays.sort(words, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}    		
		});    	
    	
    	for(String word : words) {
    		int max = 0;
    		for(int j = 0;j < word.length();j++) {
    			String temp = word.substring(0, j) + word.substring(j+1);
    			// Value is wrong when use this statement
    			/*if(map.containsKey(temp)) {
    				max = Math.max(max, map.get(temp) + 1);
    			}*/
    			max = Math.max(max, map.getOrDefault(temp, 0) + 1);
    		}
    		map.put(word, max);
    	}
    	int max = Integer.MIN_VALUE;
    	for(int val : map.values())
    		max = Math.max(max, val);
    	return max;
    }
}

public class Main {
	public static void main(String[] args) {
		String []words = {"a","b","ba","bca","bda","bdca"};
		//String []words = {"cefkp","efkp","pkacefkep","pkacefkp","pacefkp","acefkp"};
		/*String []words = {"ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh",
				"gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"};*/
		System.out.println(new Solution().longestStrChain(words));
	}
}