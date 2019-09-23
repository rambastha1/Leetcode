package main;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public TreeNode root;
	
    public TreeNode constructMaximumBinaryTree(int[] nums) {
    	if(nums == null || nums.length == 0)
    		return null;
    	return constructMaximumBinaryTreeUtil(nums, 0, nums.length - 1);
    }
    
    public TreeNode constructMaximumBinaryTreeUtil(int[] nums, int low, int high) {
    	if(low > high)
    		return null;
    	
    	if(low == high)
    		return new TreeNode(nums[low]);
    	
    	int max = Integer.MIN_VALUE;
    	int max_index = -1;
    	for(int i = low;i <= high;i++) {
    		if(nums[i] > max) {
    			max = nums[i];
    			max_index = i;
    		}
    	}
    	if(max_index == -1)
    		return null;
    	
    	TreeNode node = new TreeNode(max);
    	node.left = constructMaximumBinaryTreeUtil(nums, low, max_index - 1);
    	node.right = constructMaximumBinaryTreeUtil(nums, max_index + 1, high);
    	
    	return node;
    }
    
    void levelorder(TreeNode node) {
    	if(node == null)
    		return;
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(node);
    	while(!q.isEmpty()) {
    		TreeNode n = q.poll();
    		System.out.print(n.val + " ");
    		if(n.left != null)
    			q.offer(n.left);
    		if(n.right != null)
    			q.offer(n.right);
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {3,2,1,6,0,5};
		Solution s = new Solution();
		Solution.TreeNode node = s.constructMaximumBinaryTree(nums);
		s.levelorder(node);
	}
}