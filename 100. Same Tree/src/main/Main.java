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
	
	public boolean issame(Node node1, Node node2) {
		if(node1 == null && node2 == null)
			return true;
		if(node1 == null || node2 == null)
			return false;
		if(node1.val != node2.val)
			return false;
		
		return issame(node1.left, node2.left) && issame(node1.right, node2.right);
	}
	
}

public class Main {

	public static void main(String[] args) {

		tree t = new tree();
		t.root = t.new Node(1);
		t.root.left = t.new Node(2);
		t.root.right = t.new Node(1);
		
		tree t1 = new tree();
		t1.root = t1.new Node(1);
		t1.root.left = t1.new Node(2);
		t1.root.right = t1.new Node(2);
		//t1.root.right.left = t1.new Node(3);
		
		System.out.println(t.issame(t.root, t1.root));
	}

}
