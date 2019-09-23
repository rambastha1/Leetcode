package main;

class Tree {
	public class TreeNode {
		int val;
		TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
			this.left = this.right = null;
		}
	}
	
	TreeNode root;
	
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		
		if(t1 == null && t2 == null)
			return t1;
		if(t1 == null || t2 == null)
			return (t1==null)?t2:t1;
		
		TreeNode newnode = new TreeNode(t1.val + t2.val);
		newnode.left = mergeTrees(t1.left, t2.left);
		newnode.right = mergeTrees(t1.right, t2.right);
		return newnode;
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
		Tree t1 = new Tree();
		t1.root = t1.new TreeNode(1);
		t1.root.left = t1.new TreeNode(3);
		t1.root.right = t1.new TreeNode(2);
		t1.root.left.left = t1.new TreeNode(5);
		
		Tree t2 = new Tree();
		t2.root = t2.new TreeNode(2);
		t2.root.left = t2.new TreeNode(1);
		t2.root.right = t2.new TreeNode(3);
		t2.root.left.right = t2.new TreeNode(4);
		t2.root.right.right = t2.new TreeNode(7);
		
		t1.inorder(t1.root);
		System.out.println();
		t2.inorder(t2.root);
		System.out.println();
		
	}

}
