package main;

class Solution {
// https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/discuss/548459/Java-intuitive-single-pass-%2B-no-DFSBFS-(with-comments)
	int[][][] dirs = {
            {},
            {{0, -1}, {0, 1}},
            {{-1, 0}, {1, 0}},
            {{0, -1}, {1, 0}},
            {{0, 1}, {1, 0}},
            {{0, -1}, {-1, 0}},
            {{-1, 0}, {0, 1}}
	};
	
	public boolean hasValidPath(int[][] grid) {
        int i = 0, j = 0; 
        if (grid[i][j] == 5)
            return false;
        int[] output = dirs[grid[i][j]][1];
        int m = grid.length, n = grid[0].length;
        while (!(i == m - 1 && j == n - 1)) { 
            int[] input = new int[2];
            i += output[0];
            j += output[1];
            input[0] = output[0] * -1; //negative output becomes new input
            input[1] = output[1] * -1;
            if (isValidInput(i, j, input, grid) == false) return false;
            output = findOutput(input, grid[i][j]);
        }
        return true;
    }

    private int[] findOutput(int[] input, int street) { //output is the opposite side of the street's input
        if (dirs[street][0][0] == input[0] && dirs[street][0][1] == input[1]) return dirs[street][1];
        return dirs[street][0];
    }

    private boolean isValidInput(int i, int j, int[] input, int[][] grid) { //checks if out of bounds and if ends of street pieces meet
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return false;
        int[][] twoends = dirs[grid[i][j]];
        for (int[] end : twoends) {
            if (end[0] == input[0] && end[1] == input[1]) return true;
        }
        return false;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]grid = {{1,1,2}};
		System.out.println(new Solution().hasValidPath(grid));
	}
}
