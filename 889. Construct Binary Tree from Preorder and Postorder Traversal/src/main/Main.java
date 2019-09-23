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
	public TreeNode root;
	
	int pre_index = 0;
	public TreeNode constructFromPrePost(int[] pre, int[] post) {
    	if(pre == null || post == null)
    		return null;
    	Map<Integer, Integer> map = new HashMap<>();
    	for(int i = 0;i < post.length;i++)
    		map.put(post[i], i);
    	return constructFromPrePostUtil(pre, post, map, 0, pre.length-1);
    }
    
    public TreeNode constructFromPrePostUtil(int[] pre, int[] post, Map<Integer, Integer> map, int post_start, 
    		int post_end) {
    	if(post_start > post_end || pre_index >= pre.length)
    		return null;
    	TreeNode node = new TreeNode(pre[pre_index++]);	
    	if(post_start == post_end || pre_index >= pre.length)
    		return node;
    	int index = map.get(pre[pre_index]);
    	
    	/*
    	 * Here post_end will be index of root of this tree or subtree in post array
    	 * This "if" condition is crucial it stops from going to infinite loop
    	 * Moreover it stops from looping over elements which are not part of subtree of root
    	 * root = post[post_end]
    	 */
    	
    	if(index < post_end) {
    		node.left = constructFromPrePostUtil(pre, post, map, post_start, index);
    		node.right = constructFromPrePostUtil(pre, post, map, index+1, post_end-1);
    	}
    	return node;
    }	 
    
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
    		if(node.right!= null)
    			q.offer(node.right);
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int []pre = {1,2,4,5,3,6,7};
		int []post = {4,5,2,6,7,3,1};
		/*int pre[] = { 1, 2, 4, 8, 9, 5, 3, 6, 7 }; 
        int post[] = { 8, 9, 4, 5, 2, 6, 7, 3, 1 };*/
		Solution s = new Solution();
		s.root = s.constructFromPrePost(pre, post);
		s.levelorder(s.root);
		//s.inorder(s.root);
	}
}