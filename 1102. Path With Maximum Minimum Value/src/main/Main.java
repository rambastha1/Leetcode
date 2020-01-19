package main;

import java.util.Comparator;
import java.util.PriorityQueue;
 
/* Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].

The score of a path is the minimum value in that path.  For example, the value of the path 8 →  4 →  5 →  9 is 4.

A path moves some number of times from one visited cell to any neighboring unvisited cell in one of the 4 cardinal directions 
(north, east, west, south). 

Example 1:

Input: [[5,4,5],[1,2,6],[7,4,6]]
Output: 4
Explanation: 
The path with the maximum score is highlighted in yellow. 
Example 2:

Input: [[2,2,1,2,2,2],[1,2,2,2,1,2]]
Output: 2
Example 3:

Input: [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
Output: 3
Note:

1 <= R, C <= 100
0 <= A[i][j] <= 10^9
 * 
 */

class Solution {
	
	// Always select the maximum next
	/* https://www2.cs.duke.edu/courses/fall14/compsci330/notes/scribe7.pdf
	 * https://leetcode.com/problems/path-with-maximum-minimum-value/discuss/324923/C%2B%2BJava-Clear-Code-Dijkstra-Algorithm
	 * BFS Dijkstra algorithm: use a priority queue to choose the next step with the maximum value. 
	 * Keep track of the mininum value along the path.
	 * @edaengineer - the proof of correctness is similar to the general proof of correctness of the Dijkstra algorithm 
	 * (see, for example, https://www2.cs.duke.edu/courses/fall14/compsci330/notes/scribe7.pdf )
	 * In our case, the "shortest" distance to the destination is from the vertex S with the maximum possible minimum. 
	 * If we pass through S, we found the answer. The greedy algorithm will always pass through S because we pop the greatest 
	 * next vertex from the priority queue: we either pop the vertex with the value > value(S), or with the value = value(S).
	 * Suppose for a contradiction that the vertex with the maximum possible minimum S1 was never popped from the priority queue. 
	 * It means that we reached the destination along the path with the all values greater than value(S1). 
	 * Which proves that such S1 doesn't exist.
	 */
	
	public int maximumMinimumPath(int[][] A) {
		int [][]dirs = {{0,-1}, {-1,0}, {0,1}, {1,0}};
		// arr[0] -> value arr[1] -> x index arr[2] -> y index
		PriorityQueue<int []> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[0] - o1[0];
			}
		});
		int m = A.length, n = A[0].length;
		boolean [][]visited = new boolean[m][n];
		pq.add(new int[] {A[0][0], 0, 0});
		int maxvalue = A[0][0];
		visited[0][0] = true;
		
		while(!pq.isEmpty()) {
			int []node = pq.poll();
			int val = node[0];
			int x = node[1];
			int y = node[2];
			
			maxvalue = Math.min(maxvalue, val);
			if(x == m-1 && y == n-1)
				return maxvalue;
			
			for(int i = 0;i < 4;i++) {
				int X = x + dirs[i][0];
				int Y = y + dirs[i][1];
				// PQ will make sure that always the maximum neighbour is picked first
				if(issafe(A, X, Y, visited)) {
					visited[X][Y] = true;
					pq.offer(new int[] {A[X][Y], X, Y});
				}
			}
		}
		// couldn't reach R-1, C-1
		return -1;
	}
	
	// TLE
	int ans = Integer.MIN_VALUE;
    public int maximumMinimumPath1(int[][] A) {
    	int m = A.length, n = A[0].length;
    	boolean [][]visited = new boolean[m][n];
    	dfs(A, visited, 0, 0, A[0][0]);
    	return ans;
    }
    
    private void dfs(int [][]A, boolean [][]visited, int x, int y, int temp) {
    	if(!issafe(A, x, y, visited))
    		return;
    	visited[x][y] = true;
    	temp = Math.min(temp, A[x][y]);
    	if(x == A.length-1 && y == A[0].length-1) {
    		//max = Math.max(max, temp);
    		ans = Math.max(ans, temp);
    		visited[x][y] = false;
    		return;
    	}
    	
    	dfs(A, visited, x, y-1, temp);
    	dfs(A, visited, x-1, y, temp);
    	dfs(A, visited, x, y+1, temp);
    	dfs(A, visited, x+1, y, temp);
    	visited[x][y] = false;
    }
    
    private boolean issafe(int [][]A, int x, int y, boolean [][]visited) {
    	return x >= 0 && x < A.length && y >= 0 && y < A[0].length && !visited[x][y];
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]A = {{5,4,5},{1,2,6},{7,4,6}};
		System.out.println(new Solution().maximumMinimumPath(A));
	}
}