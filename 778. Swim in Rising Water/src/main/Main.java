package main;

import java.util.PriorityQueue;

// Brute Force perform bfs and from every node perform dfs to check whether it can reach (m-1,n-1)
/* better solution
 * Time 0(M*N*lgN) -> lgN for popping minimum value
 */
class Solution {
    public int swimInWater(int[][] grid) {
    	int m = grid.length, n = grid[0].length;
    	// x, y, grid[x][y] i.e time
    	PriorityQueue<int []> pq = new PriorityQueue<>((a,b) -> (a[2] - b[2]));
    	int [][]dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    	boolean [][]visited = new boolean[m][n];
    	
    	pq.offer(new int[] {0,0,grid[0][0]});
    	/* it is newtime that does the trick
    	 * newtime is max time of adjacent grids, thus it automatically finds the minimum time to reach this new grid
    	 * if time of (X,Y) is smaller than (x,y) -> then time won't increase else it will
    	 * kinda dijkstra
    	 */
    	while(!pq.isEmpty()) {
    		int []node = pq.poll();
    		int x = node[0], y = node[1], time =  node[2];
    		visited[x][y] = true;
    		for(int i = 0;i < 4;i++) {
    			int X = x + dirs[i][0];
    			int Y = y + dirs[i][1];
    			if(!issafe(grid, X, Y, visited))
    				continue;
    			int newtime = Math.max(time, grid[X][Y]);
    			if(X == m-1 && Y == n-1)
    				return newtime;
    			pq.offer(new int[] {X,Y, newtime});
    		}
    	}
    	return -1;
    }
    
    private boolean issafe(int [][]grid, int x, int y, boolean [][]visited) {
    	return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && !visited[x][y];
    }
}

public class Main {
	public static void main(String[] args) {
		//int [][]grid = {{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
		int [][]grid = {{3,2}, {0,1}};
		System.out.println(new Solution().swimInWater(grid));
	}
}
