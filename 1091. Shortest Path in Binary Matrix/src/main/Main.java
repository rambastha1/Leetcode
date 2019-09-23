package main;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
    	if(grid.length == 1)
    		return 0;
    	int m = grid.length, n = grid[0].length;
    	if(grid[0][0] == 1 || grid[m-1][n-1] == 1)
    		return -1;
    	int [][]dirs = {{0,-1}, {-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}};
    	Queue<int[]> q = new LinkedList<int[]>();
    	boolean [][]visited = new boolean [m][n];
    	int [][]dist = new int[m][n];
    	for(int []arr :dist)
    		Arrays.fill(arr, Integer.MAX_VALUE);
    	dist[0][0] = 1;
    	q.offer(new int[] {0,0});
    	
    	while(!q.isEmpty()) {
    		int size = q.size();
    		for(int s = 0;s < size;s++) {
    			int []node = q.poll();
        		visited[node[0]][node[1]] = true;
        		for(int i = 0;i < 8;i++) {
        			int x = node[0] + dirs[i][0];
        			int y = node[1] + dirs[i][1];
        			if(issafe(grid, m, n, x, y, visited) && dist[node[0]][node[1]] + 1 < dist[x][y]) {
        				dist[x][y] = dist[node[0]][node[1]] + 1;
        				q.offer(new int[] {x,y});
        			}
        		}
    		}
    	}
    	return dist[m-1][n-1]==Integer.MAX_VALUE?-1:dist[m-1][n-1];
    }
    
    boolean issafe(int [][]grid, int m, int n, int x, int y, boolean [][]visited) {
    	return x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0 && !visited[x][y];
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]grid = {{0,0,0},{1,1,0},{1,1,0}};
		//int [][]grid = {{0,1},{1,0}};
		System.out.println(new Solution().shortestPathBinaryMatrix(grid));
	}
}
