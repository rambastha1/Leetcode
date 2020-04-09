package main;

import java.util.ArrayDeque;
import java.util.Deque;
// https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/discuss/524820/Java-2-different-solutions-Clean-code
// peter comment
class Solution {
    public int minCost(int[][] grid) {
    	int m = grid.length, n = grid[0].length;
    	Deque<int[]> deque = new ArrayDeque<>();
    	// row, col, cost
    	deque.offer(new int[] {0,0,0});
    	boolean [][]visited = new boolean[m][n];
    	
    	int [][]dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    	// front of deque has low cost
    	// thus if no need to change direction, add at front else at last(1+cost)
    	while(!deque.isEmpty()) {
    		int []node = deque.poll();
    		int x = node[0], y = node[1], cost = node[2];
    		visited[x][y] = true;
    		if(x == m-1 && y == n-1)
    			return cost;
    		for(int i = 0;i < 4;i++) {
    			int X = x + dirs[i][0], Y = y + dirs[i][1];
    			if(X < 0 || X >= m || Y < 0 || Y >= n || visited[X][Y])
    				continue;
    			if(i == grid[x][y] - 1)
    				deque.offerFirst(new int[] {X, Y, cost});
    			else
    				deque.offerLast(new int[] {X, Y, cost + 1});
    		}
    	}
    	return -1;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]grid = {{2,2,2}, {2,2,2}};
		System.out.println(new Solution().minCost(grid));
	}
}
