package main;

class MagicDictionary {
	
	class TrieNode {
		TrieNode []children;
		boolean isend;
		String val;
		public TrieNode() {
			children = new TrieNode[26];
			/*for(int i = 0;i < children.length;i++)
				children[i] = new TrieNode();*/
		}
	}
	
	public TrieNode root;
	
    /** Initialize your data structure here. */
    public MagicDictionary() {
        root = new TrieNode();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        TrieNode trie = root;
        for(String str : dict) {
        	TrieNode node = trie;
        	char []arr = str.toCharArray();
        	for(int i = 0;i < arr.length;i++) {
        		if(node.children[i] == null)
        			node.children[i] = new TrieNode();
        		node = node.children[i];
        	}
        	node.isend = true;
        	node.val = str;
        }
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying 
     * exactly one character */
    
    /*
     *  edit every character from 'a' to 'z' and search the new string.
     *  (This process is like "word ladder")
     */
    public boolean search(String word) {
    	TrieNode node = root;
    	char []arr = word.toCharArray();
    	for(int i = 0;i < arr.length;i++) {
    		for(int j = 0;j < 26;j++) {
    			/*
    			 * if it is same character as arr[i] or node.children[j] is null continue
    			 */
    			if(j+'a' == arr[j] || node.children[j] == null)
    				continue;
    			// Search from this changed character.. it is end return true
    			// Similarly search with all replaced (not exactly replaced but search from that character)
    			if(searchUtil(node.children[j], word, i+1)) return true;
    		}
    		if(node.children[i] == null) return false;
    		node = node.children[i];
    	}    	
    	return false;
    }
    
    public boolean searchUtil(TrieNode root, String word, int index) {
    	TrieNode temp = root;
    	char []arr = word.toCharArray();
    	for(int i = index;i < arr.length;i++) {
    		if(temp.children[i] == null)
    			return false;
    		temp = temp.children[i];
    	}
    	return temp.isend;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */

public class Main {
	public static void main(String[] args) {
		MagicDictionary obj = new MagicDictionary();
		String []dict = {"hello", "leetcode"};
		obj.buildDict(dict);
		
		String word = "hhllo";
		System.out.println(obj.search(word));
	}
}