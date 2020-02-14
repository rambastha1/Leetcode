package main;

import javafx.util.Pair;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
    public TreeNode lcaDeepestLeaves(TreeNode root) {
    	return lca(root, 0).getKey();
    }
    // use height to get deepest node
    private Pair<TreeNode, Integer> lca(TreeNode root, int h) {
    	if(root == null)
    		return new Pair<>(null, h);
    	Pair<TreeNode, Integer> left = lca(root.left, h+1);
    	Pair<TreeNode, Integer> right = lca(root.right, h+1);
    	
    	if(left.getValue() == right.getValue())
    		return new Pair<>(root, left.getValue());
    	return left.getValue() > right.getValue()?left:right;
    }
    
    public TreeNode root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(2);
		s.root.left.left = s.new TreeNode(4);
		s.root.right = s.new TreeNode(3);
		System.out.println(s.lcaDeepestLeaves(s.root).val);
	}
}
