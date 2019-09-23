package main;

import java.util.HashMap;
import java.util.Map;

class MapSum {
	
	class TrieNode {
		int val;
		boolean isend;
		Map<Character, TrieNode> children;
		public TrieNode() {
			children = new HashMap<>();
		}
	}
	
	public TrieNode root;
    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode();
    }
    
    public void insert(String key, int val) {
    	
        TrieNode node = root;
        char []arr = key.toCharArray();
        for(int i = 0;i < arr.length;i++) {
        	TrieNode child = node.children.get(arr[i]);
        	if(child == null) {
        		child = new TrieNode();
        		node.children.put(arr[i], child);
        	}
        	node = child;
        }
        node.val = val;
        node.isend = true;
    }
    
    public int sum(String prefix) {
    	
    	char []arr = prefix.toCharArray();
    	TrieNode node = root;
    	for(int i = 0;i < arr.length;i++) {
    		TrieNode child = node.children.get(arr[i]);
    		if(child == null) {
    			return 0;
    		} 
    		node = child;
    	}
    	return dfs(node);
    }
    
    /*
     * After traversing all the prefix loop through every sentence and add it to the sum 
     */
    public int dfs(TrieNode root) {
    	TrieNode node = root;
    	int sum = 0;
    	for(char c : root.children.keySet()) {
    		sum += dfs(root.children.get(c)); 
    	}
    	return sum + root.val;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */

public class Main {
	public static void main(String[] args) {
		MapSum obj = new MapSum();
		obj.insert("apple", 3);
		System.out.println(obj.sum("ap"));
		obj.insert("app", 2);
		System.out.println(obj.sum("ap"));
	}
}