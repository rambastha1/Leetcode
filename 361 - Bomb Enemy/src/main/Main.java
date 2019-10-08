package main;

/* Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), 
 * return the maximum enemies you can kill using one bomb.
 * The bomb kills all the enemies in the same row and column from the planted point until it hits 
 * the wall since the wall is too strong to be destroyed.
 * Note that you can only put the bomb at an empty cell.
 * Example:
 * For the given grid
 * 
 * 0 E 0 0
 * E 0 W E
 * 0 E 0 0
 * 
 * return 3. (Placing a bomb at (1,1) kills 3 enemies)
 * 
 */

class Solution {
	
	public int maxKilledEnemies(char[][] grid) {
		if(grid.length == 0 || grid[0].length == 0)
			return 0;
		int m = grid.length, n = m!=0?grid[0].length:0;
		int row = 0;
		int []col = new int[n];
		
		int ans = 0;
		/*
		 * Traverse matrix storing number of E in row and col[j]
		 * when a wall is encountered 
		 * if wall is on left, row need to be set to 0 as there won't be any effect on left side if bomb
		 * is set here. start counting E from here
		 * 
		 * similarly when wall is above
		 * encounter empty space try to plant bomb here and get maximum
		 */
		for(int i = 0;i < m;i++) {
			for(int j = 0;j < n;j++) {
				if(j == 0 || grid[i][j-1] == 'W') {
					row = 0;
					for(int k = j;k < n && grid[i][k] != 'W';k++)
						row += grid[i][k] =='E'?1:0;
				}
				
				if(i == 0 || grid[i-1][j] == 'W') {
					col[j] = 0;
					for(int k = i;k < m && grid[k][j] != 'W';k++)
						col[j] += grid[k][j] == 'E'?1:0;
				}
				if(grid[i][j] == '0')
					ans = Math.max(ans, row + col[j]);
			}
		}
		return ans;
	}
}

public class Main {
	public static void main(String[] args) {
		char [][]grid = {{'0', 'E', '0', '0'}, {'E', '0', 'W', 'E'}, {'0', 'E', '0', '0'}};
		System.out.println(new Solution().maxKilledEnemies(grid));
	}
}
