package main;

import java.util.LinkedList;
import java.util.Queue;

import main.Solution.TreeNode;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public TreeNode root;
	
	/*
	 * Pass min and max value to dfs
	 * it check root.val with min and max to calculate overall min and max of subtree
	 * At leaf return difference to get maximum difference
	 */
    public int maxAncestorDiff(TreeNode root) {
    	if(root == null)
    		return 0;
    	return dfs(root, root.val, root.val);
    }
    
    int dfs(TreeNode root, int min, int max) {
    	if(root == null)
    		return max- min;
    	max = Math.max(max, root.val);
    	min = Math.min(min, root.val);
    	return Math.max(dfs(root.left, min, max), dfs(root.right, min, max));
    }
    
    void levelorder(TreeNode root) {
    	if(root == null)
    		return;
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(root);
    	while(!q.isEmpty()) {
    		TreeNode node = q.poll();
    		System.out.print(node.val + " ");
    		if(node.left != null)
    			q.offer(node.left);
    		if(node.right!= null)
    			q.offer(node.right);
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(8);
		s.root.left = s.new TreeNode(3);
		s.root.right = s.new TreeNode(10);
		s.root.left.left = s.new TreeNode(1);
		s.root.left.right = s.new TreeNode(6);
		s.root.right.right = s.new TreeNode(14);
		s.root.left.right.left = s.new TreeNode(4);
		s.root.left.right.right = s.new TreeNode(7);
		s.root.right.right.left = s.new TreeNode(13);
		s.levelorder(s.root);
		System.out.println();
		System.out.println(s.maxAncestorDiff(s.root));
	}
}