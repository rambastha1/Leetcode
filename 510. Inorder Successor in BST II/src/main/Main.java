package main;

/*
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * The successor of a node p is the node with the smallest key greater than p.val. 
 * You will have direct access to the node but not to the root of the tree. 
 * Each node will have a reference to its parent node. A node is defined as the following:
 */

class Solution {
	class Node {
	    public int val;
	    public Node left;
	    public Node right;
	    public Node parent;
	}
	
	public Node inorderSuccessor(Node x) {
		if(x == null)
			return null;
		if(x.right != null) {
			Node temp = x.right;
			while(temp.left != null)
				temp = temp.left;
			return temp;
		}
		
		while(x != null) {
			if(x.parent == null)
				return null;
			if(x == x.parent.left)
				return x.parent;
			x = x.parent;
		}
		return x;
	}
	
	public Node root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		
	}
}