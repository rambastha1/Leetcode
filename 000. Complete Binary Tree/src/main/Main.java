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
	
	public int insert(int v) {
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(root);
    	while(!q.isEmpty()) {
    		TreeNode node = q.poll();
    		if(node.left != null && node.right != null) {
    			q.offer(node.left);
    			q.offer(node.right);
    		} else {
    			if(node.left == null)
    				node.left = new TreeNode(v);
    			else
    				node.right = new TreeNode(v);
    			return node.val;
    		}
     	}
    	return -1;
    }
	
	boolean iscomplete(TreeNode root) {
		if(root == null)
			return true;
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		boolean notfull = false;
		while(!q.isEmpty()) {
			TreeNode node = q.poll();
			if(node.left != null) {
				/* forms a tree of three left nodes.
				 *  root -> not full node.l != null and n.l.l != null
				 *  This cannot be complete tree
				 */
				if(notfull)
					return false;
				q.offer(node.left);
			} else {
				//not full node
				notfull = true;
			}
			
			if(node.right != null) {
				/*
				 * Since node.left is checked above
				 * it will also handle the case where left child is null and right is not
				 */
				if(notfull)
					return false;
				q.offer(node.right);
			} else {
				//not full node
				notfull = true;
			}
		}
		return true;
	}
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(2);
		s.root.left.left = s.new TreeNode(4);
		s.root.left.right = s.new TreeNode(5);
		s.root.right = s.new TreeNode(3);
		s.root.right.right = s.new TreeNode(6);
		System.out.println(s.iscomplete(s.root));
	}
}