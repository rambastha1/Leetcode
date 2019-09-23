package main;

import java.util.Stack;

class Solution {
	class Node {
		int val;
		Node left, right;
		Node(int val) {this.val = val;}
	}
	
	Node root;
	void inorder(Node node) {
		if(node == null)
			return;
		Stack<Node> stack = new Stack<>();
		while(node != null || !stack.isEmpty()) {
			while(node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			System.out.print(node.val + " ");
			node = node.right;
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new Node(10);
		s.root.left = s.new Node(5);
		s.root.right = s.new Node(15);
		s.root.right.left = s.new Node(6);
		s.root.right.right = s.new Node(20);
		s.inorder(s.root);
	}
}