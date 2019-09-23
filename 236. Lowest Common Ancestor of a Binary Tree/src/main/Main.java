package main;

import java.util.ArrayList;
import java.util.List;

/*
 * LCA of BST
 * 0(height)
 * Iterative using BST property 
 */

class BST {
	public class TreeNode {
		int val;
		TreeNode left, right;
		TreeNode(int x) { val = x; }
	}
	
	public TreeNode root;
	public TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
		while(root != null && p != null && q != null) {
			if(root.val > p.val && root.val > q.val)
				root = root.left;
			if(root.val < p.val && root.val < q.val)
				root = root.right;
		}
		return root;
	}
}

class Solution {
	
	public class TreeNode {
		int val;
		TreeNode left, right;
		TreeNode(int x) { val = x; }
	}
	/*
	 * recursion
	 * But not generic
	 */
	
	/*public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }*/
	
	public TreeNode root;
	static boolean v1 = false, v2 = false;
	/*
	 * LCA of Binary Tree
	 * Single Traversal
	 * 0(N) time
	 */
	
    /*public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        TreeNode lca = lowestCommonAncestorutil(root, p, q);
        if(v1 && v2)
        	return lca;        
        return null;
    }
    
    public TreeNode lowestCommonAncestorutil(TreeNode root, int p, int q) {
    	if(root == null)
    		return null;
    	TreeNode temp = null;
    	if(root.val == p) {
    		v1 = true;
    		temp = root;
    	}
    	
    	if(root.val == q) {
    		v2 = true;
    		temp = root;
    	}
    	
    	TreeNode left_lca = lowestCommonAncestorutil(root.left, p, q);
    	TreeNode right_lca = lowestCommonAncestorutil(root.right, p, q);
    	
    	if(temp != null)
    		return temp;
    	if(left_lca != null && right_lca != null)
    		return root;
    	return (left_lca != null)?left_lca : right_lca;    			
    }
    
    private boolean contains(TreeNode root, TreeNode node) {
        return false;
    }*/
    
	/*
	 * Using Parent Pointer
	 * 0(height)
	 */
    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
    	return null;
    }
}

public class Main {

	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(3);
		s.root.left = s.new TreeNode(5);
		s.root.right = s.new TreeNode(1);
		s.root.left.left = s.new TreeNode(6);
		s.root.left.right = s.new TreeNode(2);
		s.root.left.right.left = s.new TreeNode(7);
		s.root.left.right.right = s.new TreeNode(4);
		s.root.right.left = s.new TreeNode(0);
		s.root.right.right = s.new TreeNode(8);
		main.Solution.TreeNode node = s.lowestCommonAncestor(s.root, 5, 0);
		if(node != null)
			System.out.println(node.val);
		else
			System.out.println("NULL");
		 
	}
}