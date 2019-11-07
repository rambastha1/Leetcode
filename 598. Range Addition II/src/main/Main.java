package main;

class Solution {
	// Time 0(N) N->ops.length
    public int maxCount(int m, int n, int[][] ops) {
    	int row = m, col = n;
    	for(int []op : ops) {
    		row = Math.min(row, op[0]);
    		col = Math.min(col, op[1]);
    	}
    	return row*col;
    }
}

public class Main {
	public static void main(String[] args) {
		int m = 3, n = 3;
		int [][]ops = {{2,2}, {3,3}};
		System.out.println(new Solution().maxCount(m, n, ops));
	}
}