package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* https://leetcode.com/problems/pacific-atlantic-water-flow/discuss/90733/Java-BFS-and-DFS-from-Ocean
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * means water from the continent to the edges. It is possible only when continent is higher or equal
 * 
 * BFS: Water flood from ocean to the cell. Since water can only flow from high/equal cell 
 * to low cell, add the neighbour cell with height larger or equal to current cell to the 
 * queue and mark as visited.
 * 
 */

class Solution {
	
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
    	List<List<Integer>> res = new ArrayList<>();
    	if(matrix == null || matrix.length == 0)
    		return res;
    	
    	int m = matrix.length, n = matrix[0].length;
    	boolean [][]vis_pacific = new boolean[m][n], vis_atlantic = new boolean[m][n];;
    	Queue<int[]> q_pacific = new LinkedList<>(), q_atlantic = new LinkedList<>();
    	int [][]dirs = {{0,-1}, {-1,0}, {0,1}, {1,0}};
    	
    	for(int i = 0;i < m;i++) {
    		q_pacific.offer(new int[] {i,0});
    		vis_pacific[i][0] = true;
    		
    		q_atlantic.offer(new int[] {i, n-1});
    		vis_atlantic[i][n-1] = true;
    	}
    	
    	for(int j = 0;j < n;j++) {
    		q_pacific.offer(new int[] {0,j});
    		vis_pacific[0][j] = true;
    		
    		q_atlantic.offer(new int[] {m-1, j});
    		vis_atlantic[m-1][j] = true;
    	}
    	
		bfs(matrix, q_pacific, vis_pacific, dirs);
		bfs(matrix, q_atlantic, vis_atlantic, dirs);
    	
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			if(vis_pacific[i][j] && vis_atlantic[i][j]) {
    				List<Integer> list = Arrays.asList(i,j);
    				res.add(list);
    			}
    		}
    	}
    	return res;
    }
    
    void bfs(int [][]matrix, Queue<int[]> q, boolean [][]visited, int [][]dirs) {
    	while(!q.isEmpty()) {
    		int []node = q.poll();
    		int x = node[0], y = node[1];
    		visited[x][y] = true;
    		for(int i = 0;i < 4;i++) {
    			int X = x + dirs[i][0], Y = y + dirs[i][1];
    			if(issafe(matrix, X, Y, visited) && matrix[X][Y] >= matrix[x][y])
    				q.offer(new int[] {X, Y});
    		}
    	}
    }
    
    boolean issafe(int [][]matrix, int x, int y, boolean [][]visited) {
    	return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && !visited[x][y];
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]matrix = {{1,2,2,3,5}, {3,2,3,4,4}, {2,4,5,3,1}, {6,7,1,4,5}, {5,1,1,2,4}};
		System.out.println(new Solution().pacificAtlantic(matrix));
	}
}
