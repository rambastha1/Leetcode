package main;

// https://www.geeksforgeeks.org/find-maximum-path-sum-in-a-binary-tree/
class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
    	dfs(root);
    	return this.max;
    }
    
    private int dfs(TreeNode root) {
    	if(root == null)
    		return 0;
    	int sum = root.val;
    	int left = dfs(root.left);
    	int right = dfs(root.right);
    	
    	if(left + sum >= sum && right + sum >= sum) {
    		sum += left + right;
            this.max = Math.max(max, sum);
            sum -= Math.min(left, right);
        } else if(sum + left >= sum)
    		sum += left;
    	else if(sum + right >= sum)
    		sum += right;
    	this.max = Math.max(max, sum);
    	return sum;
    }
    
    TreeNode root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(5);
		s.root.left = s.new TreeNode(4);
		s.root.left.left = s.new TreeNode(11);
		s.root.left.left.left = s.new TreeNode(7);
		s.root.left.left.right = s.new TreeNode(2);
		s.root.right = s.new TreeNode(8);
		s.root.right.left = s.new TreeNode(13);
		s.root.right.left.right = s.new TreeNode(1);
		s.root.right.right = s.new TreeNode(4);
		System.out.println(new Solution().maxPathSum(s.root));
	}
}
