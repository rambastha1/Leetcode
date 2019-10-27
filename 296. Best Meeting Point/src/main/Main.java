package main;

import java.util.ArrayList;
import java.util.List;

/* A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, 
 * where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, 
 * where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

Example:

Input: 

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 6 

Explanation: Given three people living at (0,0), (0,4), and (2,2):
             The point (0,2) is an ideal meeting point, as the total travel distance 
             of 2+2+2=6 is minimal. So return 6.
 * 
 */

/* https://leetcode.com/articles/best-meeting-point/
 * To minimize total distance, median should be used because median divides number of 1's on the left and right
 * get coordinates of people in row and column separately.
 * distance of this list from median [a1,a2,a3,a4,a5,a6]
 * say median is a3 distance = (a1-a3) + (a2-a3) + (a3-a3) + (a4-a3) + (a5-a3) 
 * which is equal to (a5-a1) + (a4-a2) + (a3-a3)
 * 
 * thus add manhanttan distance distances of both row and column list to get answer
 * https://leetcode.com/problems/best-meeting-point/discuss/74186/14ms-java-solution
 */

/* Time 0(MN)
 * Space 0(MN) 
 * It will be mn, as all the spots in the give mXn matrix can have 1 and both rows and cols list will have those many inserts
 */
class Solution {
    public int minTotalDistance(int[][] grid) {
    	List<Integer> row = new ArrayList<>(), col = new ArrayList<>();
    	int m = grid.length, n = grid[0].length;
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			if(grid[i][j] == 1) {
    				row.add(i);
    			}
    		}
    	}
    	
    	for(int i = 0;i < n;i++) {
    		for(int j = 0;j < m;j++) {
    			if(grid[j][i] == 1) {
    				col.add(i);
    			}
    		}
    	}
    	
    	// getting in sorted way because median will lie at the center else extra cost to retrieve median
        return dist(row) + dist(col);
    }
    
    private int dist(List<Integer> list) {
    	int sum = 0;
    	int i = 0, j = list.size()-1;
    	while(i <= j) {
    		sum += list.get(j) - list.get(i);
    		i++;
    		j--;
    	}
    	return sum;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]grid = {{1,0,0,0,1}, {0,0,0,0,0}, {0,0,1,0,0}};
		System.out.println(new Solution().minTotalDistance(grid));
	}
}