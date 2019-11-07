package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* https://www.quora.com/q/lbsknuilzujwaqtg/Cut-Vertex-Articulation-point
 * https://blog.vcillusion.co.in/why-articulations-point-or-cut-vertex-in-a-graph-is-sexy/
 * https://www.hackerearth.com/practice/algorithms/graphs/articulation-points-and-bridges/tutorial/
 * https://www.youtube.com/watch?v=2kREIkF9UAs&feature=youtu.be
 * https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
 * https://www.geeksforgeeks.org/bridge-in-a-graph/
 * https://www.geeksforgeeks.org/check-removing-given-edge-disconnects-given-graph/
 * https://leetcode.com/discuss/interview-question/372581
 * https://www.geeksforgeeks.org/tarjan-algorithm-find-strongly-connected-components/
 * https://www.youtube.com/watch?v=ju9Yk7OOEb8
 * 
 */

class Solution {
	
	private List<Integer> []graph;
	private int time = 0;
	// Time 0(V+E)
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    	List<List<Integer>> res = new ArrayList<>();
		build(n, connections);
		boolean []visited = new boolean[n+1], ap = new boolean[n+1];
		int []discovery = new int[n+1], low = new int[n+1], parent = new int[n+1];
		Arrays.fill(parent, -1);
		for(int i = 1;i <= n;i++) {
			if(!visited[i])
				dfs(i, visited, discovery, low, parent, ap, res);
		}
		return res;
    }
    
    private void dfs(int src, boolean []visited, int []disc, int []low, int []parent, boolean []ap, List<List<Integer>> res) {
		visited[src] = true;
		disc[src] = low[src] = time++;
		for(int dest : graph[src]) {
			if(!visited[dest]) {
				parent[dest] = src;
				dfs(dest, visited, disc, low, parent, ap, res);
				low[src] = Math.min(low[src], low[dest]);
				if(low[dest] > disc[src])
					res.add(Arrays.asList(src, dest));
			} else if(dest != parent[src]) {
				low[src] = Math.min(low[src], disc[dest]);
			}
		}
	}
	
	
	private void build(int n, List<List<Integer>> connections) {
		this.graph = new ArrayList[n+1];
		for(int i = 0;i <= n;i++)
			this.graph[i] = new ArrayList<>();
		for(List<Integer>edge : connections) {
			int src = edge.get(0), dest = edge.get(1);
			this.graph[src].add(dest);
			this.graph[dest].add(src);
		}
	}
}

public class Main {
	public static void main(String[] args) {
		int n = 4;
		List<List<Integer>> connections = Arrays.asList(Arrays.asList(0,1),Arrays.asList(1,2),Arrays.asList(2,0),
				Arrays.asList(1,3));
		System.out.println(new Solution().criticalConnections(n, connections));
	}
}