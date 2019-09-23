package main;

import java.util.LinkedList;
import java.util.Queue;

import main.Solution.TreeNode;

/* https://eugenejw.github.io/2017/08/leetcode-536.html
 * You need to construct a binary tree from a string consisting of parenthesis and integers.
 * The whole input represents a binary tree. It contains an integer followed by zero, 
 * one or two pairs of parenthesis. The integer represents the root’s value and a pair 
 * of parenthesis contains a child binary tree with the same structure.
 * You always start to construct the left child node of the parent first if it exists.
 * 
 * Example:
 * 
 * Input: "4(2(3)(1))(6(5))"
 * Output: return the tree root node representing the following tree:
 * 
 *        4
 *      /   \
 *     2     6
 *    / \   / 
 *   3   1 5   
 * Note:
 * There will only be ‘(‘, ‘)’, ‘-‘ and ‘0’ ~ ‘9’ in the input string.
 * An empty tree is represented by “” instead of “()”.
 * 
 */

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public TreeNode root;
	int index = 0;
	
	public TreeNode str2tree(String s) {
		if(s == null || s.length() == 0)
    		return null;
		return dfs(s);
	}
	
    public TreeNode dfs(String s) {
    	TreeNode node = null;    	
    	// Get value of node
    	if(s.charAt(index) != '(') {
    		int val = getVal(s);
    		node = new TreeNode(val);
    	}
    	
    	TreeNode left = null, right = null;
    	if(index < s.length() && s.charAt(index) == '(') {
    		index++;
    		left = dfs(s);
    	} 
    	if(index < s.length() && s.charAt(index) == '(') {
    		index++;
    		right = dfs(s);
    	}
    	node.left = left;
    	node.right = right;
    	index++;
    	return node;
    }
    
    int getVal(String s ) {
    	StringBuilder sb = new StringBuilder();
    	while(s.charAt(index) != '(' && s.charAt(index) != ')')
    		sb.append(s.charAt(index++));
    	return Integer.valueOf(sb.toString());
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
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "4(2(3)(1))(6(5))";
		TreeNode node = new Solution().str2tree(s);
		new Solution().levelorder(node);
	}
}
