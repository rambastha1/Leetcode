package main;
import java.util.Arrays;

class Solution {
    public int[] hitBricks(int[][] grid, int[][] hits) {
    	int m = grid.length, n = grid[0].length;
    	int []res = new int[hits.length];
    	
    	
     	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]grid = {{1,0,0,0},{1,1,1,0}}, hits = {{1,0}};
		System.out.println(Arrays.toString(new Solution().hitBricks(grid, hits)));
	}
}
