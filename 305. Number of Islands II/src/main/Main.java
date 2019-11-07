package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* A 2d grid map of m rows and n columns is initially filled with water. 
 * We may perform an addLand operation which turns the water at position (row, col) into a land. 
 * Given a list of positions to operate, count the number of islands after each addLand operation. 
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
 * You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
Follow up:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
 * 
 */

// https://leetcode.com/problems/number-of-islands-ii/discuss/75470/Easiest-Java-Solution-with-Explanations
/* rank is given by size of islands in linear mapping
 * 
 */

class Solution {
	int count = 0;
	
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
    	List<Integer> res = new ArrayList<>();
    	if(m <= 0 || n <= 0)
    		return res;
    	
    	int []size = new int[m*n], parent = new int[m*n];
    	Arrays.fill(parent, -1);
    	int [][]dirs = {{0,-1}, {-1,0}, {0,1}, {1,0}};
    	
    	for(int []position : positions) {
    		int x = position[0];
    		int y = position[1];
    		
    		int current = x*n+y;
    		// if this query is alredy fired, continue
    		if(parent[current] != -1) {
    			res.add(this.count);
    			continue;
    		}
    			
    		parent[current] = current;
    		size[current]++;
    		this.count++;
    		
    		for(int i = 0;i < 4;i++) {
    			int X = x + dirs[i][0];
    			int Y = y + dirs[i][1];
    			int neighbour = X*n+Y;
    			
    			if(issafe(m, n, X, Y, parent)) {
    				// search parent by linear mapping
    				int neigh_root = find(parent, neighbour);
    				int curr_root = find(parent, current);
    				if(neigh_root != curr_root) {
    					union(parent, size, curr_root, neigh_root);
    				}
    			}
    		}
    		res.add(this.count);
    	}
    	return res;
    }
    
    // parent[x*n+y] = -1 means water
    private boolean issafe(int m, int n, int x, int y, int []parent) {
    	return x >= 0 && x < m && y >= 0 && y < n && parent[x*n+y] != -1;
    }
    
    private int find(int []parent, int i) {
    	if(parent[i] != i)
    		parent[i] = find(parent, parent[i]);
    	return parent[i];
    }
    /* rank is determined by size of size of islands 
     * 
     */
    private void union(int []parent, int []size, int x, int y) {
    	if(size[x] < size[y]) {
    		parent[x] = y;
    		size[y] += size[x];
    	} else {
    		parent[y] = x;
    		size[x] += size[y];
    	}
    	this.count--;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int m = 3, n = 3;
		int [][]positions = {{0,0}, {0,1}, {1,2}, {2,1}};*/
		int m = 3, n = 3;
		int [][]positions = {{0,0}, {0,1}, {1,2}, {1,2}};
		System.out.println(new Solution().numIslands2(m, n, positions));
	}
}