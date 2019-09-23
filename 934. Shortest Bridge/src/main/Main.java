package main;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
	
	class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	int [][]dirs = {{0,-1}, {-1,0}, {0,1}, {1,0}};
	
	// Find one graph and expand using BFS to find second
    public int shortestBridge(int[][] A) {
    	if(A == null || A.length == 0)
    		return 0;
    	boolean [][]visited = new boolean[A.length][A[0].length];
    	Queue<Point> q = new LinkedList<>();
    	boolean found = false;
    	
    	for(int i = 0;i < A.length;i++) {
    		if(found) break;
    		for(int j = 0;j < A[0].length;j++) {
    			if(A[i][j] == 1 && !visited[i][j]) {
    				dfs(A, i, j, q, visited);
    				found = true;
    				break;
    			}
    		}
    	}
    	
    	// BFS to expand graph
    	int ans = 0;
    	while(!q.isEmpty()) {
    		int size = q.size();
    		for(int i = 0;i < size;i++) {
    			Point p = q.poll();
    			for(int j = 0;j < 4;j++) {
    				int x = p.x + dirs[j][0];
    				int y = p.y + dirs[j][1];
    				if(x>=0 && x<A.length && y>=0 && y<A[0].length && !visited[x][y]) {
    					if(A[x][y] == 1)
    						return ans;
    					q.offer(new Point(x, y));
        				visited[x][y] = true;
    				}    				
    			}
    		}
    		ans++;
    	}
    	return ans;
    }
    
    void dfs(int [][]A, int x, int y, Queue<Point> q, boolean[][] visited) {
    	if(!issafe(A, x, y)) return;
    	
    	visited[x][y] = true;
    	q.offer(new Point(x, y));    	
    	
    	for(int i = 0;i < 4;i ++) {
    		int X = x+dirs[i][0];
    		int Y = y+dirs[i][1];
    		if(issafe(A, X, Y) && !visited[X][Y])
    			dfs(A, X, Y, q, visited);
    	}
    }
    
    boolean issafe(int [][]A, int x, int y) {
    	return x >= 0 && x < A.length && y >= 0 && y < A[0].length && A[x][y] == 1;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]A = {{0,1},{1,0}};
		//int [][]A = {{0,1,0},{0,0,0},{0,0,1}};
		//int [][]A = {{1,1,1,1,1},{1,0,0,0,1},{1,0,1,0,1},{1,0,0,0,1},{1,1,1,1,1}};
		System.out.println(new Solution().shortestBridge(A));
	}
}
