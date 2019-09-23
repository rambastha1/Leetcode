package main;

import java.util.LinkedList;
import java.util.Queue;

/* https://leetcode.com/articles/the-maze/
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by 
 * rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 * 
 * Given the ball's start position, the destination and the maze, determine whether the ball could 
 * stop at the destination.

 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. 
 * You may assume that the borders of the maze are all walls. The start and destination 
 * coordinates are represented by row and column indexes.

 

 * Example 1:

 * Input 1: a maze represented by a 2D array

	0 0 1 0 0
	0 0 0 0 0
	0 0 0 1 0
	1 1 0 1 1
	0 0 0 0 0

 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)

 * Output: true

 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 */

class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
    	int m = maze.length, n = maze[0].length;
    	boolean [][]visited = new boolean[m][n];
    	Queue<int []> q = new LinkedList<>();
    	q.offer(start);
    	int [][]dirs = {{0,-1}, {-1,0}, {0,1}, {1,0}};
    	while(!q.isEmpty()) {
    		int []block = q.poll();
    		if(block[0] == destination[0] && block[1] == destination[1])
    			return true;
    		for(int i = 0;i < dirs.length;i++) {
    			int x = block[0] + dirs[i][0];
    			int y = block[1] + dirs[i][1];
    			while(x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
    				x += dirs[i][0];
    				y += dirs[i][1];
    			}    				
    			if(!visited[x-dirs[i][0]][y-dirs[i][1]]) {
    				visited[x-dirs[i][0]][y-dirs[i][1]] = true;
    				q.offer(new int[] {x-dirs[i][0], y-dirs[i][1]});
    			}
    		}
    	}    	
    	return false;
    }
}


public class Main {
	public static void main(String[] args) {
		int [][]maze = { {0, 0, 1, 0, 0},
						 {0, 0, 0, 0, 0},
						 {0, 0, 0, 1, 0},
						 {1, 1, 0, 1, 1},
						 {0, 0, 0, 0, 0}};
		int []start = {0,4};
		//int []destination = {3,2};
		int []destination = {4,4};
		System.out.println(new Solution().hasPath(maze, start, destination));
	}
}