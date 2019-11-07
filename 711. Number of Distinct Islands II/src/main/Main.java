package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally 
 * (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if they have the same shape, 
or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).

Example 1:
11000
10000
00001
00011
Given the above grid map, return 1.

Notice that:
11
1
and
 1
11
are considered same island shapes. Because if we make a 180 degrees clockwise rotation on the first island, 
then two islands will have the same shapes.
Example 2:
11100
10001
01001
01110
Given the above grid map, return 2.

Here are the two distinct islands:
111
1
and
1
1

Notice that:
111
1
and
1
111
are considered same island shapes. Because if we flip the first array in the up/down direction, then they have the same shapes.
Note: The length of each dimension in the given grid does not exceed 50.
 * 
 * https://en.wikipedia.org/wiki/Dihedral_group
 */

class Solution {
    public int numDistinctIslands2(int[][] grid) {
    	Set<String> islands = new HashSet<>();
    	int m = grid.length, n = grid[0].length;
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			if(grid[i][j] == 1) {
    				List<int[]> cells = new ArrayList<>();
    				dfs(grid, cells, i, j);
    				// extra step
    				islands.add(norm(cells));
    			}
    		}
    	}
    	return islands.size();
    }
    /* Each island has 8 equivalent arrangements, which can be formed by combinations of reflections/rotations. 
     * So I compute all those arrangements (s[0], s[1], .., s[7]) and sort them using default ordering, and return the first arrangement. 
     * Equivalent island has the same set of equivalent arrangements so the norm function return the same value.
     * 
     */
    private String norm(List<int[]> cells) {
    	TreeSet<String> normshape = new TreeSet<>();
    	for(int i = -1;i <= 1;i += 2) {
    		for(int j = -1;j <= 1;j += 2) {
    			List<int[]> s1 = new ArrayList<>(), s2 = new ArrayList<>();
    			for(int []cell : cells) {
    				int x = cell[0], y = cell[1];
    				s1.add(new int[] {i*x, j*y});
    				s2.add(new int[] {i*y, j*x});
    			}
    			
    			for(List<int[]> s : Arrays.asList(s1, s2)) {
    				s.sort(new Comparator<int[]>() {
						@Override
						public int compare(int[] o1, int[] o2) {
							return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
						}
					});
    				int x = s.get(0)[0], y = s.get(0)[1];
    				StringBuilder sb = new StringBuilder();
    				for(int []p : s) {
    					sb.append(p[0]-x).append(":").append(p[1]-y).append(":");
    				}
    				normshape.add(sb.toString());
    			}
    		}
    	}
    	return normshape.first();
    }
    
    private void dfs(int [][] grid, List<int[]> cells, int x, int y) {
    	if(!issafe(grid, x, y))
    		return;
    	cells.add(new int[] {x,y});
    	grid[x][y] = -1;
    	dfs(grid, cells, x+1, y);
    	dfs(grid, cells, x-1, y);
    	dfs(grid, cells, x, y+1);
    	dfs(grid, cells, x, y-1);
    }
    
    private boolean issafe(int [][]grid, int x, int y) {
    	return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]grid = {{1,1,1,0,0}, {1,0,0,0,1}, {0,1,0,0,1}, {0,1,1,1,0}};
		System.out.println(new Solution().numDistinctIslands2(grid));
	}
}