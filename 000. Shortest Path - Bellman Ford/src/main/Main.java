package main;

import java.util.Arrays;

class Solution {
	class Edge {
		int src, dest, weight;
		public Edge(int src, int dest, int weight) {
			this.dest = dest;
			this.src = src;
			this.weight = weight;
		}		
	}
	int V, E;
	Edge []edges;
	public Solution(int [][]graph, int V) {
		this.V = V;
		this.E = graph.length;
		edges = new Edge[E];
		for(int i = 0;i < graph.length;i++) {
			edges[i] = new Edge(graph[i][0], graph[i][1], graph[i][2]);
		}
	}
	
	boolean bellman(int src) {
		int []dist = new int[this.V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;
		for(int i = 1;i < V;i++) {
			for(int j = 0;j < this.E;j++) {
				int u = edges[j].src;
				int v = edges[j].dest;
				int w = edges[j].weight;
				if(dist[u] != Integer.MAX_VALUE && dist[u]+w < dist[v]) {
					dist[v] = dist[u] + w;
				}
			}
		}
		
		for(int j = 0;j < E;j++) {
			int u = edges[j].src;
			int v = edges[j].dest;
			int w = edges[j].weight;
			if(dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
				print(dist);
				return true;
			}
		}
		print(dist);
		return false;
	}
	
	void print(int []dist) {
		System.out.println("Vertex\tDistance from Source");
		for(int i = 0;i < dist.length;i++)
			System.out.println(i + "\t" + dist[i]); 
	}
}

public class Main {
	public static void main(String[] args) {
		int V = 5, E = 8;
		int [][]graph = {{0,1,-1}, {0,2,4}, {1,2,3}, {1,3,2}, {1,4,2}, {3,2,5}, {3,1,1}, {4,3,-3}};
		System.out.println(new Solution(graph, V).bellman(0));
	}
}
