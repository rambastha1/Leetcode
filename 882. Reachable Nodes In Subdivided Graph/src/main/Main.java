package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/* https://leetcode.com/problems/reachable-nodes-in-subdivided-graph/discuss/157252/Logical-Thinking-with-Clear-Code
 * 
 */
class Solution {
	
	public int reachableNodes(int[][] edges, int M, int N) {
		int [][]graph = new int[N][N];
		for(int i = 0;i < N;i++)
			Arrays.fill(graph[i], -1);
		// weight is basically how far a node can reach
		for(int []edge : edges) {
			int src = edge[0], dest = edge[1], weight = edge[2];
			// undirected graph
			graph[src][dest] = weight;
			graph[dest][src] = weight;
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (b[1] - a[1]));
		pq.offer(new int[] {0, M});
		int ans = 0;
		boolean []visited = new boolean[N];
		
		while(!pq.isEmpty()) {
			int []node = pq.poll();
			int src = node[0], move = node[1];
			if(visited[src])
				continue;
			visited[src] = true;
			ans++;
			for(int i = 0;i < N;i++) {
				if(graph[src][i] > -1) {
					if(move > graph[src][i] && !visited[i]) {
						// why -1? 
						// as there are n nodes in between src and destination
						pq.offer(new int[] {i, move - graph[src][i] - 1});
					}
					// decrease move traveled from src 
					// since graph[dest][src] is initialized as weight decrease this move 
					int cost = Math.min(move, graph[src][i]);
					graph[i][src] -= cost;
					// add move traveled to answer
					ans += cost; 
				}
			}
			
		}
		return ans;
	}
	
	// Brute Force working but TLE
    public int reachableNodes1(int[][] edges, int M, int N) {
    	if(edges.length == 0)
    		return 0;
    	if(M == 0)
    		return 1;
    	int nodes = N;
    	Map<Integer, Set<Integer>> graph = new HashMap<>();
    	Set<Integer> visited = new HashSet<>();
    	
    	for(int []edge : edges) {
    		int src = edge[0], dest = edge[1], add = edge[2];
    		
    		for(int i = 1;i <= add;i++) {
    			if(!graph.containsKey(src))
    				graph.put(src, new HashSet<>());
    			if(!graph.containsKey(nodes))
    				graph.put(nodes, new HashSet<>());
    			graph.get(src).add(nodes);
    			graph.get(nodes).add(src);
    			src = nodes++;
    		}
    		if(!graph.containsKey(src))
				graph.put(src, new HashSet<>());
    		if(!graph.containsKey(dest))
				graph.put(dest, new HashSet<>());
    		graph.get(src).add(dest);
    		graph.get(dest).add(src);
    	}
    	return count(graph, visited, M);
    }
    
    private int count(Map<Integer, Set<Integer>> graph, Set<Integer> visited, int M) {
    	Queue<Integer> q = new LinkedList<>();
    	q.offer(0);
    	visited.add(0);
    	int ans = 1;
    	
    	while(!q.isEmpty() && M > 0) {
    		int size = q.size();
    		for(int j = 0;j < size;j++) {
    			int node = q.poll();
    			if(!graph.containsKey(node))
    				continue;
    			for(int dest : graph.get(node)) {
    				if(!visited.contains(dest)) {
    					visited.add(dest);
    					q.offer(dest);
    					ans++;
    				}
    			}
    		}
    		M--;
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int [][]edges = {{0,1,10},{0,2,1},{1,2,2}};
		int M = 6, N = 3;*/
		int [][]edges = {{0,1,4},{1,2,6},{0,2,8}, {1,3,1}};
		int M = 10, N = 4;
		System.out.println(new Solution().reachableNodes(edges, M, N));
	}
}
