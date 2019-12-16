package main;

class Solution {
	// in same row or column doesn't need to be adjacent
	public int countServers(int[][] grid) {
	    int m = grid.length, n = grid[0].length;
	    int []row = new int[m], col = new int[n];
	    int servers = 0;
	    
	    for(int i = 0;i < m;i++) {
	    	for(int j = 0;j < n;j++) {
	    		if(grid[i][j] == 1) {
	    			row[i]++;
	    			col[j]++;
	    			servers++;
	    		}
	    	}
	    }
	    
	    for(int i = 0;i < m;i++) {
	    	for(int j = 0;j < n;j++) {
	    		if(grid[i][j] == 1) {
	    			if(row[i] == 1 && col[j] == 1)
	    				servers--;
	    		}
	    	}
	    }
	    return servers;
	}
}

public class Main {
	public static void main(String[] args) {
		int [][]grid = {{1,1,0,0}, {1,1,0,1}, {1,1,0,0}};
		System.out.println(new Solution().countServers(grid));
	}
}