package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// https://leetcode.com/articles/equal-tree-partition/

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	// Time 0(N) space 0(N)
    public boolean checkEqualTree(TreeNode root) {
    	if(root == null || root.left == null && root.right == null)
    		return false;
    	Set<Integer> set = new HashSet<>();
    	int sum = root.val + dfs(root.left, set) + dfs(root.right, set);
    	return sum%2 == 0 && set.contains(sum/2);
    }
    
    int dfs(TreeNode root, Set<Integer> set) {
    	if(root == null)
    		return 0;
    	int sum = 0;
    	sum += root.val + dfs(root.left, set) + dfs(root.right, set);
    	set.add(sum);
    	return sum;
    }
    
    public TreeNode root;
    
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
		Solution s = new Solution();
		s.root = s.new TreeNode(5);
		s.root.left = s.new TreeNode(10);
		s.root.right = s.new TreeNode(10);
		s.root.right.left = s.new TreeNode(2);
		s.root.right.right = s.new TreeNode(3);
		System.out.println(s.checkEqualTree(s.root));
		//s.levelorder(s.root);
	}
}
