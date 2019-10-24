package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. 
 * You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
Example:

Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 7 

Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
             the point (1,2) is an ideal empty land to build a house, as the total 
             travel distance of 3+3+1=7 is minimal. So return 7.
Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 * 
 */

// https://leetcode.com/problems/shortest-distance-from-all-buildings/discuss/76891/Java-solution-with-explanation-and-time-complexity-analysis
class Solution {
    public int shortestDistance(int[][] grid) {
    	if(grid.length == 0 || grid[0].length == 0 || (grid.length == 1 && grid[0].length == 1))
    		return -1;
    	
    	int m = grid.length, n = grid[0].length, buildings = 0;
    	int [][]dirs = {{0,-1}, {-1,0}, {0,1}, {1,0}};
    	// shortest distance from all buildings 
    	int [][]dist = new int[m][n];
    	
    	// how many buildings can be reached form this node
    	// this has to match total buildings else no use of this node
    	int [][]reach = new int[m][n];
    	
    	/* For each building do bfs and update distances
    	 * in the end take minimum of distances if that node is reachable from all buildings
    	 * done to check connectivity
    	 * 
    	 * another way would be to find to traverse all 0's and find its distances from all buildings
    	 * flaw -> what if one of building is not reachable cannot use that node
    	 */
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			if(grid[i][j] == 1) {
    				// total number of buildings
    				buildings++;
    				Queue<int []> q = new LinkedList<int[]>();
    				q.offer(new int[] {i,j});
    				boolean [][]visited = new boolean[m][n];
    				
    				// idea is to consider only those nodes which are connected to all the buildings thus reach array
    		    	// take minimum from such nodes
    		    	
    		    	int level = 1;
    		    	while(!q.isEmpty()) {
    		    		int size = q.size();
    		    		for(int s = 0;s < size;s++) {
    		    			int []node = q.poll();
    		    			for(int d = 0;d < 4;d++) {
    		    				int x = node[0] + dirs[d][0];
    		    				int y = node[1] + dirs[d][1];
    		    				if(issafe(grid, m, n, x, y, visited)) {
    		    					/* The shortest distance from [nextRow][nextCol] to thic building is level
    		    					 * so add all levels gives shortest distance from all reachable buildings
    		    					 * farther the node is from city higher level will be 
    		    					 * all nodes will be in queue once it reaches that all levels will be added
    		    					 * these nodes will be from every city 
    		    					 */
    		    					dist[x][y] += level;
    		    					reach[x][y]++;
    		    					visited[x][y] = true;
    		    					q.offer(new int[] {x, y});
    		    				}
    		    			}
    		    		}
    		    		level++;
    		    	}
    			}
    		}
    	}
    	
    	int ans = Integer.MAX_VALUE;
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			if(grid[i][j] == 0 && reach[i][j] == buildings) {
    				ans = Math.min(ans, dist[i][j]);
    			}
    		}
    	}
        return ans == Integer.MAX_VALUE?-1:ans;
    }
    
    private boolean issafe(int [][]grid, int m, int n, int x, int y, boolean [][]visited) {
    	return x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0 && !visited[x][y];
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]grid = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
		System.out.println(new Solution().shortestDistance(grid));
	}
}