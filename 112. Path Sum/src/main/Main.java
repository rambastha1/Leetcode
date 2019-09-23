package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
    public boolean hasPathSum(TreeNode root, int sum) {
    	return dfs(root, sum, 0);
    }
    
    boolean dfs(TreeNode root, int sum, int curr_sum) {
    	if(root == null)
    		return false;
    	curr_sum += root.val;
    	if(curr_sum == sum && root.left == null && root.right == null)
    		return true;
    	
    	//Dont't put this line as if root.val > sum return false even if path exists
    	/*if(curr_sum > sum) {
    		curr_sum -= root.val;
    		return false;
    	}*/
    	
    	boolean l = dfs(root.left, sum, curr_sum);
    	boolean r = dfs(root.right, sum, curr_sum);
    	//No need
    	//curr_sum -= root.val;
    	return l || r;
    }
    
    public TreeNode root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		/*s.root = s.new TreeNode(5);
		s.root.left = s.new TreeNode(4);
		s.root.right = s.new TreeNode(8);
		s.root.left.left = s.new TreeNode(11);
		s.root.left.left.left = s.new TreeNode(7);
		s.root.left.left.right = s.new TreeNode(2);
		s.root.right.left = s.new TreeNode(13);
		s.root.right.right = s.new TreeNode(4);
		s.root.right.right.right = s.new TreeNode(1);*/
		
		s.root = s.new TreeNode(8);
		s.root.left = s.new TreeNode(7);
		s.root.right = s.new TreeNode(-6);
		s.root.right.left = s.new TreeNode(5);
		s.root.right.right = s.new TreeNode(9);
		
		int sum = 7;
		System.out.println(s.hasPathSum(s.root, sum));
	}
}