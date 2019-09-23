package main;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
	class TreeNode {
		int val;
		TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
		}
	}
	public TreeNode root, head;
	
	TreeNode prev = new TreeNode(0);
	public TreeNode treeToDoublyList(TreeNode root) {
		if(root == null)
			return null;
		
		treeToDoublyList(root.left);
		if(prev.val == 0) {
			head = root;
		} else {
			prev.right = root;
			root.left = prev;
		}
		prev = root;
		treeToDoublyList(root.right);	
		return root;
	 }
	
	void print(TreeNode root) {
		while(root != null) {
			System.out.print(root.val + " ");
			root = root.right;
		}
		System.out.println();
	}
	
	public void levelorder(TreeNode root) {
        if(root == null)
        	return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
        	TreeNode node = q.poll();
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
		s.root = s.new TreeNode(10);
		s.root.left = s.new TreeNode(12);
		s.root.right = s.new TreeNode(15);
		s.root.left.left = s.new TreeNode(25);
		s.root.left.right = s.new TreeNode(30);
		s.root.right.left = s.new TreeNode(36);
		
		s.levelorder(s.root);
		s.root = s.treeToDoublyList(s.root);
		s.print(s.head);
	}
}