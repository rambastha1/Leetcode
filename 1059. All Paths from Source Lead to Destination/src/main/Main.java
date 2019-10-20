package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* Given the edges of a directed graph, and two nodes source and destination of this graph, 
 * determine whether or not all paths starting from source eventually end at destination, that is:

At least one path exists from the source node to the destination node
If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
The number of possible paths from source to destination is a finite number.
Return true if and only if all roads from source lead to destination.

 

Example 1:



Input: n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
Output: false
Explanation: It is possible to reach and get stuck on both node 1 and node 2.
Example 2:



Input: n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
Output: false
Explanation: We have two possibilities: to end at node 3, or to loop over node 1 and node 2 indefinitely.
Example 3:



Input: n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
Output: true
Example 4:



Input: n = 3, edges = [[0,1],[1,1],[1,2]], source = 0, destination = 2
Output: false
Explanation: All paths from the source node end at the destination node, 
but there are an infinite number of paths, such as 0-1-2, 0-1-1-2, 0-1-1-1-2, 0-1-1-1-1-2, and so on.
Example 5:



Input: n = 2, edges = [[0,1],[1,1]], source = 0, destination = 1
Output: false
Explanation: There is infinite self-loop at destination node.
Note:

The given graph may have self loops and parallel edges.
The number of nodes n in the graph is between 1 and 10000
The number of edges in the graph is between 0 and 10000
0 <= edges.length <= 10000
edges[i].length == 2
0 <= source <= n - 1
0 <= destination <= n - 1
 * 
 */

class Solution {
	/* Time complexity: O(E + V)
	 * Space complexity: O(E + V)
	 */
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
    	Map<Integer, List<Integer>> graph = new HashMap<>();
    	for(int []edge : edges) {
    		// This might not be in path of source to destination
    		/*if(edge[0] == edge[1])
    			return false;*/
    		if(!graph.containsKey(edge[0]))
    			graph.put(edge[0], new ArrayList<>());
    		graph.get(edge[0]).add(edge[1]);
    	}
    	Set<Integer> visited = new HashSet<>();
    	return dfs(graph, visited, source, destination);
    }
    
    // just DFS all path if any doesn't lead to destination return false
    private boolean dfs(Map<Integer, List<Integer>> graph, Set<Integer> visited, int src, int dest) {
    	if(!graph.containsKey(src))
    		return src == dest;
    	
    	for(int d : graph.get(src)) {
    		if(visited.contains(d))
    			return false;
    		visited.add(d);
    		if(!dfs(graph, visited, d, dest))
    			return false;
    		visited.remove(d);
    	}
    	return true;
    }
}


public class Main {
	public static void main(String[] args) {
		int n = 5;
		int[][] edges = {{0,1},{1,2},{2,3},{3,4}};
		int source = 1, destination = 3;
		System.out.println(new Solution().leadsToDestination(n, edges, source, destination));
	}
}