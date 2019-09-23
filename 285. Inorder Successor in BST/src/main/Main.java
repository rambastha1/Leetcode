package main;

class Solution {
	class TreeNode {
		int val;
		TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
		}
	}
	
	TreeNode root;
	void inorder(TreeNode root) {
		if(root == null)
			return;
		inorder(root.left);
		System.out.print(root.val + " ");
		inorder(root.right);
	}
	
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		if(root == null || p == null)
			return null;
		if(p.right != null)
			return min(p.right);
		TreeNode succ = null;
		while(root != null) {
			if(p.val < root.val) {
				succ = root;
				root = root.left;
			} else if(p.val > root.val) {
				root = root.right;
			} else
				break;				
		}
		return succ;
	}
	
	TreeNode min(TreeNode node) {
		if(node == null)
			return null;
		while(node.left != null)
			node = node.left;
		return node;
	}
}

public class Main {

	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(20);
		s.root.left = s.new TreeNode(8);
		s.root.right = s.new TreeNode(22);
		s.root.left.left = s.new TreeNode(4);
		s.root.left.right = s.new TreeNode(12);
		s.root.left.right.left = s.new TreeNode(10);
		s.root.left.right.right = s.new TreeNode(14);
		s.root.right = s.new TreeNode(22);
		Solution.TreeNode temp = s.inorderSuccessor(s.root, s.new TreeNode(14));
		if(temp != null)
			System.out.println(temp.val);
		else
			System.out.println("NULL");
 	}
}