package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.

Begin with the first character and then the number of characters abbreviated, which followed by the last character.
If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of 
only the first character until making the map from word to abbreviation become unique. 
In other words, a final abbreviation cannot map to more than one original words.
If the abbreviation doesn't make the word shorter, then keep it as original.
Example:
Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
Note:
Both n and the length of each word will not exceed 400.
The length of each word is greater than 1.
The words consist of lowercase English letters only.
The return answers should be in the same order as the original array.
 * https://leetcode.com/problems/word-abbreviation/discuss/99792/HashMap-%2B-Trie-greater-O(nL)-solution
 */

/* idea is to build trie of all words, at each node abbr string with count
 * while traversing if count of abbr word at node equals 1 means unique thus return abbr string
 * 
 */

class Solution {
	
	class TrieNode {
		Map<Character, TrieNode> child;
		Map<String, Integer> map;
		public TrieNode() {
			this.child = new HashMap<>();
			this.map = new HashMap<>();
		}
	}
	
	TrieNode root = new TrieNode();
	
    public List<String> wordsAbbreviation(List<String> dict) {
    	List<String> res = new ArrayList<>();
    	for(String str : dict)
    		insert(str);
    	for(String str : dict) {
    		res.add(getabbr(str));
    	}
    	return res;
    }
    
    private String getabbr(String str) {
    	if(str.length() <= 3)
    		return str;
    	TrieNode curr = root;
    	curr = curr.child.get(str.charAt(0));
    	for(int i = 1;i < str.length()-2;i++) {
    		char c = str.charAt(i);
    		String abbr = str.length() + " " + str.charAt(str.length()-1);
    		if(curr.map.get(abbr) == 1) {
    			return str.substring(0, i) + (str.length() - i - 1) + str.charAt(str.length()-1);
    		}
    		curr = curr.child.get(c);
    	}
    	return str;
    }
    
    private void insert(String str) {
    	TrieNode curr = root;
    	for(char c : str.toCharArray()) {
    		if(curr.child.get(c) == null)
    			curr.child.put(c, new TrieNode());
    		curr = curr.child.get(c);
    		String abbr = str.length() + " " + str.charAt(str.length()-1);
    		curr.map.put(abbr, curr.map.getOrDefault(abbr, 0) + 1);
    	}
    }
}

/**
 * To resolve conflicts in a group, we could build a trie for all the words
 * in the group. The trie node will contain the count of words that has the
 * same prefix. Then we could use this trie to determine when we could resolve
 * a conflict by identifying that the count of words in that trie node will only
 * have one word has the prefix.
 */
/* public class Solution {
    
    public List<String> wordsAbbreviation(List<String> dict) {
        Map<String, List<Integer>> abbrMap = new HashMap<>();
        // 1) create result set
        List<String> res = new ArrayList<>(Collections.nCopies(dict.size(), null));
        // 2) Group all words with the same shortest abbreviation. For example:
        // "internal", "interval" => grouped by "i6l"
        // "intension", "intrusion" => grouped by "i7n"
        // "god" => grouped by "god"
        // we can notice that only words with the same length and the same start
        // and end letter could be grouped together
        for (int i = 0; i < dict.size(); i ++) {
            String word = dict.get(i);
            String st = getShortestAbbr(word);
            List<Integer> pos = abbrMap.get(st);
            if (pos == null) {
                pos = new ArrayList<>();
                abbrMap.put(st, pos);
            }
            pos.add(i);
        }
        // 3) Resolve conflicts in each group
        for (Map.Entry<String, List<Integer>> entry : abbrMap.entrySet()) {
            String abbr = entry.getKey();
            List<Integer> pos = entry.getValue();
            resolve(dict, res, abbr, pos);
        }
        return res;
    }
    
    
    private void resolve(List<String> dict, List<String> res, String abbr, List<Integer> pos) {
        if (pos.size() == 1) {
            res.set(pos.get(0), abbr);
        } else {
            Trie trie = buildTrie(dict, pos);
            for (int p : pos) {
                String w = dict.get(p);
                Trie cur = trie;
                int i = 0;
                int n = w.length();
                // while loop to find the trie node which only has the 1 word which has
                // the prefix. That means in that position, only current word has that
                // specific character.
                while (i < n && cur.next.get(w.charAt(i)).cnt > 1) {
                    cur = cur.next.get(w.charAt(i));
                    i ++;
                }
                if (i >= n - 3) {
                    res.set(p, w);
                } else {
                    String pre = w.substring(0, i+1);
                    String st = pre + (n - i - 2) + "" + w.charAt(n - 1);
                    res.set(p, st);
                }
            }
        }
    }
    
    private String getShortestAbbr(String s) {
        if (s.length() <= 3) {
            return s;
        } else {
            return s.charAt(0) + "" + (s.length() - 2) + "" + s.charAt(s.length() - 1);
        }
    }
    
     // with the information of the count of words with the same prefix.
    private Trie buildTrie(List<String> dict, List<Integer> pos) {
        Trie root = new Trie();
        for (int p : pos) {
            String w = dict.get(p);
            Trie cur = root;
            for (int i = 0; i < w.length(); i ++) {
                char c = w.charAt(i);
                if (cur.next.containsKey(c)) {
                    cur = cur.next.get(c);
                } else {
                    Trie next = new Trie();
                    cur.next.put(c, next);
                    cur = next;
                }
                cur.cnt ++;
            }
        }
        return root;
    }
    
    private class Trie {
        int cnt = 0;
        Map<Character, Trie> next = new HashMap<>();
    }
}

 * 
 */

public class Main {
	public static void main(String[] args) {
		List<String> dict = Arrays.asList("like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion");
		System.out.println(new Solution().wordsAbbreviation(dict));
	}
}