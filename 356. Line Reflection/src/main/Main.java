package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given set of points.
 * Example 1:
 * 
 * Given points = [[1,1],[-1,1]], return true. 
 * Example 2:
 * 
 * Given points = [[1,1],[-1,-1]], return false. 
 * Follow up:
 * Could you do better than O(n2)? 
 * Hint:
 * Find the smallest and largest x-value for all points.
 * If there is a line then it should be at y = (minX + maxX) / 2.
 * For each point, make sure that it has a reflected point in the opposite side.
 * Cases:  duplicate points
 */

class Solution {
	// Time 0(N) space 0(N)
	public boolean isReflected(int[][] points) {
		if(points.length == 0)
			return true;
		if(points.length == 1)
			return false;
		
		int n = points.length;
		//y -> list of x
		Map<Integer, Set<Integer>> map = new HashMap<>();
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for(int i = 0;i < n;i++) {
			min = Math.min(min, points[i][0]);
			max = Math.max(max, points[i][0]);
			if(!map.containsKey(points[i][1]))
				map.put(points[i][1], new HashSet<>());
			map.get(points[i][1]).add(points[i][0]);
		}
		
		int sum = max + min;
		for(int y : map.keySet()) {
			Set<Integer> set = map.get(y);
			for(int x : set) {
				if(!set.contains(sum-x))
					return false;
			}
		}
		return true;
	}
}

public class Main {
	public static void main(String[] args) {
		int [][]points = {{1,1},{-1,-1}};
		System.out.println(new Solution().isReflected(points));
	}
}
