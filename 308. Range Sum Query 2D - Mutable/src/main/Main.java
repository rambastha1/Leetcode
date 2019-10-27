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

    int[][] tree;
    int[][] nums;
    int m;
    int n;
    
    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        m = matrix.length;
        n = matrix[0].length;
        tree = new int[m+1][n+1];
        nums = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        if (m == 0 || n == 0) return;
        int delta = val - nums[row][col];
        nums[row][col] = val;
        for (int i = row + 1; i <= m; i += i & (-i)) {
            for (int j = col + 1; j <= n; j += j & (-j)) {
                tree[i][j] += delta;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (m == 0 || n == 0) return 0;
        return sum(row2+1, col2+1) + sum(row1, col1) - sum(row1, col2+1) - sum(row2+1, col1);
    }
    
    public int sum(int row, int col) {
        int sum = 0;
        for (int i = row; i > 0; i -= i & (-i)) {
            for (int j = col; j > 0; j -= j & (-j)) {
                sum += tree[i][j];
            }
        }
        return sum;
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