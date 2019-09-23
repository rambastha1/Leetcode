package main;

import java.util.LinkedList;
import java.util.Queue;

class Tree {
	public class Node {
		int val;
		Node left, right;
		public Node(int val) {
			this.val = val;
			this.left = this.right = null;
		}
	}
	
	Node root;
	
	public void inorder(Node node) {
		if(node == null)
			return;
		inorder(node.left);
		System.out.print(node.val + " ");
		inorder(node.right);
	}
	
	//Recursive
	public boolean symmetric(Node node) {
		if(node == null)
			return true;
		return symutil(node, node);
	}
	
	public boolean symutil(Node node1, Node node2) {
		if(node1 == null && node2 == null)
			return true;
		
		if(node1 == null || node2 == null)
			return false;
		return (node1.val == node2.val) && symutil(node1.left, node2.right) && symutil(node1.right, node2.left);
	}
	
	//Iterative
	public boolean levelorder(Node node) {
		
		if(node == null)
			return true;
		Queue<Node> q = new LinkedList<>();
		q.offer(node);
		q.offer(node);
		while(!q.isEmpty()) {
			Node temp1 = q.poll();
			Node temp2 = q.poll();
			if(temp1 == null && temp2 == null)
				return true;
			if(temp1 == null || temp2 == null)
				return false;
			if(temp1.val != temp2.val)
				return false;
			q.offer(temp1.left);
			q.offer(temp2.right);
			q.offer(temp1.right);
			q.offer(temp2.left);
		}
		return true;
	}
}

public class Main {

	public static void main(String[] args) {
		Tree t = new Tree();
		t.root = t.new Node(3);
		t.root.left = t.new Node(4);
		t.root.right = t.new Node(4);
		t.root.left.left = t.new Node(5);
		//t.root.left.right = t.new Node(3);
		//t.root.right.left = t.new Node(3);
		t.root.right.right = t.new Node(5);
		t.root.left.left.left = t.new Node(6);
		t.root.right.right.right = t.new Node(6);
		
		t.inorder(t.root);
		System.out.println();
		
		//System.out.println(t.level(t.root));
		System.out.println(t.symmetric(t.root));
		System.out.println(t.levelorder(t.root));
	}
	
}
