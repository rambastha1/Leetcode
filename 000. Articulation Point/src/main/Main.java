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
 */
class Solution {
	// Time 0(V+E) Space 0(V+E)
	
	private List<Integer> []graph;
	int time = 0;
	
	public List<Integer> apoint(int n, int [][]edges) {
		List<Integer> res = new ArrayList<>();
		build(n, edges);
		boolean []visited = new boolean[n+1], ap = new boolean[n+1];
		int []discovery = new int[n+1], low = new int[n+1], parent = new int[n+1];
		
		Arrays.fill(parent, -1);
		for(int i = 1;i <= n;i++) {
			if(!visited[i])
				dfs(i, visited, discovery, low, parent, ap);
		}
		
		for(int i = 1;i <= n;i++)
			if(ap[i])
				res.add(i);
		return res;
	}
	
	void dfs(int src, boolean []visited, int []disc, int []low, int []parent, boolean []ap) {
		int child = 0;
		visited[src] = true;
		disc[src] = low[src] = ++time;
		
		for(int dest : graph[src]) {
			if(!visited[dest]) {
				child++;
				parent[dest] = src;
				dfs(dest, visited, disc, low, parent, ap);
				low[src] = Math.min(low[src], low[dest]);
				if(parent[src] == -1 && child > 1)
					ap[src] = true;
				if(parent[src] != -1 && low[dest] >= disc[src])
					ap[src] = true;
			} else if(dest != parent[src])
				low[src] = Math.min(low[src], disc[dest]);
		}
	}
	
	
	private void build(int n, int [][]edges) {
		this.graph = new ArrayList[n+1];
		for(int i = 0;i <= n;i++)
			this.graph[i] = new ArrayList<>();
		for(int []edge : edges) {
			int src = edge[0], dest = edge[1];
			this.graph[src].add(dest);
			this.graph[dest].add(src);
		}
	}
}


public class Main {
	public static void main(String[] args) {
		/*int n = 5;
		int [][]edges = {{2,1}, {1,3}, {3,2}, {1,4}, {4,5}};*/
		/*int n = 4;
		int [][]edges = {{1,2}, {2,3}, {3,4}};*/
		int n = 7;
		int [][]edges = {{1,2}, {2,3}, {3,1}, {2,4}, {2,5}, {2,7}, {4,6}, {5,6}};
		System.out.println(new Solution().apoint(n, edges));
	}
}
