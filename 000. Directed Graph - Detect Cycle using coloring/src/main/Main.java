package main;

import java.util.ArrayList;
import java.util.List;

class Solution {
	int V;
	List<List<Integer>> graph;
	
	enum color {
		WHITE, GRAY, BLACK;
	}
	
	public Solution(int V) {
		this.V = V;
		graph = new ArrayList<>();
		for(int i = 0;i < V;i++) {
			graph.add(new ArrayList<>());
		}
	}
	
	void addedge(int src, int dest) {
		graph.get(src).add(dest);
	}
	
	boolean iscycle() {
		color []c = new color[this.V];
		for(int i = 0;i < V;i++)
			c[i] = color.WHITE;
		
		for(int i = 0;i < V;i++) {
			if(c[i] == color.WHITE && dfsutil(i, c))
				return true;
		}
		return false; 
	}
	
	boolean dfsutil(int i, color []c) {
		c[i] = color.GRAY;
		List<Integer> child = graph.get(i);
		for(Integer dest : child) {
			if(c[dest] == color.GRAY)
				return true;
			if(c[dest] == color.WHITE && dfsutil(dest, c))
				return true;
		}
		c[i] = color.BLACK;
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
