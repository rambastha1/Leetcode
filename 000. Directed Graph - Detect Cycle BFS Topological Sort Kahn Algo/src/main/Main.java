package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Time 0(V+E)
class Solution {
	int V;
	
	public Solution(int V) {
		this.V = V;
	}
	
	public boolean iscycle(int [][]graph) {
		
		List<Integer> []g = new ArrayList[V];
		for(int i = 0;i < V;i++)
			g[i] = new ArrayList<>();
		int []indegree = new int[V];
		int n = graph.length;
		
		for(int i = 0;i < n;i++) {
			g[graph[i][0]].add(graph[i][1]);
			indegree[graph[i][1]]++;
		}
		
		Queue<Integer> q = new LinkedList<>();
		for(int i = 0;i < n;i++) {
			if(indegree[i] == 0)
				q.offer(i);
		}
		int count = 0;
		while(!q.isEmpty()) {
			int node = q.poll();
			count++;
			List<Integer> child = g[node];
			for(int i : child) {
				indegree[i]--;
				if(indegree[i] == 0)
					q.offer(i);
			}
		}
		return count!=V;
	}
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution(6);
		int [][]graph = {{0,1}, {1,2}, {2,0}, {3,4}, {4,5}};
		System.out.println(s.iscycle(graph));
		
	}
}
