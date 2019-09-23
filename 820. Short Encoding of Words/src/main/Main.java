package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
	
	class TrieNode {
		int len;
		Map<Character, TrieNode> children;
		public TrieNode() {
			children = new HashMap<>();
		}
	}
	public TrieNode root = new TrieNode();
	int ans = 0;
	
	void build(String word) {
		TrieNode node = root;
		//Check to see if new child is added
		// If all child already created this word is substring of other string		
		boolean flag = false;
		for(int i = word.length()-1;i >= 0;i--) {
			char c = word.charAt(i);
			if(node.children.get(c) == null) {
				node.children.put(c, new TrieNode());
				flag = true;
			}
			node = node.children.get(c);
			if(node.len > 0 && i != 0) {
				ans -= node.len + 1;
				node.len = 0;
			}
		}
		if(flag == false) return;
		node.len = word.length();
		ans+= node.len + 1;		
	}
	
    public int minimumLengthEncoding(String[] words) {
    	if(words == null || words.length == 0)
    		return 0;
    	for(String word : words)
    		build(word);
    	// No need to traverse again
    	// use global variable ans
    	// return dfs(root);
    	return ans;
    }
    
    /*int dfs(TrieNode root) {
    	int sum = 0;
    	for(char ch : root.children.keySet()) {
    		if(root.children.get(ch) == null)
    			continue;
    		sum += dfs(root.children.get(ch));
    	}
    	return sum + root.len;
    }*/
}

public class Main {
	public static void main(String[] args) {
		String []words = {"time", "me", "bell"};
		System.out.println(new Solution().minimumLengthEncoding(words));
	}
}