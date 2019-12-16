package main;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
	// Time and Space 0(m*n*k)
    public int shortestPath(int[][] grid, int k) {
    	int m = grid.length, n = grid[0].length;
    	int [][]dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    	Queue<int []> q =  new LinkedList<>();
    	int [][][]dist = new int[m][n][k+1];
    	for(int [][]a : dist) {
    		for(int []b : a) {
    			Arrays.fill(b, Integer.MAX_VALUE);
    		}
    	}
    	dist[0][0][k] = 0;
    	q.offer(new int[] {0,0,k});
    	
    	while(!q.isEmpty()) {
			int []node = q.poll();
			int x = node[0], y = node[1], rem = node[2];
			int currdist = dist[x][y][rem];
			if(x == m-1 && y == n-1)
				return currdist;
			
			// notice use of new rem
			for(int j = 0;j < 4;j++) {
				int X = x + dirs[j][0];
				int Y = y + dirs[j][1];
				int REM = rem;
				if(!issafe(m, n, X, Y)) 
					continue;
				if(grid[X][Y] == 1)
					REM--;
				// dijkstra
				if(REM >= 0 && currdist+1 < dist[X][Y][REM]) {
					dist[X][Y][REM] = currdist+1; 
					q.offer(new int[] {X,Y,REM});
				}
			}
    	}
    	return -1;
    }
    
    private boolean issafe(int m, int n, int x,int y) {
    	return x >= 0 && x < m && y >= 0 && y < n;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]grid = {{0,0,0}, {1,1,0}, {0,0,0}, {0,1,1}, {0,0,0}}; 
		int k = 1;
		System.out.println(new Solution().shortestPath(grid, k));
		
	}
}
