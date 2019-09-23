package main;

import java.util.LinkedList;
import java.util.Queue;

import javafx.util.Pair;

/* The idea is to use heap indexing:
 * 
 *         1
 *    2         3
 *  4   5     6   7
 * 8 9 x 11  x 13 x 15
 * Regardless whether these nodes exist:
 * 
 * Always make the id of left child as parent_id * 2;
 * Always make the id of right child as parent_id * 2 + 1;
 * So we can just:
 * 
 * Record the id of left most node when first time at each level of the tree during an pre-order run.
 * (you can tell by check the size of the container to hold the first nodes);
 * At each node, compare the distance from it the left most node with the current max width;
 * 
 */

class Solution {
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
    public int widthOfBinaryTree(TreeNode root) {
    	if(root == null)
    		return 0;
    	int ans = 0;
    	Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();
    	q.offer(new Pair<TreeNode, Integer>(root, 1));
    	/* left contains value of peek i.e leftmost node see left nodes are inserted before right ones
    	 * right counter moves on with every poll, at last it will contain index of right most node
    	 * thus right - left + 1 is width
    	 * 
    	 * indexing represent full binary tree in array 
    	 * heaps can be represented by array
    	 * use indexing to find positions, find max difference 
    	 */
    	
    	while(!q.isEmpty()) {
    		int size = q.size();
    		int left = q.peek().getValue(), right = left;
    		for(int i = 0;i < size;i++) {
    			Pair<TreeNode, Integer> pair = q.poll();
    			TreeNode node = pair.getKey();
    			right = pair.getValue();
    			if(node.left != null) 
    				q.offer(new Pair<TreeNode, Integer>(node.left, right*2));
    			if(node.right != null)
    				q.offer(new Pair<TreeNode, Integer>(node.right, right*2+1));
    		}
    		ans = Math.max(ans, right - left + 1);
    	}
    	return ans;
    }
    
    public TreeNode root;
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(3);
		s.root.left.left = s.new TreeNode(5);
		s.root.left.right = s.new TreeNode(3);
		s.root.right = s.new TreeNode(2);
		s.root.right.right = s.new TreeNode(9);
		System.out.println(new Solution().widthOfBinaryTree(s.root));
		
	}
}
