package main;

import java.util.ArrayList;
import java.util.List;

// Time 0(V+E)
class Solution {
	int V;
	List<List<Integer>> edges;
	public Solution(int V) {
		this.V = V;
		edges = new ArrayList<>();
		for(int i = 0;i < V;i++)
			edges.add(new ArrayList<>());
	}
	
	public void addedge(int src, int dest) {
		 edges.get(src).add(dest);
	}
	
	public boolean iscycle() {
		boolean []stack = new boolean[V];
		boolean []visited = new boolean[V];
		for(int i = 0;i < V;i++) {
			if(iscycleutil(i, stack, visited))
				return true;
		}
		return false;
	}
	
	public boolean iscycleutil(int i, boolean []stack, boolean []visited) {
		if(stack[i]) return true; // still in process and backedge means cycle
		if(visited[i]) return false; // Visited only if its safe
		
		stack[i] = true;
		visited[i] = true;
		List<Integer> child = edges.get(i);
		for(Integer dest : child) {
			if(iscycleutil(dest, stack, visited)) 
				return true;
		}
		stack[i] = false;
		return false;
	}
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution(4);
		s.addedge(0, 1);
		s.addedge(0, 2);
		s.addedge(1, 2);
		s.addedge(2, 0);
		s.addedge(2, 3);
		s.addedge(3, 3);
		System.out.println(s.iscycle());
	}
}
