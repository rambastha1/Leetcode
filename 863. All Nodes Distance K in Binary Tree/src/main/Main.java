package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * It would be very easy to find the neighbors in K distance in an undirected graph, 
 * so I convert the tree structure into an undirected graph that a node's parent and 
 * children become it's neighbors.
 * 
 * Do BFS to find all nodes at distance K
 */

// https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/discuss/143752/JAVA-Graph-%2B-BFS

// https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/discuss/143798/1ms-beat-100-simple-Java-dfs-using-hashmap-with-explanation

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	public TreeNode root;
	Map<TreeNode, List<TreeNode>> map = new HashMap<>();
    
	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
		List<Integer> res = new ArrayList<>();
		if(root == null || K < 0)
			return res;
		//Build undirected graph
		buildgraph(root, null);
		if(!map.containsKey(target))
			return res;
		
		Set<TreeNode> visited = new HashSet<>();
		Queue<TreeNode> q = new LinkedList<>();
    	q.add(target);
    	visited.add(target);
    	
    	while(!q.isEmpty()) {
    		int size = q.size();
    		if(K == 0) {
    			for(int i = 0;i < size;i++)
    				res.add(q.poll().val);
    			return res;
    		} 
    		for(int i = 0;i < size;i++) {
    			TreeNode node = q.poll();
    			for(TreeNode temp : map.get(node)) {
    				if(!visited.contains(temp)) {
    					visited.add(temp);
    					q.offer(temp);
    				}
    			}
    		}
    		K--;
    	}    	
    	return res;
    }
	
	void buildgraph(TreeNode node, TreeNode parent) {
		if(node == null)
			return;
		
		if(!map.containsKey(node)) {
			map.put(node, new ArrayList<>());
			if(parent != null) {
				map.get(parent).add(node);
				map.get(node).add(parent);
			}
			buildgraph(node.left, node);
			buildgraph(node.right, node);
		}		
	}
    
    
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(3);
		s.root.left = s.new TreeNode(5);
		s.root.right = s.new TreeNode(1);
		s.root.left.left = s.new TreeNode(6);
		s.root.left.right = s.new TreeNode(2);
		s.root.left.right.left = s.new TreeNode(7);
		s.root.left.right.right = s.new TreeNode(4);
		s.root.right.left = s.new TreeNode(0);
		s.root.right.right = s.new TreeNode(8);
		System.out.println(s.distanceK(s.root, s.root.left, 2));
	}
}