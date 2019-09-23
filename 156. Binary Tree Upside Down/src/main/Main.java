package main;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares 
 * the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes 
 * turned into left leaf nodes. Return the new root.
 * 
 * For example:
 * 
 * Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
 * 
 * return the root of the binary tree [4,5,2,#,#,3,1].
 * 
   4
  / \
 5   2
    / \
   3   1
 * 
 */

class Solution {
	
	public class TreeNode {
		int val;
		TreeNode left, right;
		TreeNode(int x) { val = x; }
	}
	
	public TreeNode root;
    
	//Better Solution Time 0(N) Constant Space
	public TreeNode upsideDownBinaryTree(TreeNode root) {
		if(root == null || root.left == null)
			return root;
		TreeNode newroot = upsideDownBinaryTree(root.left);
		/* Don't use newroot in place of root.left here
		 * as it will make new pointers not update them
		 */
		
		root.left.left = root.right;
		root.left.right = root;
		root.left = null;
		root.right = null;
		return newroot;
	}
	
	
	//Time 0(N) Space 0(N)
	/*public TreeNode upsideDownBinaryTree(TreeNode root) {
    	this.root = root;
    	Stack<TreeNode> stack = new Stack<>();
    	util(root, stack);
    	reverse(head);
    	return head;
    }
	
	TreeNode head = null, prev = null;
    void util(TreeNode root, Stack<TreeNode> stack) {
    	if(root == null)
    		return;
    	TreeNode left = root.left;
    	TreeNode right = root.right;
    	root.right = prev;
    	prev = right;    	
    	root.left = null;
    	
    	if(!stack.isEmpty()) {
    		root.left = stack.pop();
    	}
    	
    	if(left != null) {
    		head = left;
    		stack.push(root);
    		util(left,stack);
    	}    	
    	util(right,stack);
    }
    
    TreeNode reverse(TreeNode root) {
    	if(root == null)
    		return null;
    	TreeNode temp = root.left;
    	root.left = reverse(root.right);
    	root.right = temp;
    	return root;
    }*/
    
    void levelorder(TreeNode root) {
    	if(root == null)
    		return;
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(root);
    	while(!q.isEmpty()) {
    		TreeNode node = q.poll();
    		System.out.print(node.val + " ");
    		if(node.left != null)
    			q.offer(node.left);
    		if(node.right != null)
    			q.offer(node.right);
    	}
    	System.out.println();
    }
}


public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(2);
		s.root.right = s.new TreeNode(3);
		s.root.left.left = s.new TreeNode(4);
		s.root.left.right = s.new TreeNode(5);
		s.levelorder(s.root);
		s.root = s.upsideDownBinaryTree(s.root);
		s.levelorder(s.root);
	}
}