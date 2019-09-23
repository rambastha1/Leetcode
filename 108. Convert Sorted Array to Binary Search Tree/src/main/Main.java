package main;

import java.util.LinkedList;
import java.util.Queue;

// https://www.geeksforgeeks.org/sorted-array-to-balanced-bst/
class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
    public TreeNode sortedArrayToBST(int[] nums) {
    	return dfs(nums, 0, nums.length-1);
    }
    // Take mid and make root do for left and right subtree
    TreeNode dfs(int []nums, int low, int high) {
    	if(low > high)
    		return null;
    	int mid = low + (high-low)/2;
    	TreeNode node = new TreeNode(nums[mid]);
    	node.left = dfs(nums, low, mid-1);
    	node.right = dfs(nums, mid+1, high);
    	return node;
    }
    
    public TreeNode root;
    
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
    	System.out.println();
    }
    
}

public class Main {
	public static void main(String[] args) {
		int []nums = {-10,-3,0,5,9}; 
		Solution s = new Solution();
		s.root = s.sortedArrayToBST(nums);
		s.levelorder(s.root);
	}
}
