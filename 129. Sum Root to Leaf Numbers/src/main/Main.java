package main;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	// build total from top and add lower significant digit by traversal
	int total = 0;
	public int sumNumbers(TreeNode root) {
		dfs(root, 0);
		return total;
	}

	void dfs(TreeNode root, int sum) {
		if(root == null) 
			return;
		sum = sum*10 + root.val;
		if(root.left == null && root.right == null) {
			total += sum;
			return;
		}
		dfs(root.left, sum);
		dfs(root.right, sum);
	}
    
    public TreeNode root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		/*s.root = s.new TreeNode(4);
		s.root.left = s.new TreeNode(9);
		s.root.right = s.new TreeNode(0);
		s.root.left.left = s.new TreeNode(5);
		s.root.left.right = s.new TreeNode(1);*/
		
		s.root = s.new TreeNode(4);
		s.root.left = s.new TreeNode(9);
		s.root.right = s.new TreeNode(0);
		s.root.left.right = s.new TreeNode(1);
		
		System.out.println(new Solution().sumNumbers(s.root));
	}
}
