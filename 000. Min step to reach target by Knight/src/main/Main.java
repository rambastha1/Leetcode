package main;

import java.util.LinkedList;
import java.util.Queue;

/*
 * This code gives minimum number of moves to reach the target because BFS always give shortest
 * path. Moreover, since it is undirected graph, any move that reaches destination first is 
 * shortest path. This will be different for directed graph.
 */


class Solution {
	
	class Cell {
		int x,y, dist;
		public Cell(int x,int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
	boolean isvalid(int x, int y, int N) {
		return (x>=0 && x<=N && y>=0 && y<=N);
	}
	
	int minstep(int []start, int []end, int N) {
		
		int dx[] = {-2, -1, 1, 2, -2, -1, 1, 2};  
	    int dy[] = {-1, -2, -2, -1, 1, 2, 2, 1};
	    
	    Queue<Cell> q = new LinkedList<>();
	    q.offer(new Cell(start[0], start[1], 0));
	    boolean [][]visited = new boolean[N+1][N+1];
	    visited[start[0]][start[1]] = true;
	    
	    while(!q.isEmpty()) {
	    	Cell c = q.poll();
	    	if(c.x == end[0] && c.y == end[1])
	    		return c.dist;
	    	
	    	for(int i = 0;i < 8;i++) {
	    		int x = c.x + dx[i];
	    		int y = c.y + dy[i];
	    		if(isvalid(x, y, N) && !visited[x][y]) {
	    			visited[x][y] = true;
	    			q.offer(new Cell(x, y, c.dist+1));
	    		}
	    	}
	    }	    
		return Integer.MAX_VALUE;
	}
}

public class Main {
	public static void main(String[] args) {
		int []start = {1,1};
		int []end = {30,30};
		int N = 30;
		System.out.println(new Solution().minstep(start, end, N));
	}
}