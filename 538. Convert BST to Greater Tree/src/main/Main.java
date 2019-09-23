package main;

import org.w3c.dom.Node;

class Tree {
	public class TreeNode {
		int val;
		TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
			left = right = null;
		}
	}
	
	TreeNode root;
	int sum = 0;
	public TreeNode convertBST(TreeNode root) {
		
		if(root != null) {
			convertBST(root.right);
			sum += root.val;
			root.val = sum;
			convertBST(root.left);
		}
		return root;
    }
	
	public void inorder(TreeNode node) { 
		if(node == null)
			return;
		inorder(node.left);
		System.out.print(node.val + " ");
		inorder(node.right);
	}
}

public class Main {

	public static void main(String[] args) {

		Tree t = new Tree();
		t.root = t.new TreeNode(5);
		t.root.left = t.new TreeNode(2);
		t.root.left.left = t.new TreeNode(1);
		t.root.left.right = t.new TreeNode(4);
		t.root.right = t.new TreeNode(13);
		t.root.right.left = t.new TreeNode(11);
		t.root.right.right = t.new TreeNode(20);
		
		t.inorder(t.root);
		System.out.println();
		
		t.root = t.convertBST(t.root);
		t.inorder(t.root);
		System.out.println();
	}

}
