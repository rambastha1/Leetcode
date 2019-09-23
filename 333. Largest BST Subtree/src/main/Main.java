package main;

import java.util.Stack;

/* Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), 
 * where largest means subtree with largest number of nodes in it.
 * Note:
 * A subtree must include all of its descendants.
 * Here's an example:
 *     10
 *     / \
 *    5  15
 *   / \   \ 
 *  1   8   7
 * The Largest BST Subtree in this case is the highlighted one. 
 * The return value is the subtree's size, which is 3.
 */

/* curr.lower = Math.min(root.val, Math.min(left.lower, right.lower));
 * curr.upper = Math.max(root.val, Math.max(left.upper, right.upper));
 * 
 * Consider a case like this. A subtree rooted at a node has only right child. 
 * In this case minimum passed to the parent above should be root->data and not l.min 
 * as l.min will be INT_MIN which will lead to incorrect value getting passed to parent. 
 * Similar is the case for a subtree rooted at a node with only left child.
 */

/* https://www.geeksforgeeks.org/largest-bst-binary-tree-set-2/
 * http://buttercola.blogspot.com/2016/02/leetcode-largest-bst-subtree.html
 */
class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	public TreeNode root;
	private int ans = 0;

	class Node {
		int size;
		int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;
		boolean isbst;		
	}
	
	public int largestBSTSubtree(TreeNode root) {
		if(root == null)
			return 0;
		postorder(root);
		return ans;
	}
	
	// bottom up fashion Time 0(N) Space 0(N) - recursion stack
	private Node postorder(TreeNode root) {
		Node node = new Node();
		if(root == null) {
			node.isbst = true;
			return node;
		}
		
		Node left = postorder(root.left);
		Node right = postorder(root.right);
		
		node.low = Math.min(root.val, Math.min(left.low, right.low));
		node.high = Math.max(root.val, Math.max(left.high, right.high));
		
		if(left.isbst && left.high < root.val && right.isbst && root.val < right.low) {
			node.size = left.size + right.size + 1;
			node.isbst = true;
			ans = Math.max(ans, node.size);
		} else 
			node.size = 0;
		return node;
	}
	
	
	public boolean isbst(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		int min = Integer.MIN_VALUE;
		while(root != null || !stack.isEmpty()) {
			while(root != null) {
				stack.push(root);
				root = root.left;
			}
			
			root = stack.pop();
			if(root.val < min)
				return false;
			min = root.val;
			root = root.right;
		}
		return true;
	}
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(50);
		s.root.left = s.new TreeNode(30);
		s.root.left.left = s.new TreeNode(5);
		s.root.left.right = s.new TreeNode(20);
		s.root.right = s.new TreeNode(60);
		s.root.right.left = s.new TreeNode(45);
		s.root.right.right = s.new TreeNode(70);
		s.root.right.right.left = s.new TreeNode(65);
		s.root.right.right.right = s.new TreeNode(80);
		System.out.println(s.largestBSTSubtree(s.root));
	}
}
