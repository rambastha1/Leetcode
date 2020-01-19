package main;

/* There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network where 
 * connections[i] = [a, b] represents a connection between computers a and b. Any computer can reach any other computer 
 * directly or indirectly through the network.

Given an initial computer network connections. You can extract certain cables between two directly connected computers, 
and place them between any pair of disconnected computers to make them directly connected. Return the minimum number of 
times you need to do this in order to make all the computers connected. If it's not possible, return -1. 

Example 1:

Input: n = 4, connections = [[0,1],[0,2],[1,2]]
Output: 1
Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
Example 2:

Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
Output: 2
Example 3:

Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
Output: -1
Explanation: There are not enough cables.
Example 4:

Input: n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
Output: 0

Constraints:

1 <= n <= 10^5
1 <= connections.length <= min(n*(n-1)/2, 10^5)
connections[i].length == 2
0 <= connections[i][0], connections[i][1] < n
connections[i][0] != connections[i][1]
There are no repeated connections.
No two computers are connected by more than one cable.
 * 
 */

class Solution {
	// 0(n)
    public int makeConnected(int n, int[][] connections) {
    	int m = connections.length;
    	// atleast n-1 connections must be there to connect all nodes
    	if(m+1 < n)
    		return -1;
    	
    	int []parent = new int[n], rank = new int[n];
    	for(int i = 0;i < n;i++) {
    		parent[i] = i;
    		rank[i] = 1;
    	}
    	
    	for(int []c : connections) {
    		union(c[0], c[1], parent, rank);
    	}
    	
    	// if parent[i] == i means discrete network -> need to connect these discrete networks
    	// require d-1 connections
    	int discrete = 0;
    	for(int i = 0;i < n;i++) {
    		int p = find(parent, i);
    		if(p == i)
    			discrete++;
    	}
    	return discrete-1;
    }
    
    private int find(int []parent, int i) {
    	if(parent[i] != i)
    		parent[i] = find(parent, parent[i]);
    	return parent[i];
    }
    
    private void union(int a, int b, int []parent, int []rank) {
    	int x = find(parent, a);
    	int y = find(parent, b);
    	if(rank[x] < rank[y]) {
    		parent[x] = y;
    	} else if(rank[x] > rank[y]) {
    		parent[y] = x;
    	} else {
    		parent[y] = x;
    		rank[x]++;
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 6;
		int [][]connections = {{0,1},{0,2},{0,3}, {1,2}, {1,3}};
		System.out.println(new Solution().makeConnected(n, connections));
	}
}
