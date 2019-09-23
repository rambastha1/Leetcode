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
	
    public boolean isCompleteTree(TreeNode root) {
    	boolean flag = false;
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(root);
    	while(!q.isEmpty()) {
    		TreeNode node = q.poll();
    		if(node.left != null) {
    			if(flag)
    				return false;
    			q.offer(node.left);
    		} else
    			flag = true;
    		
    		if(node.right != null) {
    			if(flag) 
    				return false;
    			q.offer(node.right);
    		} else
    			flag = true;
    	}
    	return true;
    }
    
    public TreeNode root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(2);
		s.root.right = s.new TreeNode(3);
		s.root.left.left = s.new TreeNode(4);
		s.root.left.right = s.new TreeNode(5);
		s.root.right.left = s.new TreeNode(6);
		System.out.println(s.isCompleteTree(s.root));
	}
}