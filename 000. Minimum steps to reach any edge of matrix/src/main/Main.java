package main;

class Solution {
	int minsteps(int [][]matrix) {
		
		int [][]dp = new int[matrix.length][matrix[0].length];
		for(int i = 0;i < matrix.length;i++) {
			for(int j = 0;j < matrix[0].length;j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		int i = 0, j = 0;
		outerloop:
		for(i = 0;i < matrix.length;i++) {
			for(j = 0;j < matrix[0].length;j++) {
				if(matrix[i][j] == 2) {
					dp[i][j] = 0;
					break outerloop;
				}
			}
		}
		int steps = 0;
		return minstepsutil(matrix, dp, i, j, steps);
	}
	
	int minstepsutil(int [][]matrix, int [][]dp, int x, int y, int steps) {
		
		if(!issafe(matrix, x, y))
			return -1;
		
		if(x == 0 || x == dp.length-1 || y == 0 || y == dp[0].length-1)
			return steps+1;
		
		if(matrix[x][y] == 0 || matrix[x][y] == 2) {
			if(matrix[x][y] == 0)
				steps++;
			minstepsutil(matrix, dp, x, y+1, steps);
			minstepsutil(matrix, dp, x+1, y, steps);
			minstepsutil(matrix, dp, x, y-1, steps);
			minstepsutil(matrix, dp, x-1, y, steps);
		}
		return -1;
	}
	
	boolean issafe(int [][]matrix, int x, int y) {
		return (x >= 0 && x <= matrix.length-1 && y >= 0 && y <= matrix[0].length);
	}
}

public class Main {
	public static void main(String[] args) {
		int [][]matrix = { {1, 1, 1, 0, 1},
						   {1, 0, 2, 0, 1}, 
						   {0, 0, 1, 0, 1},
						   {1, 0, 1, 1, 0} };
		System.out.println(new Solution().minsteps(matrix));
	}
}