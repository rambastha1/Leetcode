package main;

import java.util.LinkedList;
import java.util.Queue;

/*
 * You are given a m x n 2D grid initialized with these three possible values.
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent 
 * 
 * INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. 
 * If it is impossible to reach a gate, it should be filled with INF.
 * For example, given the 2D grid:
 * 
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 * 0    -1 INF INF
 *
 * After running your function, the 2D grid should be:
 * 3  -1   0   1
 * 2   2   1  -1
 * 1  -1   2  -1
 * 0  -1   3   4
 */

class Solution {        
    
	class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	//DFS Solution
	public void DFS_wallsAndGates(int[][] rooms) {
		if(rooms == null || rooms.length == 0)
			return;
		for(int i = 0;i < rooms.length;i++) {
			for(int j = 0;j < rooms[i].length;j++) {
				if(rooms[i][j] == 0)
					dfs(rooms, i, j, 0);
			}
		}
		print(rooms);
	}
	
	public void dfs(int [][]rooms, int x,int y, int distance) {
		if(x < 0 || x >= rooms.length || y < 0 || y >= rooms[x].length || rooms[x][y] == -1 ||
				rooms[x][y] < distance)
			return;
		rooms[x][y] = distance;
		dfs(rooms, x, y-1, distance+1);
		dfs(rooms, x-1, y, distance+1);
		dfs(rooms, x, y+1, distance+1);
		dfs(rooms, x+1, y, distance+1);
	}
	
	
	//BFS Solution
	public void wallsAndGates(int[][] rooms) {
		if(rooms == null || rooms.length == 0)
			return;
		Queue<Point> q = new LinkedList<>();
		for(int i = 0;i < rooms.length;i++) {
			for(int j = 0;j < rooms[i].length;j++) {
				if(rooms[i][j] != -1 && rooms[i][j] != Integer.MAX_VALUE)
					q.offer(new Point(i, j));
			}
		}
		
		int []x_path = {0,-1,0,1};
		int []y_path = {-1,0,1,0};
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			int distance = rooms[p.x][p.y];
			for(int i = 0;i < 4;i++) {
				int X = p.x + x_path[i];
				int Y = p.y + y_path[i];
				if(issafe(rooms, X, Y)) {
					if(rooms[X][Y] == Integer.MAX_VALUE) {
						rooms[X][Y] = distance + 1;
						q.offer(new Point(X, Y));
					}
				}
			}
		}
		print(rooms);
    }
	
	void print(int [][]rooms) {
		for(int i = 0;i < rooms.length;i++) {
			for(int j = 0;j < rooms[i].length;j++) {
				System.out.print(rooms[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	boolean issafe(int [][]rooms, int x, int y) {
		return(x >= 0 && x < rooms.length && y >= 0 && y < rooms[x].length && rooms[x][y] != -1);
	}
}

public class Main {
	public static void main(String[] args) {
		int [][]rooms = {{Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE},
				 		 {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1},
				 		 {Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1},
				 		 {0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE}};
		//new Solution().print(rooms);
		//new Solution().wallsAndGates(rooms);
		new Solution().DFS_wallsAndGates(rooms);
	}
}