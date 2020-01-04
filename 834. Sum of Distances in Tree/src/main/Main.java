package main;
/*
 * https://www.youtube.com/watch?v=gm4Ye0fESpU
 * for max height from each node
 * https://www.youtube.com/watch?v=Xng1Od_v6Ug&t=1304s
 * https://blogarithms.github.io/articles/2019-10/inout-dp-tree
 * https://www.geeksforgeeks.org/dynamic-programming-trees-set-2/
 * https://codeforces.com/blog/entry/20935
 * https://leetcode.com/problems/sum-of-distances-in-tree/discuss/130583/C%2B%2BJavaPython-Pre-order-and-Post-order-DFS-O(N)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	int N;
	/* count[i] -> count of nodes in subtree rooted with i 
	 * in[i] -> sum of distances from i to all nodes in subtree of i
	 */
	int []count, res;
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
    	this.N = N;
    	Map<Integer, List<Integer>> graph = build(edges);
    	res = new int[N]; 
    	count = new int[N];
    	
    	dfs1(graph, 0, -1);
    	dfs2(graph, 0, -1);
    	
    	return res;
    }
    
    // bottom up
    private void dfs1(Map<Integer, List<Integer>> graph, int node, int parent) {
    	
    	count[node] = 1;
    	if(!graph.containsKey(node))
    		return;
    	for(int dest : graph.get(node)) {
    		if(dest == parent)
    			continue;
    		dfs1(graph, dest, node);
    		count[node] += count[dest];
    		res[node] += res[dest] + count[dest];
    	}
    }
    // top-down
    private void dfs2(Map<Integer, List<Integer>> graph, int node, int parent) {
    	if(!graph.containsKey(node))
    		return;
    	for(int dest : graph.get(node)) {
    		if(dest == parent)
    			continue;
    		// (this.N - count[dest]) -> number of nodes outside of subtree rooted with dest
    		//out[dest] = (res[node] - res[dest] - count[dest]) + out[node] + (this.N - count[dest]);
    		/* res[node] - count[dest] will remove all length in subtree rooted with dest
    		 * only distance will remain is parent-dest + parent to all siblings of dest
    		 */
    		res[dest] = res[node] - count[dest] + this.N - count[dest]; 
    		dfs2(graph, dest, node);
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
		int N = 6;
		int [][]edges = {{0,1},{0,2},{2,3},{2,4},{2,5}};
		System.out.println(Arrays.toString(new Solution().sumOfDistancesInTree(N, edges)));
	}
}
