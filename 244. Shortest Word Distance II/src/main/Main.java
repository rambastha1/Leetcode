package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Design a class which receives a list of words in the constructor, and implements a method 
 * that takes two words word1 and word2 and return the shortest distance between these 
 * two words in the list. Your method will be called repeatedly many times with different parameters. 
 * 
 * Example:
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * 
 * Input: word1 = “coding”, word2 = “practice”
 * Output: 3
 * 
 * Input: word1 = "makes", word2 = "coding"
 * Output: 1
 * 
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list. 
 */

class WordDistance {
	Map<String, List<Integer>> map;
	public WordDistance(String []words) {
		map = new HashMap<>();
		for(int i = 0;i < words.length;i++) {
			if(map.containsKey(words[i]))
				map.get(words[i]).add(i);
			else {
				List<Integer> list = new ArrayList<>();
				list.add(i);
				map.put(words[i], list);
			}
		}
	}
	
	public int shortest(String word1, String word2) {
		List<Integer> list1 = map.get(word1);
		List<Integer> list2 = map.get(word2);
		int i = 0, j = 0, min = Integer.MAX_VALUE;
		while(i < list1.size() && j < list2.size()) {
			min = Math.abs(list1.get(i) - list2.get(j));
			if(list1.get(i) <= list2.get(j))
				i++;
			else
				j++;
		}
		return min;
	}
}


public class Main {
	public static void main(String[] args) {
		String []words = {"practice", "makes", "perfect", "coding", "makes"};
		WordDistance w = new WordDistance(words);
		
		//String word1 = "coding", word2 = "practice";
		String word1 = "makes", word2 = "coding";
		System.out.println(w.shortest(word1, word2));
	}
}