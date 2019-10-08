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
 * https://leetcode.com/problems/convex-polygon/discuss/95570/Beyond-my-knowledge...-Java-solution-with-in-line-explanation
 */

class Solution {
	public boolean isConvex(List<List<Integer>> points) {
	    List<Integer> back1 = points.get(points.size() - 1);
	    List<Integer> back2 = points.get(points.size() - 2);
	    boolean seenPositive = false, seenNegative = false;
	    
	    for (List<Integer> curr : points) {
	        int orientation = orientation(back2, back1, curr);
	        
	        if (orientation < 0)
	            seenNegative = true;
	        else if (orientation > 0)
	            seenPositive = true;
	            
	        if (seenPositive && seenNegative) return false;
	        
	        back2 = back1; back1 = curr;
	    }
	    
	    return true;
	}

	private int orientation(List<Integer> point1, List<Integer> point2, List<Integer> point3) {
	    int orientation = (point2.get(1) - point1.get(1)) * (point3.get(0) - point2.get(0)) -
	                      (point2.get(0) - point1.get(0)) * (point3.get(1) - point2.get(1));
	    return orientation == 0 ? 0 : orientation / Math.abs(orientation); // 0, 1 or -1
	} 
}

public class Main {
	public static void main(String[] args) {
		List<List<Integer>> points = Arrays.asList(Arrays.asList(0,0),Arrays.asList(0,10),Arrays.asList(10,10),
				Arrays.asList(10,0), Arrays.asList(5,5));
		System.out.println(new Solution().isConvex(points));
	}
}
