package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/* 95. 96
 * https://leetcode.com/articles/all-possible-full-binary-trees/
 * Nodes with N >= 3 have 2 child
 * Full binary tree have positive odd number of nodes (all child at left is complete binary tree)
 * Full is 2 child 
 * https://stackoverflow.com/questions/12359660/difference-between-complete-binary-tree-strict-binary-tree-full-binary-tre
 */

class Solution {
	
	public class TreeNode {
		int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
	
	public TreeNode root;
	
    public List<TreeNode> allPossibleFBT(int N) {
    	List<TreeNode> res = new ArrayList<>();
    	if(N == 1) {
    		res.add(new TreeNode(0));
    		return res;
    	}
    	
    	// Number of nodes in Full binary Tree is odd
    	for(int left = 1;left <= N-1;left+=2) {
    		int right = N-1-left;
    		List<TreeNode> leftN = allPossibleFBT(left);
    		List<TreeNode> rightN = allPossibleFBT(right);
    		for(TreeNode l : leftN) {
    			for(TreeNode r : rightN) {
    				TreeNode node = new TreeNode(0);
    				node.left = l;
    				node.right = r;
    				res.add(node);
    			}
    		}
    	}
    	return res;
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
    	System.out.println();
    }
}


public class Main {
	public static void main(String[] args) {
		int N = 7;
		List<Solution.TreeNode> list = new Solution().allPossibleFBT(N);
		Solution s = new Solution();
		for(Solution.TreeNode node : list) {
			s.levelorder(node);
		}
	}
}