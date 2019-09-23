package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
    	List<TreeNode> res = new ArrayList<>();
    	if(root == null)
    		return res;
    	Map<String, Integer> map = new HashMap<>();
    	dfs(root, res, map);
     	return res;
    }
    
    String dfs(TreeNode root, List<TreeNode> res, Map<String, Integer> map) {
    	if(root == null) {
    		return "#";
    	}    	
    	String str = root.val + "#" + dfs(root.left, res, map) + "#" + dfs(root.right, res, map);
    	map.put(str, map.getOrDefault(str, 0)+1);
    	if(map.get(str) == 2) {
    		res.add(root);
    		System.out.print(root.val + " "); 
    	}
    	return str;
    }
    
    public TreeNode root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(2);
		s.root.right = s.new TreeNode(3);
		s.root.left.left = s.new TreeNode(4);
		s.root.right.left = s.new TreeNode(2);
		s.root.right.right = s.new TreeNode(4);
		s.root.right.left.left = s.new TreeNode(4);
		s.findDuplicateSubtrees(s.root);
	}
}