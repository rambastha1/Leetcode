package main;

import java.util.HashMap;
import java.util.Map;
/* store strings as String str = suffix + ":" + prefix;
 * apple -> {apple, e{apple, le{apple, ple{apple, pple{apple, apple{apple;
 */
class WordFilter {

	class TrieNode {
		Map<Character, TrieNode> child;
		int weight;
		public TrieNode() {
			child = new HashMap<>();
		}
	}
	
    public WordFilter(String[] words) {
        for(int i = 0;i < words.length;i++) {
        	String str = words[i];
        	for(int j = 0;j <= str.length();j++) {
        		String s = str.substring(j) + ":" + str;
        		insert(s, i);
        	}
        }
    }
    
    public int f(String prefix, String suffix) {
    	String str = suffix + ":" + prefix;
    	TrieNode curr = root;
    	for(char c : str.toCharArray()) {
    		if(curr.child.get(c) == null)
    			return -1;
    		curr = curr.child.get(c);
    	}
        return curr.weight;
    }
    
    private void insert(String str, int w) {
    	TrieNode curr = root;
    	for(char c : str.toCharArray()) {
    		if(!curr.child.containsKey(c))
    			curr.child.put(c, new TrieNode());
    		curr = curr.child.get(c);
    		curr.weight = w;
    	}
    }
    
    TrieNode root = new TrieNode();
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */

public class Main {
	public static void main(String[] args) {
		String []words = {"apple"};
		WordFilter obj = new WordFilter(words);
		System.out.println(obj.f("a", "e"));
		System.out.println(obj.f("b", ""));
	}
}
