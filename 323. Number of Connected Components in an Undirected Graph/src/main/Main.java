package main;

/*
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
 * write a function to find the number of connected components in an undirected graph.
 * 
 * Example 1:

     0        3

     |          |

     1 --- 2    4

 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
 * 
 * Example 2:

     0         4

     |           |

     1 --- 2 --- 3

 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
 * 
 * Note:
 * You can assume that no duplicate edges will appear in edges. 
 * Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */

class Solution {
	
	int find(int []parent, int i) {
		if(parent[i] != i)
			parent[i] = find(parent, parent[i]);
		return parent[i];
	}
	
	void union(int []parent, int []rank, int i, int j) {
		int x = find(parent, i);
		int y = find(parent, j);
		if(rank[x] < rank[y]) {
			parent[x] = y;
		} else if(rank[x] > rank[y]) {
			parent[y] = x;
		} else {
			parent[y] = x;
			rank[x]++;
		}
		count--;
	}
	
	int count;
    public int countComponents(int n, int[][] edges) {
    	int []parent = new int[n];
    	int []rank = new int[n];
    	count = n;
    	for(int i = 0;i < parent.length;i++) {
    		parent[i] = i;
    		rank[i] = 0;
    	}
    	
    	for(int i = 0;i < edges.length;i++) {
    		int src = find(parent, edges[i][0]);
    		int dest = find(parent, edges[i][1]);
    		if(src != dest)
    			union(parent, rank, src, dest);
    	}
    	//print(parent);    	
    	return count;
    }
    
    void print(int []parent) {
    	for(int i : parent)
    		System.out.print(i + " ");
    	System.out.println();
    }
}


public class Main {
	public static void main(String[] args) {
		int n = 5;
		int [][] edges = {{0, 1}, {1, 2}, {3, 4}};
		System.out.println(new Solution().countComponents(n, edges));
	}
}