package main;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
    	if(root == null)
    		return new TreeNode(val);
    	else if(val > root.val) {
    		TreeNode temp = root;
    		TreeNode newnode = new TreeNode(val);
    		newnode.left = temp;
    		root = newnode;
    	} else {
    		root.right = insertIntoMaxTree(root.right, val);
    	}
    	return root;
    }
}

public class Main {
	public static void main(String[] args) {

	}
}
