package main;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/design-file-system/discuss/365901/JavaPython-3-7-line-simple-HashMap-code-w-analysis.
class FileSystem {
	
	// path -> value
	Map<String, Integer> map;
    public FileSystem() {
        map = new HashMap<>();
        map.put("", -1);
    }
    
    public boolean createPath(String path, int value) {
        int index = path.lastIndexOf("/");
        String parent = path.substring(0, index);
        if(!map.containsKey(parent))
        	return false;
        return map.putIfAbsent(path, value) == null;
    }
    
    public int get(String path) {
        return map.getOrDefault(path, -1);
    }
}

// Using Trie
class FileSystem1 {
	class TrieNode {
		Map<String, TrieNode> child;
		int val;
		public TrieNode() {
			this.child = new HashMap<>();
			this.val = -1;
		}
	}
	
	TrieNode root;
    public FileSystem1() {
        root = new TrieNode();
    }
    
    public boolean createPath(String path, int value) {
    	path = path.substring(1);
    	String []strs = path.split("/");
    	TrieNode node = root;
		for(int i = 0;i < strs.length;i++) {
			if(!node.child.containsKey(strs[i]) && i != strs.length-1)
				return false;
			if(i == strs.length - 1 && node.child.containsKey(strs[i]))
				return false;
			if(node.child.get(strs[i]) == null)
				node.child.put(strs[i], new TrieNode());
			node = node.child.get(strs[i]);
		}
    	node.val = value;
    	return true;
    }
    
    public int get(String path) {
    	path = path.substring(1);
    	TrieNode node = root;
    	String []strs = path.split("/");
    	for(int i = 0;i < strs.length;i++) {
    		if(!node.child.containsKey(strs[i]))
    			return -1;
    		node = node.child.get(strs[i]);
    	}
        return node.val;
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 */

public class Main {
	public static void main(String[] args) {
		FileSystem file = new FileSystem();
		System.out.println(file.createPath("/a", 1));
		System.out.println(file.get("/a"));
	}
}