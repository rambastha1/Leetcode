package main;
import java.util.Arrays;

/* There are two cases for the tree structure to be invalid.
1) A node having two parents;
   including corner case: e.g. [[4,2],[1,5],[5,2],[5,3],[2,4]]
2) A circle exists

If we can remove exactly 1 edge to achieve the tree structure, a single node can have at most two parents.
 * two steps 
 * 1. if there is no edge with 2 parent, check for cycle
 * 2. turn by turn skip those two edge and try to build tree -> if no cycle means this edge should be removed
 * https://leetcode.com/problems/redundant-connection-ii/discuss/108045/C%2B%2BJava-Union-Find-with-explanation-O(n)
 */

class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
    	int n = edges.length;
    	// node -> count of incoming edge
    	int []incoming = new int[n+1];
    	int nodewith2incomingedge = -1;
    	
    	for(int []edge : edges) {
    		int src = edge[0], dest = edge[1];
    		incoming[dest]++;
    		if(incoming[dest] == 2)
    			nodewith2incomingedge = dest;
    	}
    	// no double edge
    	if(nodewith2incomingedge == -1) {
    		int []res = findconnection(edges, -1);
    		return res == null?new int[0]:res;
    	} else {
    		for(int i = edges.length-1;i >= 0;i--) {
    			// skip both edges one by one and try to form tree
    			if(edges[i][1] == nodewith2incomingedge) {
    				int []res = findconnection(edges, i);
    				// means graph can be found without cycle
    				if(res == null)
    					return edges[i];
    			}
    		}
    	}
    	return new int[0];
    }
    
    private int []findconnection(int [][]edges, int skip) {
    	int[] parent = new int[edges.length+1];
    	for(int i = 1;i < parent.length;i++)
    		parent[i] = i;
    	
    	for(int i = 0;i < edges.length;i++) {
    		if(i == skip)
    			continue;
    		int p1 = find(parent, edges[i][0]), p2 = find(parent, edges[i][1]);
    		if(p1 == p2)
    			return edges[i];
    		parent[p2] = p1;
    	}
    	return null;
    }
    
    private int find(int[] parent, int i) {
    	if(parent[i] != i)
    		parent[i] = find(parent, parent[i]);
    	return parent[i];
    }
}

public class Main {
	public static void main(String[] args) {
		int[][] edges = {{1,2}, {2,3}, {3,4}, {4,1}, {1,5}};
		System.out.println(Arrays.toString(new Solution().findRedundantDirectedConnection(edges)));
	}
}
