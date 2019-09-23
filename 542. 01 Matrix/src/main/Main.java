package main;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
/* https://leetcode.com/problems/01-matrix/discuss/101039/java-33ms-solution-with-two-sweeps-in-on
 * The idea is: I want to know the answer for min(up, left, down, right), 
 * but I do not want the recursion relation goes forever. So I compute min(up, left) first, 
 * then this part of recursion relation ends at bottom right. After that I do min(right, down) 
 * in the second sweep to end the recursion relation at top left.
 */

	// DP similar to 764. 764a. largest plus sign and sum Space 0(1)
	public int[][] updateMatrix(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
    		return new int[][] {{}};
    		
    	int m = matrix.length, n = matrix[0].length;
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			if(matrix[i][j] == 1) {
    				matrix[i][j] = Integer.MAX_VALUE;
    				// top
    				if(i > 0 && matrix[i-1][j] != Integer.MAX_VALUE)
    					matrix[i][j] = Math.min(matrix[i][j], 1 + matrix[i-1][j]);
    				// left
    				if(j > 0 && matrix[i][j-1] != Integer.MAX_VALUE)
    					matrix[i][j] = Math.min(matrix[i][j], 1 + matrix[i][j-1]);
    			}
    		}
    	}
    	
    	for(int i = m-1;i >= 0;i--) {
    		for(int j = n-1;j >= 0;j--) {
    			//down
    			if(i < m-1 && matrix[i+1][j] != Integer.MAX_VALUE)
					matrix[i][j] = Math.min(matrix[i][j], 1 + matrix[i+1][j]);
    			//right
				if(j < n-1 && matrix[i][j+1] != Integer.MAX_VALUE)
					matrix[i][j] = Math.min(matrix[i][j], 1 + matrix[i][j+1]);
    		}
    	}
    	print(matrix);
    	return matrix;
	}
	
	
	// BFS
    public int[][] updateMatrix1(int[][] matrix) {
    	if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
    		return new int[][] {{}};
    		
    	int m = matrix.length, n = matrix[0].length;
    	int [][]res = new int[m][n];
    	for(int []arr : res)
    		Arrays.fill(arr, Integer.MAX_VALUE);
    	
    	Queue<int[]> q = new LinkedList<int[]>();
    	boolean [][]visited = new boolean[m][n];
    	int [][]dirs = {{0,-1}, {-1,0}, {0,1}, {1,0}};
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			if(matrix[i][j] == 0) {
    				q.offer(new int[] {i,j});
    				res[i][j] = 0;
    			}
    		}
    	}
    	
    	while(!q.isEmpty()) {
    		int []node = q.poll();
    		if(visited[node[0]][node[1]])
    			continue;
    		visited[node[0]][node[1]] = true;
    		for(int i = 0;i < 4;i++) {
    			int x = node[0] + dirs[i][0];
    			int y = node[1] + dirs[i][1];
    			
    			if(issafe(matrix, m, n, x, y) && res[node[0]][node[1]] + 1 < res[x][y]) {
    				res[x][y] = res[node[0]][node[1]] + 1;
    				q.offer(new int[] {x,y});
    			}
    		}
    	}
    	print(res);
    	return res;
    }
    
    public boolean issafe(int [][]matrix, int m, int n, int i, int j) {
    	return i >= 0 && i < m && j >= 0 && j < n;
    }
    
    public void print(int [][]res) {
    	for(int i = 0;i < res.length;i++) {
    		for(int j = 0;j < res[0].length;j++) {
    			System.out.print(res[i][j] + " ");
    		}
    		System.out.println();
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]matrix = {{0,0,0}, {0,1,0}, {1,1,1}};
		new Solution().updateMatrix(matrix);
	}
}
