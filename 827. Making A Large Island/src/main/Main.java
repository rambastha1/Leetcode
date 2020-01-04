package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {
	// use DFS to count islands
	// 0(N^2) 
	public int largestIsland(int[][] grid) {
        int max = 0, m = grid.length, n = grid[0].length;
        boolean hasZero = false; //To check if there is any zero in the grid
        for(int i = 0; i < grid.length; i++){
            for(int j = 0;j<grid[0].length;j++){
                if(grid[i][j] == 0){
                    grid[i][j] = 1;
                    max = Math.max(max,dfs(i,j,grid,new boolean[m][n]));   
                    if(max == m*n) return max;
                    grid[i][j] = 0;
                    hasZero = true;
                }
            }
        }
        return hasZero?max:m*n;
    }
    private int dfs(int i, int j, int[][] grid,boolean[][] visited){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0||visited[i][j]) return 0;
        visited[i][j] = true;
        int result = 1+dfs(i-1,j,grid,visited)+dfs(i+1,j,grid,visited)+dfs(i,j+1,grid,visited)+dfs(i,j-1,grid,visited);
        return result;
    }
	
    // Not working code passing 29/63
	class State {
		int x, y, k, island;
		int [][]grid;
		Set<Integer> visited;
		public State(int x, int y, int [][]grid, Set<Integer> visited, int island, int k) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.island = island;
			this.grid = new int[grid.length][grid[0].length];
			for(int i = 0;i < grid.length;i++) {
				for(int j = 0;j < grid[0].length;j++) {
					this.grid[i][j] = grid[i][j];
				}
			}
			this.visited = new HashSet<>();
			this.visited.add(x*grid.length+y);
			this.visited.addAll(visited);
		}
	}
	
    public int largestIsland1(int[][] grid) {
    	int m = grid.length, n = grid[0].length;
    	int [][]dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    	Queue<State> q = new LinkedList<>();
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			q.offer(new State(i, j, grid, new HashSet<>(), 1, grid[i][j]==1?1:0));
    		}
    	}
    	
    	int ans = 0;
    	while(!q.isEmpty()) {
    		State s = q.poll();
    		ans = Math.max(ans, s.island);
    		int x = s.x, y = s.y;
    		for(int i = 0;i < 4;i++) {
    			int X = x + dirs[i][0];
    			int Y = y + dirs[i][1];
    			if(!issafe(s.grid, X, Y, s.visited) || (s.k <= 0 && s.grid[X][Y] == 0))
    				continue;
    			if(s.grid[X][Y] == 0)
    				s.k--;
    			s.island++;
    			s.visited.add(X*s.grid.length + Y);
    			q.offer(new State(X, Y, s.grid, s.visited, s.island, s.k));
    		}
    	}
    	return ans;
    }
    
    private boolean issafe(int [][]grid, int x, int y, Set<Integer> visited) {
    	return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && !visited.contains(x*grid.length+y);
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]grid = {{1,0}, {0,1}};
		System.out.println(new Solution().largestIsland(grid));
	}
}
