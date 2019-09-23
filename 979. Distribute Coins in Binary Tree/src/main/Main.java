package main;


//https://leetcode.com/articles/distribute-coins-in-binary-tree/
class Solution {
	
	public class TreeNode {
		int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
	
	public TreeNode root;
	int ans = 0;
    public int distributeCoins(TreeNode root) {
        dfs(root);
    	return ans;
    }
    
    public int dfs(TreeNode root) {
    	if(root == null)
    		return 0;
    	int L = dfs(root.left);
    	int R = dfs(root.right);
    	ans += Math.abs(L) + Math.abs(R);
    	return root.val - 1 + L + R;
    }
}


public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(3);
		s.root.left = s.new TreeNode(0);
		s.root.right = s.new TreeNode(0);
		System.out.println(s.distributeCoins(s.root));
	}
}