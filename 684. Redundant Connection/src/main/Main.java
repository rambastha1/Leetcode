package main;

class Solution {
	
	int find(int []parent, int i) {
		if(parent[i] != i)
			parent[i] = find(parent, parent[i]);
		return parent[i];
	}
	
	void union(int []parent, int []rank, int i, int j) {
		int x = find(parent, i);
		int y = find(parent, j);
		if(rank[x] < rank[y])
			parent[x] = y;
		else if(rank[x] > rank[y])
			parent[y] = x;
		else {
			parent[y] = x;
			rank[x]++;
		}
	}
	
	
    public int[] findRedundantConnection(int[][] edges) {
    	
    	int []parent = new int[edges.length+1];
    	int []rank = new int[edges.length+1];
    	
    	for(int i = 0;i < parent.length;i++) {
    		parent[i] = i;
    		rank[i] = 0;
    	}
    	
    	int []res = new int[2];
    	for(int i = 0;i < edges.length;i++) {
    		int src = find(parent, edges[i][0]);
    		int dest = find(parent, edges[i][1]);
    		if(src == dest) {
    			res[0] = edges[i][0];
    			res[1] = edges[i][1];
    		} else {
    			union(parent, rank, src, dest);
    		}
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]edges = {{1,2}, {1,3}, {2,3}};
		int []res = new Solution().findRedundantConnection(edges);
		System.out.println(res[0] + " " + res[1]);
	}
}