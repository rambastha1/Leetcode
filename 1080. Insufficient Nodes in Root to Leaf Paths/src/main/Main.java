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
	
    public TreeNode sufficientSubset(TreeNode root, int limit) {
    	if(root == null)
    		return root;
    	if(root.left == null && root.right == null)
    		return root.val >= limit?root:null;
    	
    	root.left = sufficientSubset(root.left, limit - root.val);
    	root.right = sufficientSubset(root.right, limit-root.val);
    	/* because all path from root to leaf should be equal or above limit
    	 * if childs are null, means there is no such path. Thus that node is insufficient
    	 */
    	return (root.left == null && root.right == null)?null:root;
    }
    
    public TreeNode root;
    
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
    		if(node.right != null)
    			q.offer(node.right);
    	}
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(2);
		s.root.left.left = s.new TreeNode(4);
		s.root.left.left.left = s.new TreeNode(8);
		s.root.left.left.right = s.new TreeNode(9);
		s.root.left.right = s.new TreeNode(-99);
		s.root.left.right.left = s.new TreeNode(-99);
		s.root.left.right.right = s.new TreeNode(-99);
		s.root.right = s.new TreeNode(3);
		s.root.right.left = s.new TreeNode(-99);
		s.root.right.left.left = s.new TreeNode(12);
		s.root.right.left.right = s.new TreeNode(13);
		s.root.right.right = s.new TreeNode(7);
		s.root.right.right.left = s.new TreeNode(-99);
		s.root.right.right.right = s.new TreeNode(14);
		
		s.levelorder(s.root);
		int limit = 1;
		TreeNode node = s.sufficientSubset(s.root, limit);
		s.levelorder(node);
	}
}
