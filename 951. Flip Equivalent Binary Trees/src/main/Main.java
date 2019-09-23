package main;

import java.util.LinkedList;
import java.util.Queue;

import main.Solution.TreeNode;

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
    	System.out.println();
    }
	
	public boolean flipEquiv(TreeNode root1, TreeNode root2) {
		if(root1 == null && root2 == null)
			return true;
		if(root1 == null || root2 == null)
			return false;
				
        if(root1.val != root2.val)
        	return false;
		
		return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) ||
				flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }
}

public class Main {
	public static void main(String[] args) {
		Solution s1 = new Solution();
		Solution s2 = new Solution();
		
		s1.root = s1.new TreeNode(1);
		s1.root.left = s1.new TreeNode(2);
		s1.root.right = s1.new TreeNode(3);
		s1.root.left.left = s1.new TreeNode(4);
		s1.root.left.right = s1.new TreeNode(5);
		s1.root.left.right.left = s1.new TreeNode(7);
		s1.root.left.right.right = s1.new TreeNode(8);
		s1.root.right.left = s1.new TreeNode(6);
		
		s2.root = s2.new TreeNode(1);
		s2.root.left = s2.new TreeNode(3);
		s2.root.right = s2.new TreeNode(2);
		s2.root.left.right = s2.new TreeNode(6);
		s2.root.right.left = s2.new TreeNode(4);
		s2.root.right.right = s2.new TreeNode(5);
		s2.root.right.right.left = s2.new TreeNode(8);
		s2.root.right.right.right = s2.new TreeNode(7);
		
		s1.levelorder(s1.root);
		s2.levelorder(s2.root);
		System.out.println(s1.flipEquiv(s1.root, s2.root));
	}
}