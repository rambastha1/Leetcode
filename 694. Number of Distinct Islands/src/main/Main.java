package main;

import java.util.HashSet;
import java.util.Set;

/*
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) 
 * connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid 
 * are surrounded by water.
 * 
 * Count the number of distinct islands. An island is considered to be the same as another 
 * if and only if one island can be translated (and not rotated or reflected) to equal the other.
 * 
 * Example 1:
 * 11000
 * 11000
 * 00011
 * 00011
 * 
 * Given the above grid map, return 1.
 * 
 * Example 2:
 * 11011
 * 10000
 * 00001
 * 11011
 * Given the above grid map, return 3.
 * 
 * Notice that:
 * 11
 * 1
 * 
 * and
 * 1
 * 11
 * 
 * are considered different island shapes, because we do not consider reflection / rotation.
 * 
 * Note: The length of each dimension in the given grid does not exceed 50.
 */

/*
 * Not exact explanation but idea is same
 * This question allows us to ask for the number of different islands. 
 * It is the expansion of the Number of Islands. The difficulty of this question is how to judge 
 * whether the two islands are different islands. The number of the first one must be the same. 
 * However, the same number of 1 is not guaranteed to be the same island. 
 * For example, the two islands in Example 2 are different, that is, 
 * two identical islands can completely overlap by translation, but cannot rotate. So how do we judge? 
 * We find that we can judge by relative position coordinates. 
 * For example, if we use the top left corner of the island as the base point, 
 * then the point to the left of the base point is (0, -1), and the point on the right is (0). , 1), 
 * the upper point is (-1, 0), and the lower point is (1, 0). 
 * Then the two islands in Example 1 can be represented as [(0,0), (0,1), (1,0), (1,1)], 
 * and the order of the points is the base point - the right point - the lower point - Right lower point. 
 * By doing this, we can judge whether the two islands are the same. 
 * The following solution does not use an array to store, but encodes it into a string. 
 * For example, the array of these four points is stored as "0_0_0_1_1_0_1_1_", and then the string is saved. 
 * In the collection unordered_set, using its automatic deduplication feature, 
 * you can get the number of different islands.   
 */


class Solution {
	 public int numDistinctIslands(int[][] grid) {
		 if(grid == null || grid.length == 0)
			 return 0;
		 Set<String> set = new HashSet<>();
		 for(int i = 0;i < grid.length;i++) {
			 for(int j = 0;j < grid[0].length;j++) {
				 if(grid[i][j] == 1) {
					 StringBuilder sb = new StringBuilder();
					 dfs(grid, i, j, sb, "");
					 System.out.println(sb.toString());
					 set.add(sb.toString());
				 }
			 }
		 }
		 return set.size();
	 }
	 
	 void dfs(int [][]grid, int i, int j, StringBuilder sb, String direction) {
		 if(i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] == 0)
			 return;
		 sb.append(direction);
		 grid[i][j] = 0;
		 dfs(grid, i-1, j, sb, "u");
		 dfs(grid, i, j-1, sb, "l");
		 dfs(grid, i+1, j, sb, "d");
		 dfs(grid, i, j+1, sb, "r");
		 sb.append("#");
	 }
}

public class Main {
	public static void main(String[] args) {
		int [][]grid = {{1,1,0,1,1}, {1,0,0,0,0}, {0,0,0,0,1}, {1,1,0,1,1}};
		System.out.println(new Solution().numDistinctIslands(grid));
	}
}