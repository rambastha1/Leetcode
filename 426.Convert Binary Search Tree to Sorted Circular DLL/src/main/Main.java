package main;

import java.util.LinkedList;
import java.util.Queue;

/*
 *  Use 000.Convert Binary Tree to Sorted DLL logic. In the end adjust head.left = end and end.right = head
 *  to make it circular DLL 
 */


class Solution {
	class Node {
		public int val;
		public Node left;
		public Node right;

		public Node(int val) {
			this.val = val;
		}
	}
	
	public Node root, head = new Node(0);
	Node prev = new Node(0);
	
	public Node treeToDoublyList(Node root) {
		util(root);
		this.head.left = prev;
		this.prev.right = head;
		
		return this.head;
	}	
	
	public void util(Node root) {
		if(root == null) 
			return;
		treeToDoublyList(root.left);
		if(prev.val == 0) {
			head = root;
		} else {
			prev.right = root;
			root.left = prev;
		}
		prev = root;
		treeToDoublyList(root.right);
	}
	
	void print(Node root) {
		Node temp = root.right;
		System.out.print(root.val + " ");
		while(temp != root) {
			System.out.print(temp.val + " ");
			temp = temp.right;
		}
		System.out.println();
	}
	
	public void levelorder(Node root) {
        if(root == null)
        	return;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
        	Node node = q.poll();
        	System.out.print(node.val + " ");
        	if(node.left != null) {
        		q.offer(node.left);
        	}
        	if(node.right != null)
        		q.offer(node.right);
        }
        System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new Node(10);
		s.root.left = s.new Node(12);
		s.root.right = s.new Node(15);
		s.root.left.left = s.new Node(25);
		s.root.left.right = s.new Node(30);
		s.root.right.left = s.new Node(36);
		
		s.levelorder(s.root);
		s.treeToDoublyList(s.root);
		s.print(s.head);
	}
}