package main;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	public TreeNode root;
	
	/* Notice right child of every node is next element in preorder traversal
	 * Preorder Traversal
	 */
    
	TreeNode prev = new TreeNode(0);
    public void flatten(TreeNode root) {
    	if(root == null)
    		return;
    	TreeNode left = root.left;
    	TreeNode right = root.right;
    	
    	if(root != prev) {
    		prev.right = root;
    		prev.left = null;
    		prev = root;
    	}
    	
    	flatten(left);
    	flatten(right);
    	if(left == null && right == null) {
    		prev = root;
    	}
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
		s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(2);
		s.root.right = s.new TreeNode(5);
		s.root.left.left = s.new TreeNode(3);
		s.root.left.right = s.new TreeNode(4);
		s.root.right.right = s.new TreeNode(6);
		
		s.levelorder(s.root);
		s.flatten(s.root);
		s.levelorder(s.root);
	}
}