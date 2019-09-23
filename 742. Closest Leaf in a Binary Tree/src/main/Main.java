package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/* Given a binary tree where every node has a unique value, and a target key k, 
 * find the closest leaf node to target k in the tree.
 * A node is called a leaf if it has no children.
 * In the following examples, the input tree is represented in flattened form row by row. 
 * The actual root tree given will be a TreeNode object.
 * 
 * root = [1,2,3,4,null,null,null,5,null,6], k = 2
 * Diagram of binary tree:
 *              1
 *             / \
 *            2   3
 *           /
 *          4
 *         /
 *        5
 *       /
 *      6
 * 
 * Output: 3
 * Explanation: The leaf node with value 3 (and not the leaf node with value 6) is closest 
 * to the node with value 2.
 * Note:
 * root represents a binary tree with at least 1 node and at most 1000 nodes.
 * Every node has a unique node.val in range [1, 1000].
 * There exists some node in the given binary tree for which node.val == k.
 * 
 */

// Like 863 build undirected graph and do BFS 

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public TreeNode root, start;
	Map<TreeNode, List<TreeNode>> graph;
	
	public int findClosest(TreeNode root, int key) {
		start = null;
		graph = new HashMap<>();
		buildgraph(root, null, key);
		
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(start);
		Set<TreeNode> visited = new HashSet<>();
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i = 0;i < size;i++) {
				TreeNode node = q.poll();
				visited.add(node);
				// since its BFS and undirected graph this will be shortest
				if(node.left == null && node.right == null)
					return node.val;
				for(TreeNode n : graph.get(node)) {
					if(!visited.contains(n))
						q.offer(n);
				}
			}
		}
		return 0;
	}
	
	void buildgraph(TreeNode root, TreeNode parent, int k) {
		if(root == null)
			return;
		if(root.val == k)
			this.start = root;
		if(parent != null) {
			if(!graph.containsKey(root))
				graph.put(root, new ArrayList<>());
			if(!graph.containsKey(parent))
				graph.put(parent, new ArrayList<>());
			graph.get(root).add(parent);
			graph.get(parent).add(root);
		}
		buildgraph(root.left, root, k);
		buildgraph(root.right, root, k);
	}
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(2);
		s.root.right = s.new TreeNode(3);
		s.root.left.left = s.new TreeNode(4);
		s.root.left.left.left = s.new TreeNode(5);
		s.root.left.left.left.left = s.new TreeNode(6);
		System.out.println(s.findClosest(s.root, 2));
	}
}