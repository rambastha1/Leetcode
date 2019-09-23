package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//https://leetcode.com/problems/house-robber-iii/discuss/79330/Step-by-step-tackling-of-the-problem
class Solution {
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	TreeNode root;
	
	//Using Top Down Memoization
	/*
	 * Map is used so that every time this method is called, it doesn't need to calculate value 
	 * for each root. This will happen for lower child nodes.
	 * For eg. this function is called for root. it will recursively call this function with
	 * its grandchildren and so on. 
	 * then this function is called for left and right child. It will call its children and grandchildren.
	 * Thus, hashmap can reduce calculations for child nodes.
	 */
	Map<TreeNode, Integer> map = new HashMap<>();
	public int rob(TreeNode root) {
		if(root == null)
			return 0;
		if(map.containsKey(root))
			return map.get(root);
		int robroot = root.val;
		if(root.left != null) 
			robroot += rob(root.left.left) + rob(root.left.right);
		
		if(root.right != null)
			robroot += rob(root.right.left) + rob(root.right.right);
		
		int val = Math.max(robroot, rob(root.left) + rob(root.right));
		map.put(root, val);
		return val;
	}
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(4);
		s.root.left = s.new TreeNode(1);
		s.root.left.left = s.new TreeNode(2);
		s.root.left.left.left = s.new TreeNode(3);
		System.out.println(s.rob(s.root));
	}
}