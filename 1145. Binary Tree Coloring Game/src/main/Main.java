package main;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	/* x is red node, left = number of nodes in left subtree of red, right = number of nodes in right subtree of red
	 * n - left - right -1 is number of nodes outside red subtree
	 * if player 2 picks any of three nodes, and the count > n/2, then true  
	 */
	int left, right, red;
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
    	red = x;
    	count(root);
    	return Math.max(Math.max(left, right), n - left - right - 1) > n/2;
    }
    
    private int count(TreeNode root) {
    	if(root == null)
    		return 0;
    	int l = count(root.left), r = count(root.right);
    	if(root.val == red) {
    		left = l;
    		right = r;
    	}
    	return l + r + 1;
    }
    
    public TreeNode root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(2);
		s.root.left.left = s.new TreeNode(4);
		s.root.left.left.left = s.new TreeNode(8);
		s.root.left.left.right = s.new TreeNode(9);
		s.root.left.right = s.new TreeNode(5);
		s.root.left.right.left = s.new TreeNode(10);
		s.root.left.right.right = s.new TreeNode(11);
		s.root.right = s.new TreeNode(3);
		s.root.right.left = s.new TreeNode(6);
		s.root.right.right = s.new TreeNode(7);
		System.out.println(new Solution().btreeGameWinningMove(s.root, 11, 3));
	}
}
