package main;

import java.util.ArrayList;
import java.util.List;

class Solution {
	int V;
	public Solution(int V) {
		this.V = V;
	}
	
	boolean iscycleUtil(int [][]graph, int vertex, boolean []visited, int parent) {
		visited[vertex] = true;
		for(int dest : graph[vertex]) {
			if(!visited[dest]) {
				if(iscycleUtil(graph, dest, visited, vertex)) // vertex is passed as parent
					return true;
				// w is discovered and w is not a parent
				// we found a back-edge (cycle)
			} else if(dest != parent) return true;
		}
		return false;
	}
	
	boolean iscycle(int [][]graph) {
		boolean []visited = new boolean[this.V];
		for(int i = 0;i < graph.length;i++) {
			if(!visited[i]) {
				if(iscycleUtil(graph, i, visited, -1))
					return true;
			}
		}
		return false;
	}	
}

public class Main {
	public static void main(String[] args) {
		//int [][]graph = {{1,0}, {0,2}, {2,0}, {0,3}, {3,4}};
		//System.out.println(new Solution(5).iscycle(graph));
		int [][]graph = {{0,1}, {1,2}};
		System.out.println(new Solution(3).iscycle(graph));
	}
}
