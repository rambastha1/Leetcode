package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Given a text string and words (a list of strings), return all index pairs [i, j] so that the 
 * substring text[i]...text[j] is in the list of words.

 

Example 1:

Input: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
Output: [[3,7],[9,13],[10,17]]
Example 2:

Input: text = "ababa", words = ["aba","ab"]
Output: [[0,1],[0,2],[2,3],[2,4]]
Explanation: 
Notice that matches can overlap, see "aba" is found in [0,2] and [2,4].
 

Note:

All strings contains only lowercase English letters.
It's guaranteed that all strings in words are different.
1 <= text.length <= 100
1 <= words.length <= 20
1 <= words[i].length <= 50
Return the pairs [i,j] in sorted order (i.e. sort them by their first coordinate in case of ties sort them by their second coordinate).
 * 
 */

class Solution {
	
	class TrieNode {
	    boolean isWord;
	    Map<Character, TrieNode> child;
	    
	    public TrieNode() {
	        this.child = new HashMap<>();
	    }
	}
	
	TrieNode root = new TrieNode();
	
	public int[][] indexPairs(String text, String[] words) {
        // null empty
        
        for(String word : words) {
            insert(word);
        }
        
        List<int[]> list = new ArrayList<>();
        
        for(int i = 0; i < text.length(); i++)
            search(text, i, i, root, list);        
        return list.toArray(new int[0][]);
    }
    
    public void search(String text, int start, int i, TrieNode root, List<int[]> list) {
        if(i >= text.length()) 
        	return;
        
        char c = text.charAt(i);
        
        if(!root.child.containsKey(c))
        	return;
        
        if(root.child.get(c).isWord) 
        	list.add(new int[]{start, i});
        search(text, start, i+1, root.child.get(c), list);
    }
	
	public void insert(String word) {
        TrieNode curr = root;
        for(char c : word.toCharArray()) {
            if(!curr.child.containsKey(c))
            	curr.child.put(c, new TrieNode());
            curr = curr.child.get(c);
        }
        curr.isWord = true;
    }
}

public class Main {
	public static void main(String[] args) {
		String text = "thestoryofleetcodeandme";
		String []words = {"story","fleet","leetcode"};
		System.out.println(Arrays.toString(new Solution().indexPairs(text, words)));
	}
}