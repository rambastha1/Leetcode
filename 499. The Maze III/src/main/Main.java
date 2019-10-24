package main;

import java.util.PriorityQueue;
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
		
	public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length, n = maze[0].length;
        boolean[][] seen = new boolean[m][n];
        Queue<Ball> q = new PriorityQueue<>();
        q.offer(new Ball(ball[0], ball[1], 0, ""));
        
        while(!q.isEmpty()) {
            Ball cur = q.poll();
            if(cur.x == hole[0] && cur.y == hole[1])
                return cur.dir;
            seen[cur.x][cur.y] = true;
            
            for(int k = 0; k < 4; k++) {
                int nx = cur.x;
                int ny = cur.y;
                int nd = cur.dist;
                String ndir = cur.dir;
                
                while(nx+dirs[k][0] >= 0 && nx+dirs[k][0] < m && 
                      ny+dirs[k][1] >= 0 && ny+dirs[k][1] < n && 
                      maze[nx+dirs[k][0]][ny+dirs[k][1]] != 1) { 
                    nx += dirs[k][0];
                    ny += dirs[k][1];
                    nd++;
                    
                    if(nx == hole[0] && ny == hole[1]) break;
                } 
                
                if(!seen[nx][ny]) q.offer(new Ball(nx, ny, nd, ndir+dirct[k])); 
            }
        } 
        
        return "impossible";
    }
    
    final private char[] dirct = {'d', 'l', 'r', 'u'};
    final private int[][] dirs = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    
    class Ball implements Comparable<Ball> {
        int x;
        int y;
        int dist;
        String dir;
        
        public Ball(int x, int y, int dist, String dir) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.dir = dir;
        }
        
        public int compareTo(Ball other) {
            return dist != other.dist? dist-other.dist : dir.compareTo(other.dir);
        }
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
