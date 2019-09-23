package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import main.Solution.TreeNode;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
    public List<TreeNode> generateTrees(int n) {
    	if(n == 0)
    		return new ArrayList<>();
        return dfs(1, n);
    }
    
    List<TreeNode> dfs(int start, int end) {
    	List<TreeNode> res = new ArrayList<>();
    	//empty tree
    	if(start > end) {
    		res.add(null);
    		return res;
    	}
    	
    	// For all i's as root
    	for(int i = start;i <= end;i++) {
    		List<TreeNode> left = dfs(start, i-1);
    		List<TreeNode> right = dfs(i+1, end);
    		
    		for(TreeNode l : left) {
    			for(TreeNode r : right) {
    				TreeNode node = new TreeNode(i);
    				node.left = l;
    				node.right = r;
    				res.add(node);
    			}
    		}
    	}
    	return res;
    }
    
    void levelorder(TreeNode root) {
    	if(root == null)
    		return;
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(root);
    	while(!q.isEmpty()) {
    		System.out.print(q.peek().val + " ");
    		TreeNode node = q.poll();
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
		int n = 7;
		Solution s = new Solution();
		List<Solution.TreeNode> root = s.generateTrees(n);
		for(TreeNode t : root)
			s.levelorder(t);
	}
}