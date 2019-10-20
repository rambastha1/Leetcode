package main;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
	
	int count = 0;
	public int minCosttoConnectAllNodes(int n, int edges[][], int newEdges[][]){
		this.count = n;
		int []parent = new int[n+1], rank = new int[n+1];
		for(int i = 1;i <= n;i++) {
			parent[i] = i;
			rank[i] = 1;
		}
		
		for(int i = 0;i < edges.length;i++)
			union(edges[i][0], edges[i][1], parent, rank);
		
		Arrays.sort(newEdges, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[2]-arg1[2];
			}
		});
		
		int ans = 0;
		for(int i = 0;i < newEdges.length;i++) {
			int x = find(newEdges[i][0], parent);
			int y = find(newEdges[i][1], parent);
			if(x != y) {
				union(newEdges[i][0], newEdges[i][1], parent, rank);
				ans += newEdges[i][2];
			}
			if(this.count == 1)
				return ans;
		}
		return -1;
	}
	
	private int find(int i, int []parent) {
		if(parent[i] != i)
			parent[i] = find(parent[i], parent);
		return parent[i];
	}
	
	private void union(int a, int b, int []parent, int []rank) {
		int x = find(a, parent);
		int y = find(b, parent);
		if(rank[x] < rank[y]) {
			parent[x] = y;
		} else if(rank[x] > rank[y]) {
			parent[y] = x;
		} else {
			parent[y] = x;
			rank[x]++;
		}
		this.count--;
	}
	
	private boolean isconnected(int a, int b, int []parent) {
		return find(a, parent) == find(b, parent);
	}
}


public class Main {
	public static void main(String[] args) {
		int n = 6;
		int [][]edges = {{1,4},{4,5},{2,3}}, newEdges = {{1,2,5},{1,3,10},{1,6,2},{5,6,5}};
		System.out.println(new Solution().minCosttoConnectAllNodes(n, edges, newEdges));
	}
}
