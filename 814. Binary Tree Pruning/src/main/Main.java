package main;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
	
	public class TreeNode {
		int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
	
	public TreeNode root;
	
	void levelorder(TreeNode node) {
    	if(node == null)
    		return;
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(node);
    	while(!q.isEmpty()) {
    		TreeNode n = q.poll();
    		System.out.print(n.val + " ");
    		if(n.left != null)
    			q.offer(n.left);
    		if(n.right != null)
    			q.offer(n.right);
    	}
    }
	
    public TreeNode pruneTree(TreeNode root) {
        if(root == null)
        	return root;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if(root.val == 1 || (root.val == 0 && (root.left != null || root.right != null)))
        	return root;
        return null;
    }
}


public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		/*s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(0);
		s.root.left.left = s.new TreeNode(0);
		s.root.left.right = s.new TreeNode(0);
		s.root.right = s.new TreeNode(1);
		s.root.right.left = s.new TreeNode(0);
		s.root.right.right = s.new TreeNode(1);*/
		
		s.root = s.new TreeNode(1);
		s.root.right = s.new TreeNode(0);
		s.root.right.left = s.new TreeNode(0);
		s.root.right.right = s.new TreeNode(1);
		
		Solution.TreeNode node = s.pruneTree(s.root);
		s.levelorder(node);
	}
}