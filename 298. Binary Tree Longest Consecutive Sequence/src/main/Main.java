package main;

import java.util.LinkedList;
import java.util.Queue;

/* Given a binary tree, find the length of the longest consecutive sequence path.
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the 
 * parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
 * For example,
 *    1
 *     \
 *      3
 *     / \
 *    2   4
 *         \
 *          5
 * Longest consecutive sequence path is 3-4-5, so return 3.
 *    2
 *     \
 *      3
 *     / 
 *    2    
 *   / 
 *  1
 * Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
 * 
 */

class Solution {
	
	public class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}
	
	public int longestConsecutive(TreeNode root) {
		if(root == null)
			return 0;
		return dfs(root);
	}
	
	int dfs(TreeNode root) {
		if(root == null)
			return 0;
		
		if(root.left == null && root.right == null)
			return 1;
		
		int l = dfs(root.left);
		int r = dfs(root.right);
		
		if(root.left != null && root.val == root.left.val-1)
			l++;
		if(root.right != null && root.val == root.right.val-1)
			r++;
		return Math.max(l, r);
	}
	
	public TreeNode root;
	
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
			if(node.right!= null)
				q.offer(node.right);
		}
		System.out.println();
	}
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		/*s.root = s.new TreeNode(1);
		s.root.right = s.new TreeNode(3);
		s.root.right.left = s.new TreeNode(2);
		s.root.right.right = s.new TreeNode(4);
		s.root.right.right.right = s.new TreeNode(5);*/
		
		s.root = s.new TreeNode(2);
		s.root.right = s.new TreeNode(3);
		s.root.right.left = s.new TreeNode(2);
		s.root.right.left.left = s.new TreeNode(1);		
		
		s.levelorder(s.root);
		System.out.println(s.longestConsecutive(s.root));
	}
}
