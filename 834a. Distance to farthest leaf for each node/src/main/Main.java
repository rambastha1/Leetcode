package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* Given a tree with N nodes and N-1 edges, find out the maximum height of tree when any node in the tree is 
 * considered as the root of the tree.
 * Time 0(N) In-Out DP
 * https://www.geeksforgeeks.org/dynamic-programming-trees-set-1/
 * https://www.geeksforgeeks.org/dynamic-programming-trees-set-2/
 */
class Solution {
	// Time 0(N) Space 0(N)
	int n;
	public int[] height(int n, int [][]edges) {
		this.n = n;
		// n+1 because node start from 1
		int []in = new int[n+1], out = new int[n+1];
		
		Map<Integer, List<Integer>> graph = build(edges);
		dfs1(graph, 1, 0, in, out);
		dfs2(graph, 1, 0, in, out);
		int []res = new int[n];
		for(int i = 1;i <= n;i++) 
			res[i-1] = Math.max(in[i], out[i]);
		return res;
	}
	// calculate in- height (subtree like normal height of tree)
	private void dfs1(Map<Integer, List<Integer>> graph, int node, int parent, int []in, int []out) {
		//in[leaf node] = 0
		in[node] = 0;
		if(!graph.containsKey(node))
			return;
		for(int dest : graph.get(node)) {
			// it is undirected graph thus necessary there will be edge
			// make sure move downwards only
			if(dest == parent)
				continue;
			dfs1(graph, dest, node, in, out);
			in[node] = Math.max(in[node], 1 + in[dest]);
		}
	}
	
	// out-height
	private void dfs2(Map<Integer, List<Integer>> graph, int node, int parent, int []in, int []out) {
		/* two values because all nodes considering in the subtree of parent, this node is also one
		 * it might be case that in[parent] goes through this node  
		 * thus for out[node] maximum sibling -> consider second longest for out[sibling]
		 */
		int max = -1, secmax = -1;
		if(!graph.containsKey(node))
			return;
		for(int dest : graph.get(node)) {
			if(dest == parent)
				continue;
			if(in[dest] > max) {
				secmax = max;
				max = in[dest];
			} else if(in[dest] > secmax) {
				secmax = in[dest];
			}
		}
		
		for(int dest : graph.get(node)) {
			if(dest == parent)
				continue;
			int longest = max;
			if(in[dest] == max) {
				longest = secmax;
			}
			/* node-parent(dest-node) is common so 1 +
			 * out[node] is out[parent]
			 * longest is maximum siblings height
			 */
			out[dest] = 1 + Math.max(out[node], 1 + longest);
			dfs2(graph, dest, node, in, out);
		}
	}
	
	private Map<Integer, List<Integer>> build(int [][]edges) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		for(int []edge : edges) {
			if(!map.containsKey(edge[0]))
				map.put(edge[0], new ArrayList<>());
			if(!map.containsKey(edge[1]))
				map.put(edge[1], new ArrayList<>());
			map.get(edge[0]).add(edge[1]);
			map.get(edge[1]).add(edge[0]);
		}
		return map;
	}
}

public class Main {
	public static void main(String[] args) {
		int n = 11;
		int [][]edges = {{1,2}, {1,3}, {1,4}, {2,5}, {2,6}, {3,7}, {4,8}, {4,9}, {7,10}, {7,11}};
		System.out.println(Arrays.toString(new Solution().height(n, edges)));
	}
}
