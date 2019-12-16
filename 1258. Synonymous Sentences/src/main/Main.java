package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* Given a list of pairs of equivalent words synonyms and a sentence text, 
 * Return all possible synonymous sentences sorted lexicographically.
 

Example 1:

Input:
synonyms = [["happy","joy"],["sad","sorrow"],["joy","cheerful"]],
text = "I am happy today but was sad yesterday"
Output:
["I am cheerful today but was sad yesterday",
​​​​​​​"I am cheerful today but was sorrow yesterday",
"I am happy today but was sad yesterday",
"I am happy today but was sorrow yesterday",
"I am joy today but was sad yesterday",
"I am joy today but was sorrow yesterday"]
 

Constraints:

0 <= synonyms.length <= 10
synonyms[i].length == 2
synonyms[0] != synonyms[1]
All words consist of at most 10 English letters only.
text is a single space separated sentence of at most 10 words.
 * 
 */

class Solution {
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
    	List<String> res = new ArrayList<>();
    	if(synonyms.size() == 0) {
    		res.add(text);
    		return res;
    	}
    	
    	Map<String, List<String>> map = new HashMap<>();
    	for(List<String> list : synonyms) {
    		if(!map.containsKey(list.get(0)))
    			map.put(list.get(0), new ArrayList<>());
    		
    		if(!map.containsKey(list.get(1)))
    			map.put(list.get(1), new ArrayList<>());
    		map.get(list.get(0)).add(list.get(1));
    		map.get(list.get(1)).add(list.get(0));
    	}
    	
    	Set<String> visited = new HashSet<>();
    	String []arr = text.split("\\s");
    	res.add(text);
    	visited.add(text);
    	dfs(res, map, visited, arr);
    	Collections.sort(res);
    	return res;
    }
    /* match word in graph, use dfs to change like permutation
     * 
     */
    void dfs(List<String> res, Map<String, List<String>> map, Set<String> visited, String []arr) {
    	
    	for(int i = 0;i < arr.length;i++) {
    		if(!map.containsKey(arr[i]))
    			continue;
    		List<String> list = map.get(arr[i]);
    		String temp = arr[i];
    		for(String str : list) {
    			StringBuilder sb = new StringBuilder();
    			// create new string with this new word in place of old word
    			for(int k = 0;k < arr.length;k++) {
    				if(k == i) {
    					if(k != arr.length - 1)
    						sb.append(str + " ");
    					else
    						sb.append(str);
    				}
    				else if(k == arr.length - 1)
    					sb.append(arr[k]);
    				else
    					sb.append(arr[k] + " ");
    			}
    			System.out.println(sb.toString());
    			if(!visited.contains(sb.toString())) {
    				visited.add(sb.toString());
    				res.add(sb.toString());
    				arr[i] = str;
    				dfs(res, map, visited, arr);
    				arr[i] = temp;
    			}
    		}
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		String text = "a c e";
		List<List<String>> synonyms = Arrays.asList(Arrays.asList("a","b"), Arrays.asList("c","d"), 
				Arrays.asList("e","f"));
		/*String text = "I am happy today but was sad yesterday";
		List<List<String>> synonyms = Arrays.asList(Arrays.asList("happy","joy"), Arrays.asList("sad","sorrow"), 
				Arrays.asList("joy","cheerful"));*/
		System.out.println(new Solution().generateSentences(synonyms, text));
	}
}