package main;

import java.util.ArrayList;
import java.util.List;

/* https://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/
 */

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	// Time 0(N)
	public List<Integer> boundaryOfBinaryTree(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if(root == null)
			return res;
		if (root.left != null || root.right != null)
			res.add(root.val);
		leftnode(root.left, res);
		leaves(root, res);
		rightnode(root.right, res);
		return res;
	}
	
	private void leaves(TreeNode root, List<Integer> res) {
		if(root == null)
			return;
		if(root.left == null && root.right == null)
			res.add(root.val);
		leaves(root.left, res);
		leaves(root.right, res);
	}
	
	private void leftnode(TreeNode root, List<Integer> res) {
		if(root == null)
			return;
		if(root.left != null) {
			res.add(root.val);
			leftnode(root.left, res);
		} else if(root.right != null) {
			res.add(root.val);
			leftnode(root.right, res);
		}
	}
	
	private void rightnode(TreeNode root, List<Integer> res) {
		if(root == null)
			return;
		if(root.right != null) {
			rightnode(root.right, res);
			res.add(root.val);
		} else if(root.left != null) {
			rightnode(root.left, res);
			res.add(root.val);
		}
	}
	
	public TreeNode root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(2);
		s.root.left.left = s.new TreeNode(4);
		s.root.left.right = s.new TreeNode(5);
		s.root.left.right.left = s.new TreeNode(7);
		s.root.left.right.right = s.new TreeNode(8);
		s.root.right = s.new TreeNode(3);
		s.root.right.left = s.new TreeNode(6);
		s.root.right.left.left = s.new TreeNode(9);
		s.root.right.left.right = s.new TreeNode(10);
		System.out.println(s.boundaryOfBinaryTree(s.root));
	}
}
