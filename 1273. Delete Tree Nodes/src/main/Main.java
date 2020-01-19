package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/delete-tree-nodes/discuss/440914/Java-DFS-Time-O(N)-Clean-code
/* A tree rooted at node 0 is given as follows:

The number of nodes is nodes;
The value of the i-th node is value[i];
The parent of the i-th node is parent[i].
Remove every subtree whose sum of values of nodes is zero.

After doing so, return the number of nodes remaining in the tree.

Example 1:


Input: nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
Output: 2
Constraints:

1 <= nodes <= 10^4
-10^5 <= value[i] <= 10^5
parent.length == nodes
parent[0] == -1 which indicates that 0 is the root.
 * 
 */
class Solution {
	// Time and Space 0(N)
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
    	List<List<Integer>> graph = new ArrayList<>();
    	
    	for(int i = 0;i < parent.length;i++) {
    		graph.add(new ArrayList<>());
    	}
    	
    	for(int i = 0;i < parent.length;i++) {
    		if(parent[i] == -1)
    			continue;
    		graph.get(parent[i]).add(i);
    	}
    	return dfs(graph, value, 0)[1];
    }
    
    private int []dfs(List<List<Integer>> graph, int[] value, int root) {
    	// nodes -> supposed to be counted
    	int sum = value[root], nodes = 1;
    	for(int dest : graph.get(root)) {
    		int []temp = dfs(graph, value, dest);
    		sum += temp[0];
    		nodes += temp[1];
    	}
    	// if sum of this subtree rooted at this node is 0, there is point in counting nodes of its children
    	if(sum == 0)
    		nodes = 0;
    	return new int[] {sum, nodes};
    }
    
    /* https://leetcode.com/problems/delete-tree-nodes/discuss/440792/JavaO(n)
     * In the first for loop, we are adding up the values of the children to its parent. 
     * So after that for loop, if the value of a node is 0, then that node value along with its children are adding up to 0, 
     * and can be removed.

Now during the second loop, we are adding the nodes with 0 value to the set. 
So Hash set should contains all the nodes which needs to be deleted.(which includes a node with 0 value and all its children) 
so we first add nodes with 0 value and also those nodes whose children is already added in the set. In order to identify those nodes, 
the values of those nodes are set as 0.
     * 
     */
    // Similar logic but without constructing graph
    // Time and Space 0(N)
    public int deleteTreeNodes1(int nodes, int[] parent, int[] value) {
    	int n = parent.length;
    	// add value of children to its parent
    	for(int i = n-1;i > 0;i--) {
    		value[parent[i]] += value[i];
    	}
    	
    	// if any parent has sum 0, don't count that and its children
    	Set<Integer> set = new HashSet<>();
    	// count of nodes where sum = 0
    	int count = 0;
    	for(int i = 0;i < nodes;i++) {
    		// parent already in set, no need to count this node, parent is added only if sum of parent and its children = 0
    		if(set.contains(parent[i]))
    			value[i] = 0;
    		
    		// parent valiue = 0, add to set
    		if(value[i] == 0) {
    			set.add(i);
    			count++;
    		}
    	}
    	return nodes - count;
    }
}

public class Main {
	public static void main(String[] args) {
		int nodes = 7;
		int []parent = {-1,0,0,1,2,2,2}, value = {1,-2,4,0,-2,-1,-1};
		System.out.println(new Solution().deleteTreeNodes(nodes, parent, value));
		System.out.println(new Solution().deleteTreeNodes1(nodes, parent, value));
	}
}
