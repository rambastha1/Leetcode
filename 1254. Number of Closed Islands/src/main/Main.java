package main;
/* "&" will evaluate both side even the left part is false
"&&" will ignore the right part if the left part is false
 * discard all 0's that reach to edge, return the rest groups
 */
class Solution {
    public int closedIsland(int[][] grid) {
    	int m = grid.length, n = grid[0].length;
    	int ans = 0;
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			if(grid[i][j] == 0) {
    				if(dfs(grid, i, j))
    					ans++;
    			}
    		}
    	}
    	return ans;
    }
    
    private boolean dfs(int [][]grid, int x, int y) {
    	if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) 
    		return false;
        
        if(grid[x][y] == 1) 
        	return true;
        
        grid[x][y] = 1;
        boolean res = true;
        int [][]dir = {{0,-1}, {0,1}, {1,0},{-1,0}};
        for(int[] d : dir){
            res = res & dfs(grid, x + d[0], y + d[1]);
        }
        
        return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]grid = {{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}};
		System.out.println(new Solution().closedIsland(grid));
	}
}
