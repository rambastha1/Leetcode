package main;

class Solution {
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	double ans = 0.0;
    public double maximumAverageSubtree(TreeNode root) {
    	dfs(root);
    	return ans;
    }
    // count, sum
    // used array because need persistent data
    private int[] dfs(TreeNode root) {
    	if(root == null)
    		return new int[] {0,0};
    	int sum = root.val;
    	int count = 1;
    	int []left = dfs(root.left);
    	int []right = dfs(root.right);
    	count += left[0] + right[0];
    	sum += left[1] + right[1];
    	this.ans = Math.max(this.ans, (double)sum/count);
    	return new int[] {count, sum};
    }
    
    public TreeNode root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(5);
		s.root.left = s.new TreeNode(6);
		s.root.right = s.new TreeNode(1);
		System.out.println(s.maximumAverageSubtree(s.root));
		
	}
}
