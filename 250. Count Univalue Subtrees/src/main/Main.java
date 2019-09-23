package main;

/*
 * Given a binary tree, count the number of uni-value subtrees.
 * A Uni-value subtree means all nodes of the subtree have the same value.
 * 
 * Example :
 * 
 * Input:  root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

  * Output: 4
  */

class Solution {
	
	public class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}
	int count = 0;
	public int countUnivalSubtrees(TreeNode root) {	
		if(root == null)
			return 0;
		dfs(root);
		return count;
	}
	
	boolean dfs(TreeNode root) {
		if(root == null)
			return true;	
		
		boolean l = dfs(root.left);
		boolean r = dfs(root.right);
		
		if(l && r) {
			if(root.left != null && root.val != root.left.val) return false;
			if(root.right != null && root.val != root.right.val) return false;
			count++;
			return true;
		}
		return false;
	}
	
	public TreeNode root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(5);
		s.root.left = s.new TreeNode(1);
		s.root.right = s.new TreeNode(5);
		s.root.left.left = s.new TreeNode(5);
		s.root.left.right = s.new TreeNode(5);
		s.root.right.right = s.new TreeNode(5);
		System.out.println(s.countUnivalSubtrees(s.root));
	}
}