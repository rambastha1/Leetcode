package main;

import java.util.HashMap;
import java.util.Map;

/* You are asked to design a file system which provides two functions:

createPath(path, value): Creates a new path and associates a value to it if possible and returns True. 
Returns False if the path already exists or its parent path doesn't exist.
get(path): Returns the value associated with a path or returns -1 if the path doesn't exist.
The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. 
For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.

Implement the two functions.

Please refer to the examples for clarifications.

 

Example 1:

Input: 
["FileSystem","createPath","get"]
[[],["/a",1],["/a"]]
Output: 
[null,true,1]
Explanation: 
FileSystem fileSystem = new FileSystem();

fileSystem.createPath("/a", 1); // return true
fileSystem.get("/a"); // return 1
Example 2:

Input: 
["FileSystem","createPath","createPath","get","createPath","get"]
[[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
Output: 
[null,true,true,2,false,-1]
Explanation: 
FileSystem fileSystem = new FileSystem();

fileSystem.createPath("/leet", 1); // return true
fileSystem.createPath("/leet/code", 2); // return true
fileSystem.get("/leet/code"); // return 2
fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
fileSystem.get("/c"); // return -1 because this path doesn't exist.
 

Constraints:

The number of calls to the two functions is less than or equal to 10^4 in total.
2 <= path.length <= 100
1 <= value <= 10^9
 * 
 */
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