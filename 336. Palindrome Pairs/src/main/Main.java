package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://www.geeksforgeeks.org/palindrome-pair-in-an-array-of-words-or-strings/
// https://leetcode.com/problems/palindrome-pairs/discuss/79195/O(n-*-k2)-java-solution-with-Trie-structure
class Solution {
	
	public class TrieNode {
	    Map<Character, TrieNode> child;
	    int index;
	    List<Integer> list;
	    	
	    TrieNode() {
	    	child = new HashMap<>();
	    	index = -1;
	    	list = new ArrayList<>();
	    }
	}
	    
	public List<List<Integer>> palindromePairs(String[] words) {
	    List<List<Integer>> res = new ArrayList<>();

	    TrieNode root = new TrieNode();
	    for (int i = 0; i < words.length; i++)
	        addWord(words[i], root, i);
			
	    for (int i = 0; i < words.length; i++)
	        search(words, i, root, res);

	    return res;
	}
	    
	private void addWord(String word, TrieNode root, int index) {
		//TrieNode curr = root;
	    for (int i = word.length() - 1; i >= 0; i--) {
	        char c = word.charAt(i);
					
	        if (!root.child.containsKey(c)) {
	            root.child.put(c, new TrieNode());
	        }
					
	        if (isPalindrome(word, 0, i)) {
	            root.list.add(index);
	        }
	        root = root.child.get(c);
	    }
	    root.list.add(index);
	    root.index = index;
	}
	    
	private void search(String[] words, int index, TrieNode root, List<List<Integer>> res) {
		//TrieNode curr = root;
	    for (int i = 0; i < words[index].length(); i++) {	
	    	if (root.index >= 0 && root.index != index && isPalindrome(words[index], i, words[index].length() - 1)) {
	    	    res.add(Arrays.asList(index, root.index));
	    	}
	    	
	    	root = root.child.get(words[index].charAt(i));
	      	if (root == null) 
	      		return;
	    }
	    	
	    for (int i : root.list) {
	    	if (index == i) 
	    		continue;
	    	res.add(Arrays.asList(index, i));
	    }
	}
	    
	private boolean isPalindrome(String word, int i, int j) {
	    while (i < j) {
	    	if (word.charAt(i++) != word.charAt(j--)) 
	    		return false;
	    }
	    return true;
	}
}

public class Main {
	public static void main(String[] args) {
		String []words = {"bat","tab","cat"};
		System.out.println(new Solution().palindromePairs(words));
	}
}
