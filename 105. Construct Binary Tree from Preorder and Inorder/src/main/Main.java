package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
	public class TreeNode {
		int val;
		TreeNode left, right;
		TreeNode(int val) {
			this.val = val;
		}
	}
	int pre_index = 0;
	public TreeNode buildTreeUtil(int[] preorder, int[] inorder, int in_start, int in_end, Map<Integer, Integer> map) {
		if(in_start > in_end)
			return null;
		
		int curr = preorder[pre_index++];
		TreeNode node = new TreeNode(curr);
		
		//If this node has no children
		if(in_start == in_end)
			return node;
		//else find index in inorder traversal
		int in_index = map.get(curr);
		//elements to left side of in_index form left subtree 
		//elements to right side form in_index right subtree
		node.left = buildTreeUtil(preorder, inorder, in_start, in_index-1, map);
		node.right = buildTreeUtil(preorder, inorder, in_index+1, in_end, map);
		
		return node;
	}
	
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    	if(preorder.length == 0 || inorder.length == 0)
    		return null;
    	Map<Integer, Integer> map = new HashMap<>();
    	for(int i = 0;i < preorder.length;i++)
    		map.put(inorder[i], i);
    	
    	return buildTreeUtil(preorder, inorder, 0, inorder.length-1, map);
    }
}

public class Main {
	public static void main(String[] args) {

	}
}