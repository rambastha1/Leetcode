package main;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import main.Solution.TreeNode;

class Solution {
    
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	void levelorder(TreeNode node) {
    	if(node == null)
    		return;
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(node);
    	while(!q.isEmpty()) {
    		TreeNode n = q.poll();
    		System.out.print(n.val + " ");
    		if(n.left != null)
    			q.offer(n.left);
    		if(n.right != null)
    			q.offer(n.right);
    	}
    }
	
	public TreeNode root;
	
	public TreeNode bstFromPreorder(int[] preorder) {
		
		if(preorder == null || preorder.length == 0)
			return null;
		
		TreeNode root = new TreeNode(preorder[0]);
		Stack<TreeNode> s = new Stack<>();
		s.push(root);
		for(int i = 1;i < preorder.length;i++) {
			TreeNode temp = null;
			while(!s.isEmpty() && preorder[i] > s.peek().val) {
				temp = s.pop();
			}
			
			if(temp == null) {
				temp = s.peek();
				temp.left = new TreeNode(preorder[i]);
				s.push(temp.left);
			} else {
				temp.right =  new TreeNode(preorder[i]);
				s.push(temp.right);
			}
			//don't use this as this is independent node. two push statements are important
			//s.push(new TreeNode(preorder[i]));
		}
		return root;
    }
}


public class Main {
	public static void main(String[] args) {
		int []preorder = {8,5,1,7,10,12};
		Solution s = new Solution();
		Solution.TreeNode node = s.bstFromPreorder(preorder);
		s.levelorder(node);
	}
}