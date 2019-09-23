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
	
    public TreeNode addOneRow(TreeNode root, int v, int d) {
    	if(root == null)
    		return root;
    	if(d == 1) {
    		TreeNode node = new TreeNode(v);
    		node.left = root;
    		return node;
    	}
    	
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(root);
    	int depth = 1;
    	while(!q.isEmpty()) {
    		int size = q.size();
    		if(depth + 1 == d) {
    			for(int i = 0;i < size;i++) {
    				TreeNode node = q.poll();
    				TreeNode left = new TreeNode(v);
        			TreeNode right = new TreeNode(v);
        			left.left = node.left;
        			right.right = node.right;
        			node.left = left;
        			node.right = right;
    			}
    			break;
    		} else {
    			for(int i = 0;i < size;i++) {
    				TreeNode node = q.poll();
    				if(node.left != null)
    					q.offer(node.left);
    				if(node.right != null)
    					q.offer(node.right);
    			}
    		}
    	}
    	return root;
    }
    
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
    
    public TreeNode root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(4);
		s.root.left = s.new TreeNode(2);
		s.root.right = s.new TreeNode(6);
		s.root.left.left = s.new TreeNode(3);
		s.root.left.right = s.new TreeNode(1);
		s.root.right.left = s.new TreeNode(5);
		
		s.levelorder(s.root);
		Solution.TreeNode node = s.addOneRow(s.root, 1, 2);
		s.levelorder(node);
	}
}