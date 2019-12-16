package main;

import java.util.Arrays;
import java.util.List;

/* Given a 01 matrix M, find the longest line of consecutive one in the matrix. 
 * The line could be horizontal, vertical, diagonal or anti-diagonal.
 * Example:
 * 
 * Input:
 * [[0,1,1,0],
 *  [0,1,1,0],
 *  [0,0,0,1]]
 * Output: 3
 * 
 * Hint: The number of elements in the given matrix will not exceed 10,000.
 * 
 */


class Solution {
	/* from every a[i][j], keep track of number of 1's in row, col, diag and anti-diag from that index
	 * find maximum
	 * Single Pass (row*col)
	 */
	public int longestLine(int[][] M) {
		if(M == null || M.length == 0)
    		return 0;
		int m = M.length, n = M[0].length;
		int row = 0;
		int []col = new int[n];
		int []diag = new int[m+n], anti = new int[m+n];
		
		int max = 0;
		for(int i = 0;i < M.length;i++) {
			row = 0;
    		for(int j = 0;j < M[0].length;j++) {
    			if(M[i][j] == 1) {
    				row++;
    				col[j]++;
    				diag[i+j]++;
    				anti[m-i+j]++;
    				max = Math.max(max, Math.max(row, col[j]));
    				max = Math.max(max, Math.max(diag[i+j], anti[m-i+j]));
    			} else {
    				// working as it is consecutive
    				// remember longest common substring
    				row = 0;
    				col[j] = 0;
    				diag[i+j] = 0;
    				anti[m-i+j] = 0;
    			}
    		}
		}
		return max;
	}
	
	/* In DFS traversing multiple times
	 * time complexity will be 0(row*col*diag) 
	 */
	int max = 0;
	int [][]dirs = {{0,-1}, {-1,0}, {0,1}, {1,0}, {1,1}, {1,-1}};
	
    public int longestLine1(int[][] M) {
    	if(M == null || M.length == 0)
    		return 0;
    	boolean [][]visited = new boolean[M.length][M[0].length];
    	for(int i = 0;i < M.length;i++) {
    		for(int j = 0;j < M[0].length;j++) {
    			for(int dir = 0;dir < 6;dir++) {
    				dfs(M, i, j, dir, 1, visited);
    			}
    		}
    	}
    	return max;
    }
    
    void dfs(int [][]M, int x, int y, int dir, int count, boolean [][]visited) {
    	if(!issafe(M, x, y, visited)) return;
    	visited[x][y] = true;
    	max = Math.max(max, count);
    	dfs(M, x+dirs[dir][0], y+dirs[dir][1], dir, count+1, visited);
    	visited[x][y] = false;    	
    }
    
    boolean issafe(int [][]M, int x, int y, boolean [][]visited) {
    	return x>=0 && x < M.length && y >= 0 && y < M[0].length && M[x][y] == 1 && !visited[x][y];
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]M = {{0,1,1,0}, {0,1,1,0}, {0,0,0,1}};
		System.out.println(new Solution().longestLine(M));
	}
}
