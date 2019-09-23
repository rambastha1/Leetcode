package main;

/*
 * Given a grid where each entry is only 0 or 1, find the number of corner rectangles.
 * A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. 
 * Note that only the corners need to have the value 1. Also, all four 1s used must be distinct.
 * 
 * Input: grid = 
 * [[1, 0, 0, 1, 0],
 *  [0, 0, 1, 0, 1],
 *  [0, 0, 0, 1, 0],
 *  [1, 0, 1, 0, 1]]
 * Output: 1
 * 
 * Explanation: There is only one corner rectangle, with corners grid[1][2], 
 * grid[1][4], grid[3][2], grid[3][4].
 */

class Solution {
	
	/*
	 * https://math.stackexchange.com/questions/1240264/how-many-rectangles-or-triangles
	 * In this method, the two row or length sides of rectangle are fixed
	 * suppose there are n columns for which grid[row1][c] is equal to grid[row2][c]
	 * Number of rectangle formed  = choosing any two lines from n columns as two length sides 
	 * are already fixed. 
	 * Number of rectangles = nC2 = (n)(n-1)/2  
	 */
	public int countCornerRectangles(int[][] grid) {
		int ans = 0;
		for(int row1 = 0;row1 < grid.length;row1++) {
			for(int row2 = row1+1;row2 < grid.length;row2++) {
				int count = 0;
				for(int col = 0;col < grid[0].length;col++) {
					if(grid[row1][col] == 1 && grid[row2][col] == 1) {
						count++;
					}
				}
				ans += (count * (count-1))/2;
			}
		}
		return ans;	
	}
	
    /* Brute force
     * Time 0(N^4) 
     * public int countCornerRectangles(int[][] grid) {
    	int count = 0;
    	for(int i = 0;i < grid.length;i++) {
    		for(int j = 0;j < grid[i].length;j++) {
    			int col = j;
    			if(grid[i][j] == 0)
    				continue;
    			int x,y;
    			for(y = j+1;y < grid[i].length;y++) {
    				if(grid[i][y] == 0)
    					continue;
    				for(x = i+1;x < grid.length;x++) {
    					if(grid[x][col] == 1 && grid[x][y] == 1) {
    						count++;
    					}
    				}
    			}
    				
    		}
    	}
    	return count;
    }*/
}

public class Main {
	public static void main(String[] args) {
		/*int [][]grid = {{1, 0, 0, 1, 0}, 
				        {0, 0, 1, 0, 1},
				        {0, 0, 0, 1, 0},
				        {1, 0, 1, 0, 1}};*/
		
		//int [][]grid = {{1,1,1}, {1,1,1}, {1,1,1}};
		int [][]grid = {{1,1,1}};
		System.out.println(new Solution().countCornerRectangles(grid));
	}
}