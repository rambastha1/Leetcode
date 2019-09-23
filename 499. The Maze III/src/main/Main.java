package main;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/* There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by 
 * rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. 
 * When the ball stops, it could choose the next direction. There is also a hole in this maze. 
 * The ball will drop into the hole if it rolls on to the hole.
 * 
 * Given the ball position, the hole position and the maze, your job is to find out how the ball could 
 * drop into the hole by moving shortest distance in the maze. The distance is defined by the number of 
 * empty spaces the ball go through from the start position (exclude) to the hole (include). Output the 
 * moving directions by using 'u', 'd', 'l' and 'r'. Since there may have several different shortest ways, 
 * 
 * you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. 
 * You may assume that the borders of the maze are all walls. The ball and hole coordinates are 
 * represented by row and column indexes.
 * Example 1
 * Input 1: a maze represented by a 2D array
 * 
 * 0 0 0 0 0
 * 1 1 0 0 1
 * 0 0 0 0 0
 * 0 1 0 0 1
 * 0 1 0 0 0
 * 
 * Input 2: ball coordinate (rowBall, colBall) = (4, 3)
 * Input 3: hole coordinate (rowHole, colHole) = (0, 1)
 * 
 * Output: "lul"
 * Explanation: There are two shortest ways for the ball to drop into the hole.
 * The first way is left -> up -> left, represented by "lul".
 * The second way is up -> left, represented by 'ul'.
 * Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. 
 * So the output is "lul".
 * 
 * Example 2
 * Input 1: a maze represented by a 2D array
 * 
 * 0 0 0 0 0
 * 1 1 0 0 1
 * 0 0 0 0 0
 * 0 1 0 0 1
 * 0 1 0 0 0
 * 
 * Input 2: ball coordinate (rowBall, colBall) = (4, 3)
 * Input 3: hole coordinate (rowHole, colHole) = (3, 0)
 * Output: "impossible"
 * Explanation: The ball cannot reach the hole.
 * 
 * Note:
 * There are only one ball and one hole in the maze.
 * The ball and hole will only exist in the empty space, and they will not at the same position initially.
 * The given maze doesn't contain border (like the red rectangle in the example pictures), 
 * but you should assume the border of the maze are all walls.
 * The maze contains at least 2 empty spaces, and the length and width of the maze won't exceed 30.
 * 
 */

class Solution {
		
	class Point {
		int start, end, dist;
		String dir;
		public Point(int start, int end, int dist, String dir) {
			this.start = start;
			this.end = end;
			this.dist = dist;
			this.dir = dir;
		}
	}
	
	public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
		
		int [][]dirs = {{0,-1}, {-1,0}, {0,1}, {1,0}};
		String []dir = {"l", "u", "r", "d"};
		
		Point h = new Point(hole[0], hole[1], Integer.MAX_VALUE,"z");
		int m = maze.length, n = maze[0].length;
		
		int [][]dist = new int[m][n];
		for(int []row : dist)
			Arrays.fill(row, Integer.MAX_VALUE);
		dist[ball[0]][ball[1]] = 0;
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(ball[0], ball[1], 0, ""));
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i = 0;i < 4;i++) {
				int x = p.start + dirs[i][0];
				int y = p.end + dirs[i][1];
				int count = 0;
				String str = p.dir + dir[i];
				// Second comparison so that ball stops at hole
				while(issafe(maze, m, n, x, y) && (x!= hole[0] || y != hole[1])) {
					x += dirs[i][0];
					y += dirs[i][1];
					count++;
				}
				
				if(x == hole[0] && y == hole[1]) {
					if(p.dist+count < h.dist) {
						h.dist = p.dist + count;
						h.dir = str;
					} else if(p.dist+count == h.dist) {
						if(str.compareTo(h.dir) < 0)
							h.dir = str;
					}
				}
				
				if(issafe(maze, m, n, x-dirs[i][0], y-dirs[i][1]) && dist[p.start][p.end] + count < dist[x-dirs[i][0]][y-dirs[i][1]]) {
					dist[x-dirs[i][0]][y-dirs[i][1]] = dist[p.start][p.end] + count; 
					q.offer(new Point(x-dirs[i][0], y-dirs[i][1], dist[x-dirs[i][0]][y-dirs[i][1]], str));
				}
			}
		}
		return h.dist == Integer.MAX_VALUE?"impossible":h.dir;
	}
	
	boolean issafe(int [][]maze, int m, int n, int x, int y) {
		return x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0;
	}
}

public class Main {
	public static void main(String[] args) {
		int [][]maze = {{0, 0, 0, 0, 0}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 1, 0, 0, 1}, {0, 1, 0, 0, 0}};
		int []ball = {4,3};
		int []hole = {0,1};
		System.out.println(new Solution().findShortestWay(maze, ball, hole));
	}	
}
