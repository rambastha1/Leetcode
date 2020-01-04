package main;

import java.util.HashMap;
import java.util.Map;

class StreamChecker {
	
	class TrieNode {
		Map<Character, TrieNode> child;
		boolean isword;
		public TrieNode() {
			this.child = new HashMap<>();
			this.isword = false;
		}
	}
	StringBuilder sb;
	TrieNode root = new TrieNode();
    public StreamChecker(String[] words) {
    	for(String str : words) {
    		insert(str);
    	}
    	
    	this.sb = new StringBuilder();
    }
    
    public boolean query(char letter) {
    	TrieNode curr = root;
    	sb.append(letter);
    	
    	for(int i = sb.length()-1;i >= 0;i--) {
    		char c = sb.charAt(i);
    		curr = curr.child.get(c);
    		if(curr == null)
    			return false;
    		if(curr.isword)
    			return true;
    	}
        return false;
    }
    
    private void insert(String str) {
    	TrieNode curr = root;
    	for(int i = str.length()-1;i >= 0;i--) {
    		char c = str.charAt(i);
    		if(curr.child.get(c) == null)
    			curr.child.put(c, new TrieNode());
    		curr = curr.child.get(c);
    	}
    	curr.isword = true;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */

public class Main {
	public static void main(String[] args) {
		String []words = {"abaa","abaab","aabbb","bab","ab"};
		StreamChecker s = new StreamChecker(words);
		System.out.println(s.query('d'));
		System.out.println(s.query('a'));
		System.out.println(s.query('b'));
		System.out.println(s.query('b'));
		System.out.println(s.query('b'));
		System.out.println(s.query('a'));
		System.out.println(s.query('b'));
		System.out.println(s.query('b'));
		System.out.println(s.query('a'));
		System.out.println(s.query('a'));
		System.out.println(s.query('a'));
		System.out.println(s.query('a'));
		System.out.println(s.query('b'));
		System.out.println(s.query('a'));
		System.out.println(s.query('b'));
		System.out.println(s.query('b'));
		System.out.println(s.query('b'));
		System.out.println(s.query('a'));
		System.out.println(s.query('b'));
		System.out.println(s.query('b'));
		System.out.println(s.query('b'));
		System.out.println(s.query('a'));
		System.out.println(s.query('a'));
		System.out.println(s.query('a'));
		System.out.println(s.query('a'));
		System.out.println(s.query('a'));
		System.out.println(s.query('b'));
		System.out.println(s.query('a'));
		System.out.println(s.query('b'));
		System.out.println(s.query('b'));
		System.out.println(s.query('b'));
		System.out.println(s.query('a'));
		System.out.println(s.query('a'));
		System.out.println(s.query('b'));
		System.out.println(s.query('b'));
		System.out.println(s.query('b'));
		System.out.println(s.query('a'));
		System.out.println(s.query('b'));
		System.out.println(s.query('a'));
	}
}
