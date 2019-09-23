package main;

class tree {
	public class TreeNode {
		int val;
		TreeNode left,right;
		public TreeNode(int val) {
			this.val = val;
			left = right = null;
		}
	}
	
	TreeNode root;
	
	public TreeNode invertTree(TreeNode root) {
		if(root == null)
			return root;
		TreeNode temp = root.left;
		root.left = invertTree(root.right);
		root.right = invertTree(temp);
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

		tree t = new tree();
		t.root = t.new TreeNode(4);
		t.root.left = t.new TreeNode(2);
		t.root.right = t.new TreeNode(7);
		t.root.left.left = t.new TreeNode(1);
		t.root.left.right = t.new TreeNode(3);
		t.root.right.left = t.new TreeNode(6);
		t.root.right.right = t.new TreeNode(9);
		
		t.inorder(t.root);
		System.out.println();
		t.invertTree(t.root);
		t.inorder(t.root);
		System.out.println();
		
	}

}
