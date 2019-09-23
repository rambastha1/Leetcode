package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
    public TreeNode buildTree(int[] inorder, int[] postorder) {
    	if(inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0)
    		return null;
    	if(inorder.length == 1 && postorder.length == 1)
    		return new TreeNode(inorder[0]);
    	Map<Integer, Integer> map = new HashMap<>();
    	for(int i = 0;i < inorder.length;i++)
    		map.put(inorder[i], i);
    	post_index = postorder.length-1;
    	return dfs(inorder, postorder, 0, postorder.length-1, map);
    }
    
    int post_index;
    TreeNode dfs(int []inorder, int []postorder, int start, int end, Map<Integer, Integer> map) {
    	if(start > end)
    		return null;
    	TreeNode node = new TreeNode(postorder[post_index--]);
    	if(start == end)
    		return node;
    	int index = map.get(node.val);
    	// right then left this order is important 
    	// in preorder and inorder its left then right
    	node.right = dfs(inorder, postorder, index + 1, end, map);
    	node.left = dfs(inorder, postorder, start, index - 1, map);
    	
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
		int []inorder = {9,3,15,20,7}, postorder = {9,15,7,20,3};
		Solution s = new Solution();
		s.root = s.buildTree(inorder, postorder);
		s.levelorder(s.root);
	}
}
