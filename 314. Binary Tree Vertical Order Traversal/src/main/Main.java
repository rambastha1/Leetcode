package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/* Given a binary tree, return the  vertical order  traversal of its nodes' values. 
 * (ie, from top to bottom, column by column).
 * If two nodes are in the same row and column, the order should be from  left to right .
 * 
 * Examples:
 * 
 * Given binary tree  [3,9,20,null,null,15,7],
 *    3
 *   /\
 *  /  \
 *  9  20
 *     /\
 *    /  \
 *   15   7
 * Return its vertical order traversal as:
 * 
 * [
 *   [9],
 *   [3,15],
 *   [20],
 *   [7]
 * ]
 * Given binary tree  [3,9,8,4,0,1,7],
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\   /\
 *  /  \ /  \
 *  4  0 1  7
 * Return its vertical order traversal as:
 * 
 * [
 *   [4],
 *   [9],
 *   [3,0,1],
 *   [8],
 *   [7]
 * ]
 * Given binary tree  [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
 *      3
 *     /\
 *    /  \
 *    9  8
 *   /\  /\
 *  /  \/  \
 *  4  01  7
 *     /\
 *    /  \
 *    5  2
 * Return its vertical order traversal as:
 * 
 * [
 *   [4],
 *   [9,5],
 *   [3,0,1],
 *   [8,2],
 *   [7]
 * ]
 */

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	class Node {
		TreeNode node;
		int dist;
		public Node(TreeNode node, int dist) {
			this.node = node;
			this.dist = dist;
		}
	}
	
    public List<List<Integer>> verticalOrder(TreeNode root) {
    	List<List<Integer>> res = new ArrayList<>();
    	if(root == null)
            return res;
    	TreeMap<Integer, List<Integer>> map = new TreeMap<>();
    	Queue<Node> q = new LinkedList<>();
    	q.offer(new Node(root, 0));
    	while(!q.isEmpty()) {
    		Node temp = q.poll();
    		TreeNode node = temp.node;
    		int dist = temp.dist;
    		if(!map.containsKey(dist))
    			map.put(dist, new ArrayList<>());
    		map.get(dist).add(node.val);
    		if(node.left != null)
    			q.offer(new Node(node.left, dist-1));
    		if(node.right != null)
    			q.offer(new Node(node.right, dist+1));
    	}
    	for(List<Integer> list : map.values()) {
    		res.add(list);
    	}
    	return res;
    }
    
    public TreeNode root;
    
    public void levelorder(TreeNode root) {
        if(root == null)
        	return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
        	TreeNode node = q.poll();
        	System.out.print(node.val + " ");
        	if(node.left != null) {
        		q.offer(node.left);
        	}
        	if(node.right != null)
        		q.offer(node.right);
        }
        System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(3);
		s.root.left = s.new TreeNode(9);
		s.root.left.left = s.new TreeNode(4);
		s.root.left.right = s.new TreeNode(0);
		s.root.left.right.left = s.new TreeNode(5);
		s.root.left.right.right = s.new TreeNode(2);
		s.root.right = s.new TreeNode(8);
		s.root.right.left = s.new TreeNode(1);
		s.root.right.right = s.new TreeNode(7);
		s.levelorder(s.root);
		System.out.println(s.verticalOrder(s.root));
	}
}
