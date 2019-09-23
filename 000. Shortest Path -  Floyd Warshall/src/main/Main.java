package main;

// Directed Graph
// https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/

class Solution {
	// Time 0(V^3)
	void floyd(int [][]g) {
		int V = g.length;
		int [][]dist = new int[V][V];
		
		for(int i = 0;i < V;i++) {
			for(int j = 0;j < V;j++) {
				dist[i][j] = g[i][j];
			}
		}
		
		for(int k = 0;k < V;k++) {
			for(int i = 0;i < V;i++) {
				for(int j = 0;j < V;j++) {
					if(dist[i][k] + dist[k][j] < dist[i][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
		
		for(int i = 0;i < V;i++) {
			for(int j = 0;j < V;j++) {
				System.out.print(dist[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
}

public class Main {
	public static void main(String[] args) {
		int [][]g = {{0,5,99999,10}, {99999,0,3,99999},	{99999,99999,0,1}, {99999,99999,99999,0}};
		new Solution().floyd(g);
	}
}
