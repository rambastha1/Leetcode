package main;

class tree {
	public class Node {
		int val;
		Node left, right;
		public Node(int val) {
			this.val = val;
			this.left = this.right = null;
		}
	}
	
	Node root;
	int length;
	
	public int diameter(Node node) {
		if(node == null)
			return 0;
		
		int l = diameter(node.left);
		int r = diameter(node.right);
		
		length = Math.max(length, l+r+1);
		/*
		 * This is for parent node. This will give max length of left or right sub child of parent node
		 */
		return Math.max(l, r) + 1; 		
	}
	
	public int d(Node node) {
		if(node == null)
			return 0;
		length = 0;
		diameter(node);
		return length - 1;
	}
}

public class Main {

	public static void main(String[] args) {
		
		tree t = new tree();
		t.root = t.new Node(1);
		t.root.left = t.new Node(2);
		t.root.right = t.new Node(3);
		t.root.left.left = t.new Node(4);
		t.root.left.right = t.new Node(5);
		
		/*t.root = t.new Node(2);
		t.root.left = t.new Node(3);
		t.root.left.left = t.new Node(1);*/
		System.out.println(t.d(t.root));
	}
}
