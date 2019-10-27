package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Design an in-memory file system to simulate the following functions:

ls: Given a path in string format. If it is a file path, return a list that only contains this file's name. 
If it is a directory path, return the list of file and directory names in this directory. Your output 
(file and directory names together) should in lexicographic order.

mkdir: Given a directory path that does not exist, you should make a new directory according to the path. 
If the middle directories in the path don't exist either, you should create them as well. This function has void return type.

addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to 
create that file containing given content. If the file already exists, you need to append given content to original content. 
This function has void return type.

readContentFromFile: Given a file path, return its content in string format.

 

Example:

Input: 
["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
[[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]

Output:
[null,[],null,null,["a"],"hello"]

Explanation:
filesystem
 

Note:

You can assume all file or directory paths are absolute paths which begin with / and do not end with / except that the 
path is just "/".
You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or 
list a directory or file that does not exist.
You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the 
same directory.
 * 
 */

/* https://leetcode.com/articles/design-in-memory-file-system/
 * https://segmentfault.com/a/1190000017339491
 * https://leetcode.com/problems/design-in-memory-file-system/discuss/103331/Java-Solution-File-class
 */

class FileSystem {
	class TrieNode {
		boolean isFile;
		Map<String, TrieNode> child;
		String content;
		public TrieNode() {
			this.child = new HashMap<String, FileSystem.TrieNode>();
			this.content = "";
		}
	}
	
	TrieNode root;
    public FileSystem() {
        root = new TrieNode();
    }
    
    public List<String> ls(String path) {
    	TrieNode curr = root;
    	List<String> res = new ArrayList<String>();
    	String []dirs = path.split("/");
    	String name = "";
    	
    	for(String dir : dirs) {
    		if(dir.length() == 0)
    			continue;
    		if(!curr.child.containsKey(dir))
    			return res;
    		
    		curr = curr.child.get(dir);
    		name = dir;
    	}
    	
    	// reached end of path
    	if(curr.isFile) {
			res.add(name);
		} else {
			// directory
			for(String key : curr.child.keySet()) {
				res.add(key);
			}
		}
    	
    	Collections.sort(res);
    	return res;
    }
    
    public void mkdir(String path) {
        String []dirs = path.split("/");
        TrieNode node = root;
        for(String dir : dirs) {
        	if(dir.length() == 0)
        		continue;
        	if(!node.child.containsKey(dir)) {
        		node.child.put(dir, new TrieNode());
        	}
        	node = node.child.get(dir);
        }
    }
    
    public void addContentToFile(String filePath, String content) {
    	String []dirs = filePath.split("/");
        TrieNode node = root;
        for(String dir : dirs) {
        	if(dir.length() == 0)
        		continue;
        	if(!node.child.containsKey(dir)) {
        		node.child.put(dir, new TrieNode());
        	}
        	node = node.child.get(dir);
        }
        
        node.isFile = true;
        node.content += content;
    }
    
    public String readContentFromFile(String filePath) {
    	String []dirs = filePath.split("/");
        TrieNode node = root;
        for(String dir : dirs) {
        	if(dir.length() == 0)
        		continue;
        	if(!node.child.containsKey(dir)) {
        		node.child.put(dir, new TrieNode());
        	}
        	node = node.child.get(dir);
        }
        return node.content;
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */

public class Main {
	public static void main(String[] args) {
		FileSystem file = new FileSystem();
		System.out.println(file.ls("/"));
		file.mkdir("/a/b/c");
		file.addContentToFile("/a/b/c/d", "hello");
		System.out.println(file.ls("/"));
		System.out.println(file.readContentFromFile("/a/b/c/d"));
	}
}