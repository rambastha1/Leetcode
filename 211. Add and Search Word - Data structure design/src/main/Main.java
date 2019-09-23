package main;
import java.util.HashMap;
import java.util.Map;

class WordDictionary {
	class TrieNode {
		Map<Character, TrieNode> child;
		boolean isend;
		public TrieNode() {
			child = new HashMap<>();
		}
	}
	
	public TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
    	root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()) {
        	if(node.child.get(c) == null)
        		node.child.put(c, new TrieNode());
        	node = node.child.get(c);
        }
        node.isend = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
    	return dfs(word, 0, this.root);
    }
    
    private boolean dfs(String word, int index, TrieNode root) {
    	if(index >= word.length())
    		return root.isend;
    	
    	char c = word.charAt(index);
    	// search for all child
    	if(c == '.') {
    		for(char ch : root.child.keySet()) {
    			if(dfs(word, index+1, root.child.get(ch)))
    				return true;
    		}
    		return false;
    	} else
    		// if this character matches search for next character in the word
    		return root.child.get(c) != null && dfs(word, index+1, root.child.get(c));
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

public class Main {
	public static void main(String[] args) {
		WordDictionary w = new WordDictionary();
		w.addWord("bad");
		w.addWord("dad");
		w.addWord("mad");
		System.out.println(w.search("pad"));
		System.out.println(w.search("bad"));
		System.out.println(w.search(".ad"));
		System.out.println(w.search("b.."));
	}
}
