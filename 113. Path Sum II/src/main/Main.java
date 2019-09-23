package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public TreeNode root;
	
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> curr = new ArrayList<>();
		dfs(root, sum, res, curr, 0);
		return res;
	}
	
	void dfs(TreeNode root, int target, List<List<Integer>> res, List<Integer> curr, int sum) {
		if(root == null)
			return;
		
		curr.add(root.val);
		sum += root.val;
		if(sum == target && root.left == null && root.right == null) {
			res.add(new ArrayList<>(curr));
		}
		dfs(root.left, target, res, curr, sum);
		dfs(root.right, target, res, curr, sum);
		curr.remove(curr.size()-1);		
	}
	
	
	//Working but slow No need of Map
    /*public List<List<Integer>> pathSum(TreeNode root, int sum) {
    	List<List<Integer>> res = new ArrayList<>();
    	Map<Integer, List<Integer>> map = new HashMap<>();
    	if(root.left == null && root.right == null) {
    		if(sum == root.val) {
    			res.add(new ArrayList<>(root.val));
    			return res;
    		} else
    			return res;
    	}
    	dfs(root, sum, res, new ArrayList(), map, 0);
    	return res;
    }
    
    void dfs(TreeNode root, int target, List<List<Integer>> res, List<Integer> curr,
    		Map<Integer, List<Integer>> map, int curr_sum) {
    	
    	if(root == null)
    		return;
    	
    	if(curr_sum + root.val == target && root.left == null && root.right == null) {
    		map.get(curr_sum).add(root.val);
    		res.add(new ArrayList<>(curr));
    		//This step is important
    		curr.remove(curr.size()-1);
    		return;
    	}
    	
    	curr_sum += root.val;
    	curr.add(curr.size(), root.val);
    	map.put(curr_sum, curr);
    	    	
    	dfs(root.left, target, res, curr, map, curr_sum);
    	dfs(root.right, target, res, curr, map, curr_sum);
    	
    	curr.remove(curr.size()-1);
    	map.put(curr_sum, curr);
    }*/
    
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
		Solution s = new Solution();
		s.root = s.new TreeNode(5);
		s.root.left = s.new TreeNode(4);
		s.root.right = s.new TreeNode(8);
		s.root.left.left = s.new TreeNode(11);
		s.root.left.left.left = s.new TreeNode(7);
		s.root.left.left.right = s.new TreeNode(2);
		s.root.right.left = s.new TreeNode(13);
		s.root.right.right = s.new TreeNode(4);
		s.root.right.right.left = s.new TreeNode(5);
		s.root.right.right.right = s.new TreeNode(1);
		
		s.levelorder(s.root);
		int sum = 22;
		System.out.println(s.pathSum(s.root, sum));
		
	}
}