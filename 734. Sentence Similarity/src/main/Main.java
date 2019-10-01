package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* Given two sentences words1, words2 (each represented as an array of strings), and a list of similar 
 * word pairs pairs, determine if two sentences are similar.
 * For example, “great acting skills” and “fine drama talent” are similar, if the similar word pairs are 
 * pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].
 * Note that the similarity relation is not transitive. For example, if “great” and “fine” are similar, 
 * and “fine” and “good” are similar, “great” and “good” are not necessarily similar.
 * However, similarity is symmetric. For example, “great” and “fine” being similar is the same as “fine” 
 * and “great” being similar.
 * Also, a word is always similar with itself. For example, the sentences words1 = ["great"], 
 * words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.
 * Finally, sentences can only be similar if they have the same number of words. So a 
 * sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].
 * Note:
 * The length of words1 and words2 will not exceed 1000.
 * The length of pairs will not exceed 2000.
 * The length of each pairs[i] will be 2.
 * The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 */

class Solution {
	public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
		if(words1.length != words2.length)
			return false;
		// store mapping of pairs
		Map<String, HashSet<String>> map = new HashMap<>();
		for(List<String> pair : pairs) {
			if(!map.containsKey(pair.get(0)))
				map.put(pair.get(0), new HashSet<>());
			map.get(pair.get(0)).add(pair.get(1));
			
			if(!map.containsKey(pair.get(1)))
				map.put(pair.get(1), new HashSet<>());
			map.get(pair.get(1)).add(pair.get(0));
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < words1.length;i++) {
			if(words1[i].equals(words2[i]))
				continue;
			if(!map.containsKey(words1[i]) || !map.get(words1[i]).contains(words2[i]))
				return false;
		}
		
		return true;
	} 
}

public class Main {
	public static void main(String[] args) {
		String []words1 = {"great", "acting", "skills"}, words2 = {"fine", "drama", "talent"};
		List<List<String>>pairs = Arrays.asList(Arrays.asList("great", "fine"), Arrays.asList("acting","drama"), 
				Arrays.asList("skills","talent"));
		System.out.println(new Solution().areSentencesSimilar(words1, words2, pairs));
				
	}
}
