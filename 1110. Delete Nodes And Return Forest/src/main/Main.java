package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
    	Set<Integer> set = new HashSet<>();
    	for(int num : to_delete)
    		set.add(num);
    	List<TreeNode> res = new ArrayList<>();
    	TreeNode r = dfs(root, res, set);
        if(r != null)
    	    res.add(r);
    	return res;
    }
    
    private TreeNode dfs(TreeNode root, List<TreeNode> res, Set<Integer> delete) {
    	if(root == null)
    		return null;
    	TreeNode left = dfs(root.left, res, delete);
    	TreeNode right = dfs(root.right, res, delete);
    	
    	if(delete.contains(root.val)) {
    		if(left != null)
    			res.add(left);
    		if(right != null)
    			res.add(right);
    		return null;
    	}
        
        if(left == null)
    		root.left = null;
    	if(right == null)
    		root.right = null;
    	return root;
    }
    
    public TreeNode root;
}

public class Main {
	public static void main(String[] args) {
		int []to_delete = {3,5};
		Solution s = new Solution();
		s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(2);
		s.root.left.left = s.new TreeNode(4);
		s.root.left.right = s.new TreeNode(5);
		s.root.right = s.new TreeNode(3);
		s.root.right.left = s.new TreeNode(6);
		s.root.right.right = s.new TreeNode(7);
		new Solution().delNodes(s.root, to_delete);
	}
}
