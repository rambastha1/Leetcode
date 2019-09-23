package main;

import java.util.Arrays;
import java.util.List;

/* Given a list of points that form a polygon when joined sequentially, find if this polygon is 
 * convex (Convex polygon definition).
 * Note:
 * There are at least 3 and at most 10,000 points.
 * Coordinates are in the range -10,000 to 10,000.
 * You may assume the polygon formed by given points is always a simple polygon 
 * (Simple polygon definition). In other words, we ensure that exactly two edges intersect at 
 * each vertex, and that edges otherwise don't intersect each other.
 * Example 1:
 * [[0,0],[0,1],[1,1],[1,0]]
 * 
 * Answer: True
 * 
 * Explanation:
 * Example 2:
 * [[0,0],[0,10],[10,10],[10,0],[5,5]]
 * Answer: False
 * 
 * https://massivealgorithms.blogspot.com/2016/12/leetcode-469-convex-polygon.html
 * For each set of three adjacent points A, B, C, find the cross product AB · BC. If the sign of
 * all the cross products is the same, the angles are all positive or negative (depending on the
 * order in which we visit them) so the polygon is convex.
 */

class Solution {
	public boolean isConvex(List<List<Integer>> points) {  
        long prev = 0;  
        int n = points.size();  
        for(int i = 0; i < n; i++) { 
        	// create 2X2 matrix to get determinant
            int[][] m = new int[2][2];  
            for(int j = 0; j < 2; j++) {  
                m[j] = new int[] {points.get((i+j+1)%n).get(0) - points.get(i).get(0),  
                        points.get((i+j+1)%n).get(1) - points.get(i).get(1)};  
            }  
            long cur = det(m);  
            if (cur * prev < 0) return false;  
            prev = cur;  
        }  
        return true;  
    }  
    private long det(int[][] m) {  
        return m[0][0] * m[1][1] - m[0][1] * m[1][0];  
    } 
}

public class Main {
	public static void main(String[] args) {
		List<List<Integer>> points = Arrays.asList(Arrays.asList(0,0),Arrays.asList(0,10),Arrays.asList(10,10),
				Arrays.asList(10,0), Arrays.asList(5,5));
		System.out.println(new Solution().isConvex(points));
	}
}
