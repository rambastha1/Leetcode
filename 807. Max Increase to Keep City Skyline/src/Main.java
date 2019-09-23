class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
    	
    	if(grid == null || grid.length == 0 || grid[0].length == 0)
    		return 0;
    	int []row = new int[grid.length];
    	int []col = new int[grid[0].length];
    	int rowi = 0, colj = 0;
    	
    	for(int i = 0;i < grid.length;i++) {
    		for(int j = 0;j < grid[0].length;j++) {
    			row[i] = Math.max(row[i], grid[i][j]);
    			col[j] = Math.max(col[j], grid[i][j]);
    		}
    	}
    	
    	int sum = 0;
    	for(int i = 0;i < grid.length;i++) {
    		for(int j = 0;j < grid[0].length;j++) {
    			sum += Math.min(row[i], col[j]) - grid[i][j];
    		}
    	}    	
    	return sum;
    }
    
    void print(int []arr) {
    	for(int i : arr)
    		System.out.print(i + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		int [][] grid = { {3,0,8,4},{2,4,5,7},{9,2,6,3},{0,3,1,0}};
		System.out.println(new Solution().maxIncreaseKeepingSkyline(grid));
	}
}