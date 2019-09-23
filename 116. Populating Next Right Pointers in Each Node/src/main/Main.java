package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

class Solution {
	public class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;
		TreeLinkNode(int x) { val = x; }
	}
	
	public TreeLinkNode root;
	/*
	 * 0(N) solution
	 */
	
	/*public void connect(TreeLinkNode root) {
		if (root == null) {
		    return;
		}
		while (root != null) {
		    TreeLinkNode left = root.left;
		    while (root != null && root.left != null && root.right != null) {
		        root.left.next = root.right;
		        if (root.next != null) {
		            root.right.next = root.next.left;
		        }
		        root = root.next;
		    }
		    root = left;
		}	
	}*/
	
	public void connect(TreeLinkNode root) {
		Queue<TreeLinkNode> queue = new LinkedList<>();
	    if (root == null) {
	        return;
	    }
	    queue.add(root);
	    while (!queue.isEmpty()) {
	        TreeLinkNode prev = null;
	        int size = queue.size();
	        while (size > 0) {
	            TreeLinkNode node = queue.poll();
	            if (node.right != null) {
	                queue.offer(node.right);
	            }
	            if (node.left != null) 
	                queue.offer(node.left);
	            node.next = prev;
	            prev = node;
	            size--;
	        }
	    }
	}
    
    public void preorder(TreeLinkNode node) {
    	if(node == null)
    		return;
    	System.out.print(node.val + " ");
    	preorder(node.left);
    	preorder(node.right);
    }
    
}

public class Main {

	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeLinkNode(1);
		s.root.left = s.new TreeLinkNode(2);
		s.root.right = s.new TreeLinkNode(3);
		s.root.left.left = s.new TreeLinkNode(4);
		s.root.left.right = s.new TreeLinkNode(5);
		s.root.right.left = s.new TreeLinkNode(6);
		s.root.right.right = s.new TreeLinkNode(7);
		
		s.preorder(s.root);
		System.out.println();
		s.connect(s.root);
	}
}