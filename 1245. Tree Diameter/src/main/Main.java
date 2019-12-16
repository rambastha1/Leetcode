package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.

The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.  
Each node has labels in the set {0, 1, ..., edges.length}.

 

Example 1:



Input: edges = [[0,1],[0,2]]
Output: 2
Explanation: 
A longest path of the tree is the path 1 - 0 - 2.
Example 2:



Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
Output: 4
Explanation: 
A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.
 

Constraints:

0 <= edges.length < 10^4
edges[i][0] != edges[i][1]
0 <= edges[i][j] <= edges.length
The given edges form an undirected tree.
 * 
 */

// https://leetcode.com/problems/tree-diameter/discuss/418982/Java-Depth-of-the-Tree-solution-Time-O(N)-Easy-to-understand
/* Travese all the nodes of the tree. The diameter of the tree is maximum of the longest path through each node.
Longest path through a node is sum of top 2 depths of children's tree.
 * Similar logic to 543. Diameter of Binary Tree
 * Longest diameter in n-ary tree
 */
class Solution {
	int diameter = 0;
    public int treeDiameter(int[][] edges) {
    	Map<Integer, List<Integer>> graph = new HashMap<>();
    	for(int []edge : edges) {
    		if(!graph.containsKey(edge[0]))
    			graph.put(edge[0], new ArrayList<>());
    		if(!graph.containsKey(edge[1]))
    			graph.put(edge[1], new ArrayList<>());
    		graph.get(edge[0]).add(edge[1]);
    		graph.get(edge[1]).add(edge[0]);
    	}
    	// assuming 0 as root of tree
//The given edges form an undirected tree. means all nodes are connected and form a tree, we can make any node as a root of the tree.
    	// parent variable instead of tracking all visited vertices
    	dfs(graph, 0, -1);
    	return this.diameter;
    }
    
    int dfs(Map<Integer, List<Integer>> graph, int src, int parent) {
    	int max = 0, secmax = 0;
    	for(int dest : graph.get(src)) {
    		// Only one way from root node to child node, don't allow child node go to root node again!
    		if(dest != parent) {
	    		int depth = dfs(graph, dest, src);
	    		if(depth > max) {
	    			secmax = max;
	    			max = depth;
	    		} else if(depth > secmax) {
	    			secmax = depth;
	    		}
    		}
    	}
    	int longest = max + secmax;
    	this.diameter = Math.max(this.diameter, longest);
    	return max + 1;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]edges = {{0,1},{1,2},{2,3},{1,4},{4,5}};
		System.out.println(new Solution().treeDiameter(edges));
	}
}
