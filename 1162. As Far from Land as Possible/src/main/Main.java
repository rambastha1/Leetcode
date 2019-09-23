package main;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
	// BFS time 0(mn) space 0(mn)
    public int maxDistance(int[][] grid) {
    	int m = grid.length, n = grid[0].length;
    	Queue<int []> q = new LinkedList<int[]>();
    	boolean [][]visited = new boolean[m][n];
    	
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			if(grid[i][j] == 1) {
    				q.offer(new int[] {i,j});
    				visited[i][j] = true;
    			}
    		}
    	}
    	int res = -1;
    	int [][]dir = {{0,-1}, {-1,0}, {0,1}, {1,0}};
    	
    	while(!q.isEmpty()) {
    		int size = q.size();
    		for(int s = 0;s < size;s++) {
    			int []node = q.poll();
    			int x = node[0], y = node[1];
        		
    			for(int i = 0;i < 4;i++) {
        			int X = x + dir[i][0];
        			int Y = y + dir[i][1];
        			if(X >= 0 && X < m && Y >= 0&& Y < n && !visited[X][Y]) {
        				q.offer(new int[] {X,Y});
        				visited[X][Y] = true;
        			}
        		}
    		}
    		res++;
    	}
    	// res == 0 because if all 0 or all 1, res will increment once
    	return res==0?-1:res;
    }
}

public class Main {
	public static void main(String[] args) {
		//int [][]grid = {{1,0,1},{0,0,0},{1,0,1}};
		int [][]grid = {{1,0,0},{0,0,0},{0,0,0}};
		System.out.println(new Solution().maxDistance(grid));
	}
}
