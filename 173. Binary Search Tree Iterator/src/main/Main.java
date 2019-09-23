package main;

import java.util.Stack;

// https://leetcode.com/articles/binary-search-tree-iterator/
// Great Article

class BSTIterator {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public TreeNode root;
	
	Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        this.root = root;
        stack = new Stack<>();
        inorderUtil(root);
    }
    
    void inorderUtil(TreeNode root) {
    	while(root != null) {
    		stack.push(root);
    		root = root.left;
    	}
    }
    
    /** @return the next smallest number */
    public int next() {
    	
    	TreeNode node = stack.pop();
    	if(node.right != null) {
    		inorderUtil(node.right);
    	}
    	return node.val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

public class Main {
	public static void main(String[] args) {
		
	}
}