package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javafx.util.Pair;

/*
 * Return smallest subtree.  It means if both child are present return parent else return child. 
 * It is for this reason we need to keep track of parents
 */

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public TreeNode subtreeWithAllDeepest(TreeNode root) {
    	if(root == null)
    		return root;
    	return util(root).getValue();
    }
	
	/*
	 * first value of pair is height of node, second is node which contain deepest node
	 * Math.max(d1, d2) + 1 gives height
	 * if height of both subtree is same return node 
	 * If left is more, return left child else return right child just like Lowest Common Ancestor 
	 */
	
	public Pair<Integer, TreeNode> util(TreeNode root) {
		if(root == null) return new Pair<Integer, Solution.TreeNode>(0, null);
		Pair<Integer, TreeNode> l = util(root.left);
		Pair<Integer, TreeNode> r = util(root.right);
		
		int d1 = l.getKey(), d2 = r.getKey();
		return new Pair<>(Math.max(d1, d2) + 1, (d1==d2)?root:(d1>d2)?l.getValue():r.getValue());
	}
    
    public TreeNode root;
    
    public void levelOrder(TreeNode root) {
    	if(root == null)
    		return;
    	
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(root);  	
    	
    	while(!q.isEmpty()) {
    		for(int i = 0;i < q.size();i++) {
    			TreeNode node = q.poll();
    			System.out.print(node.val + " ");
    			if(node.left!= null) q.offer(node.left);
    			if(node.right != null) q.offer(node.right);
    		}
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		/*s.root = s.new TreeNode(3);
		s.root.left = s.new TreeNode(5);
		s.root.right = s.new TreeNode(1);
		s.root.left.left = s.new TreeNode(6);
		s.root.left.right = s.new TreeNode(2);
		s.root.right.left = s.new TreeNode(0);
		s.root.right.right = s.new TreeNode(8);
		s.root.left.right.left = s.new TreeNode(7);
		s.root.left.right.right = s.new TreeNode(4);*/
		//s.levelOrder(s.root);
		s.root = s.new TreeNode(0);
		s.root.left = s.new TreeNode(1);
		
		s.root.right = s.new TreeNode(3);
		s.root.right.right = s.new TreeNode(2);
		
		Solution.TreeNode node = s.subtreeWithAllDeepest(s.root);
		System.out.println(node.val);
		
		
	}
}