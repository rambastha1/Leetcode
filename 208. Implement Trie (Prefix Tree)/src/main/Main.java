package main;

class Trie {
	class Node {
		Node []children = new Node[26];
		boolean isend;
		public Node() {
			isend = false;
			for(int i = 0;i < 26;i++)
				children[i] = null;
		}
	}
	
	Node root;
	
    /** Initialize your data structure here. */
    public Trie() {
    	root = new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        int len = word.length();
        int index;
        Node temp = root;
        for(int i = 0;i < len;i++) {
        	index = word.charAt(i) - 'a';
        	if(temp.children[index] == null)
        		temp.children[index] = new Node();
        	temp = temp.children[index];
        }
        temp.isend = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
    	Node temp = root;
    	for(int i = 0;i < word.length();i++) {
    		int index = word.charAt(i);
    		if(temp.children[index] == null)
    			return false;
    		temp = temp.children[index];
    	}
    	return (temp!= null && temp.isend);
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
    	Node temp = root;
    	for(int i = 0;i < prefix.length();i++) {
    		int index = prefix.charAt(i);
    		if(temp.children[index] == null)
    			return false;
    		temp = temp.children[index];
    	}
    	return (temp != null && !temp.isend);
    }
}


public class Main {

	public static void main(String[] args) {

	}
}