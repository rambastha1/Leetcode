package main;

/* Given n nodes labeled from 0 to n – 1 and a list of undirected edges 
 * (each edge is a pair of nodes), write a function to check whether these edges make up 
 * a valid tree.
 * For example:
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 * Hint:
 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? 
 * Is this case a valid tree?
 * According to the definition of tree on Wikipedia: “a tree is an undirected graph 
 * in which any two vertices are connected by exactly one path. In other words, any 
 * connected graph without simple cycles is a tree.”
 */

class Solution {
	public boolean validTree(int n, int[][] edges) {
		int []parent = new int[n];
		int []rank = new int[n];
		for(int i = 0;i < n;i++) {
			parent[i] =i;
			rank[i] = 1;
		}
		
		for(int []edge : edges) {
			int i = find(edge[0], parent);
			int j = find(edge[1], parent);
			if(i == j)
				return false;
			union(i, j, parent, rank);
		}
		return true;
	}
	
	int find(int i, int []parent) {
		if(parent[i] != i)
			parent[i] = find(parent[i], parent);
		return parent[i];
	}
	
	void union(int i, int j, int []parent, int []rank) {
		int x = find(i, parent);
		int y = find(j, parent);
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
		/*int n = 5;
		int [][]edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};*/
		
		int n = 5;
		int [][]edges = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
		System.out.println(new Solution().validTree(n, edges));
	}
}
