package main;

class Solution {
    
	public int numIslands(char[][] grid) {
		if(grid.length == 0 || grid[0].length == 0)
			return 0;
		int count = 0;
		boolean [][]visited = new boolean[grid.length][grid[0].length];
		for(int i = 0;i < grid.length;i++) {
			for(int j = 0;j < grid[0].length;j++) {
				if(grid[i][j] == '1' && !visited[i][j]) {
					numIslandsUtil(grid, visited, i, j);
					count++;
				}
			}
		}
     	return count;
    }
	
	public void numIslandsUtil(char[][] grid, boolean [][]visited, int x, int y) {
		
		int []xa = {-1, -1, -1 ,0 , 0, 1, 1, 1};
		int []ya = {-1, 0, 1, -1, 1, -1, 0, 1};
		visited[x][y] = true;
		for(int i = 0;i < 8;i++) {
			if(issafe(grid.length-1, grid[0].length-1, x+xa[i], y+ya[i], visited))
				numIslandsUtil(grid, visited, x+xa[i], y+ya[i]);
		}
	}
    
    public boolean issafe(int X, int Y, int x, int y, boolean [][]visited) {
    	return (x >= 0 && x <= X && y >= 0 && y <= Y && !visited[x][y]);
    }
}

public class Main {

	public static void main(String[] args) {
		char [][] grid = {   {'1','1','1','1','0'},
							{'1','1','0','1','0'},
							{'1','1','0','0','0'},
							{'0','0','0','0','0'} };
		System.out.println(new Solution().numIslands(grid));
	}
}