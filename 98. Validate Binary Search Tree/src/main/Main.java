package main;

import java.util.Stack;

class Solution {
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	TreeNode root;
	
	/*
	 * Recursive Solution
	 */
	
	public boolean isValidBST(TreeNode root) {	
		if(root == null)
			return true;
		return isValidBSTutil(root, null, null);
	}
	
	public boolean isValidBSTutil(TreeNode root, TreeNode left, TreeNode right) {
		if(root == null)
			return true;
		
		if(left != null && root.val <= left.val)
			return false;
		
		if(right != null && root.val >= right.val)
			return false;
		
		
		return isValidBSTutil(root.left, left, root) && isValidBSTutil(root.right, root, right);
	}
	
	
	/*
	 * Iterative using stack
	 */
	
	/*public boolean isValidBST(TreeNode root) {
		if(root == null)
			return true;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode pre = null;
		while(root != null || !stack.isEmpty()) {
			while(root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			if(pre != null && pre.val >= root.val)
				return false;
			pre = root;
			root = root.right;
		}		
		return true;
	}*/
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(Integer.MIN_VALUE);
		//s.root.left = s.new TreeNode(5);
		s.root.right = s.new TreeNode(Integer.MAX_VALUE);
		//s.root.right.left = s.new TreeNode(6);
		//s.root.right.right = s.new TreeNode(20);
		System.out.println(s.isValidBST(s.root));
	}
}