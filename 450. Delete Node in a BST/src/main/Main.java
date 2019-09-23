package main;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
    public TreeNode deleteNode(TreeNode root, int key) {
    	if(root == null)
    		return root;
    	if(key < root.val)
    		root.left = deleteNode(root.left, key);
    	else if(key > root.val)
    		root.right = deleteNode(root.right, key);
    	else {
    		if(root.left == null && root.right == null)
    			return null;
    		if(root.left == null || root.right == null) {
    			return root.left == null?root.right:root.left;
    		} else {
    			TreeNode temp = min(root.right);
    			root.val = temp.val;
    			root.right = deleteNode(root.right, temp.val);
    		}
    	}
    	return root;
    }
    
    public TreeNode min(TreeNode root) {
    	if(root == null)
    		return null;
    	while(root.left != null)
    		root = root.left;
    	return root;
    }
}

public class Main {
	public static void main(String[] args) {

	}
}
