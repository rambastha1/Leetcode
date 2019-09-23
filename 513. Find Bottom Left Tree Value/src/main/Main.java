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
    public int findBottomLeftValue(TreeNode root) {
    	if(root == null)
    		return -1;
    	
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(root);
    	/*
    	 * just keep storing the first value
    	 * when this end the first value of last row i.e. ans will be answer
    	 */
    	int ans = 0;
    	while(!q.isEmpty()) {
    		int size = q.size();
    		for(int i = 0;i < size;i++) {
    			TreeNode node = q.poll();
    			if(i == 0)
    				ans = node.val;
    			if(node.left != null)
    				q.offer(node.left);
    			if(node.right != null)
    				q.offer(node.right);
    		}
    	}
    	return ans;
    }
    
    int height(TreeNode root) {
    	if(root == null)
    		return -1;
    	return 1 + Math.max(height(root.left), height(root.right));
    }
    
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(2);
		s.root.left = s.new TreeNode(1);
		s.root.right = s.new TreeNode(3);
		System.out.println(s.findBottomLeftValue(s.root));
	}
}