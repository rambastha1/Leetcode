package main;

import java.util.ArrayList;
import java.util.List;

// If graph has cycle 
class Solution {
	
	enum color {GRAY, WHITE, BLACK};
	
    public List<Integer> eventualSafeNodes(int[][] graph) {
    	List<Integer> res = new ArrayList<>();
    	if(graph == null || graph.length == 0)
    		return res;
    	int n = graph.length;
    	color []c = new color[n];
    	for(int i = 0;i < n;i++) 
    		c[i] = color.WHITE;
    	
    	for(int i = 0;i < n;i ++) {
    		if(dfs(graph, i, c))
    			res.add(i);
    	}
    	return res;
    }
    
    // DFS returns true is the node is safe
    public boolean dfs(int [][]graph, int node, color []c) {
    	// if already visited check if its safe
    	if(c[node] != color.WHITE)
    		return c[node] == color.BLACK;
    	
    	// mark unsafe or in progress
    	c[node] = color.GRAY;
    	
    	// check for child nodes
    	for(int dest : graph[node]) {
    		if(!dfs(graph, dest, c)) 
    			return false;
    	}
    	// processing done mark safe
    	c[node] = color.BLACK;
    	//return this node is safe
    	return true;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]graph = {{1,2},{2,3},{5},{0},{5},{},{}};
		System.out.println(new Solution().eventualSafeNodes(graph));
	}
}
