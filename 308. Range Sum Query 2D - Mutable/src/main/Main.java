package main;

/*
 * Given a 2D matrix  matrix , find the sum of the elements inside the
 * rectangle defined by its upper left corner ( row 1,  col 1) and lower right corner ( row 2,  col 2).
 * Given matrix = 
 * [ [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5] ] 
 * sumRegion(2, 1, 4, 3) -> 8
 * Update(3, 2, 2)
 * sumRegion(2, 1, 4, 3) -> 10
 * 
 * Done using 2D BIT
 * https://www.geeksforgeeks.org/two-dimensional-binary-indexed-tree-or-fenwick-tree/
 * -1 is done to include that value in sum.
 * 
 *  	 ___________ (x2, y2)
 *  	|			|
 *  	|			|
 *  	|___________|
 * 	(x1,y1)
 * 	
 * Sum of region = sum(x2,y2) - sum(x2, y1-1) - sum(x1-1,y2) + sum(x1-1, y1-1)
 * The updateBIT(x, y, val) function updates all the elements under the region – (x, y) to (N, M) 
 * where, N = maximum X co-ordinate of the whole matrix.
 * M = maximum Y co-ordinate of the whole matrix.
 * 
 * 
 * 
 */

class NumMatrix {
	int [][]matrix;
	int [][]bit;
	
	public NumMatrix(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return;
		this.matrix = matrix;
		this.bit = new int[matrix.length+1][matrix[0].length+1];
		for(int i = 0;i < matrix.length;i++) {
			for(int j = 0;j < matrix[0].length;j++)
				update(i, j, matrix[i][j]);
		}
	}
	
	public void update(int row, int col, int val) {
		if(matrix == null)
			return;
		//int adjust = val - matrix[row][col];
		//matrix[row][col] = val;
		row++;
		col++;
		int i = row;
		while(i < bit.length) {
			int j = col;
			while(j < bit[i].length) {
				bit[i][j] += val;//adjust;
				j += j & (-j);
			}
			i += i & (-i);
		}
	}
	
	public int sum(int row, int col) {
		row++;
		col++;
		int i = row, sum = 0;
		while(i > 0) {
			int j = col;
			while(j > 0) {
				sum += bit[i][j];
				j -= j & (-j);
			}
			i -= i & (-i);
		}	
		return sum;
	}
	
	public int sumRegion(int row1, int col1, int row2, int col2) {
		if(matrix == null)
			return 0;
		//If Top Left is chosen as origin, just change parameters accordingly
		return sum(row2, col2) - sum(row2, col1-1) - sum(row1-1, col2) + sum(row1-1, col1-1);
	}
}

public class Main {
	public static void main(String[] args) {
		int [][]matrix = {{3, 0, 1, 4, 2},
						  {5, 6, 3, 2, 1},
						  {1, 2, 0, 1, 5},
						  {4, 1, 0, 1, 7},
						  {1, 0, 3, 0, 5}};
		NumMatrix obj = new NumMatrix(matrix);
		System.out.println(obj.sumRegion(2, 1, 4, 3));
		obj.update(3, 2, 2);
		System.out.println(obj.sumRegion(2, 1, 4, 3));
	}
}