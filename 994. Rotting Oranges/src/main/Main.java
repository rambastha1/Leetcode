package main;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int orangesRotting(int[][] grid) {
    	if(grid == null || grid.length == 0 || grid[0].length == 0)
    		return 0;
    	int m = grid.length, n = grid[0].length;
    	if(m == 1 && n == 1) {
    		return grid[0][0] == 1?-1:0;
    	}
    	
    	Queue<int[]> q = new LinkedList<>();
    	
    	int count = 0, target = 0;
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			if(grid[i][j] == 2) {
    				q.offer(new int[] {i,j});
    			} else if(grid[i][j] == 1)
    				target++;
    		}
    	}
    	
    	int ans = 0;
    	int [][]dirs = {{0,-1}, {-1,0}, {0,1}, {1,0}};
    	while(!q.isEmpty()) {
    		int size = q.size();
    		if(count == target)
    			return ans;
    		for(int i = 0;i < size;i++) {
    			int []node = q.poll();
    			for(int []dir : dirs) {
    				int x = node[0] + dir[0];
    				int y = node[1] + dir[1];
    				if(issafe(grid, x, y)) {
    					count++;
    					q.offer(new int[] {x,y});
    					grid[x][y] = 2;
    				}
    			}
    		}
    		ans++;
    	}
    	return -1;
    }
    
    private boolean issafe(int [][]grid, int x, int y) {
    	return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1;
    }
}

public class Main {
	public static void main(String[] args) {
		//int [][]grid = {{2,1,1},{1,1,0},{0,1,1}};
		int [][]grid = {{1},{2},{2}};
		System.out.println(new Solution().orangesRotting(grid));
	}
}