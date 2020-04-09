package main;
// https://www.geeksforgeeks.org/given-matrix-o-x-find-largest-subsquare-surrounded-x/
class Solution {
    public int largest1BorderedSquare(int[][] grid) {
    	int m = grid.length, n = grid[0].length;
    	int [][]left = new int[m][n];
    	int [][]top = new int[m][n];
    	int max = 0;
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			if(grid[i][j] == 0) {
    				left[i][j] = 0;
    				top[i][j] = 0;
    			} else {
    				left[i][j] = (j == 0)? 1 : 1 + left[i][j-1];
    				top[i][j] = (i == 0)?1 : 1 + top[i-1][j];
    			}
    		}
    	}
    	
    	for(int i = m-1;i >= 0;i--) {
    		for(int j = n-1;j >= 0;j--) {
    			int small = Math.min(left[i][j], top[i][j]);
    			// right most shows has sufficient 1s
    			// we need to check left vertical and top left (for sides of square)
    			while(small > max) {
    				if(left[i - small + 1][j] >= small && top[i][j - small + 1] >= small)
    					max = small;
    				small--;
    			}
    		}
    	}
    	return max*max;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]grid = {{1,1,1}, {1,0,1}, {1,1,1}};
		System.out.println(new Solution().largest1BorderedSquare(grid));
	}
}
