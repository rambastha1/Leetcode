package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* Given two sentences words1, words2 (each represented as an array of strings), 
 * and a list of similar word pairs pairs, determine if two sentences are similar.
 * 
 * For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are 
 * similar, if the similar word pairs are 
 * pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].
 * 
 * Note that the similarity relation is transitive. For example, if  
 * Also, a word is always similar with itself. For example, 
 * the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, 
 * even though there are no specified similar word pairs.
 * Finally, sentences can only be similar if they have the same number of words. 
 * So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].
 * Note:
 * The length of words1 and words2 will not exceed 1000.
 * The length of pairs will not exceed 2000.
 * The length of each pairs[i] will be 2.
 * The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 */

class Solution {
	public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
		if(words1.length != words2.length) return false;
		
		Map<String, HashSet<String>> map = new HashMap<>();
		for(String []pair : pairs) {
			if(!map.containsKey(pair[0]))
				map.put(pair[0], new HashSet<>());
			if(!map.containsKey(pair[1]))
				map.put(pair[1], new HashSet<>());
			map.get(pair[0]).add(pair[1]);
			map.get(pair[1]).add(pair[0]);
		}
		// for each word dfs to find target if not false
		for(int i = 0;i < words1.length;i++) {
			if(words1[i].equals(words2[i])) continue;
			else {
				if(!dfs(words1[i], words2[i], map, new HashSet<>()))
					return false; 
			}
		}
		return true;
	}
	
	boolean dfs(String word1, String word2, Map<String, HashSet<String>> map, HashSet<String> visited) {
		if(map.get(word1).contains(word2))
			return true;
		visited.add(word1);
		for(String dest : map.get(word1)) {
			if(!visited.contains(dest)) {
				if(dfs(dest, word2, map, visited))
					return true;
			}
		}
		return false;
	}
}

public class Main {
	public static void main(String[] args) {
		String []words1 = {"great", "acting", "skills"}, words2 = {"fine", "drama", "talent"};
		String [][]pairs = {{"great", "good"}, {"fine", "good"}, {"acting","drama"}, {"skills","talent"}};
		System.out.println(new Solution().areSentencesSimilarTwo(words1, words2, pairs));
	}
}
