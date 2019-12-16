package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
 * 
 * https://leetcode.com/problems/sentence-similarity-ii/discuss/109752/JavaC%2B%2B-Clean-Code-with-Explanation
 * 
 * can be done using dfs as well just more complexity
 */

class Solution {
	// run through above example
	public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
		if(words1.length != words2.length) 
			return false;
		// word -> parent word
		Map<String, String> map = new HashMap<>();
		for(List<String> pair : pairs) {
			String str1 = find(map, pair.get(0));
			// notice difference
			String str2 = find(map, find(map, pair.get(1)));
			if(!str1.equals(str2))
				map.put(str1, str2);
		}
		
		for(int i = 0;i < words1.length;i++) {
			if(!words1[i].equals(words2[i]) && !find(map, words1[i]).equals(find(map, words2[i])))
				return false;
		}
		return true;
	}
	
	private String find(Map<String, String> map, String str) {
		// this is same as parent array where initially parent[i] = i
		if(!map.containsKey(str)) {
			map.put(str, str);
		}
		if(map.get(str).compareTo(str) != 0)
			map.put(str, find(map, map.get(str)));
		return map.get(str);
	}
}

public class Main {
	public static void main(String[] args) {
		String []words1 = {"great", "acting", "skills"}, words2 = {"fine", "drama", "talent"};
		List<List<String>>pairs = Arrays.asList(Arrays.asList("great", "good"), Arrays.asList("fine", "good"), 
				Arrays.asList("acting","drama"), Arrays.asList("skills","talent"));
		System.out.println(new Solution().areSentencesSimilarTwo(words1, words2, pairs));
	}
}
