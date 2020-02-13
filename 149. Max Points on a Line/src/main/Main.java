package main;

import java.util.HashMap;
import java.util.Map;

// https://www.geeksforgeeks.org/count-maximum-points-on-same-line/
/* https://leetcode.com/problems/max-points-on-a-line/discuss/47113/A-java-solution-with-notes
 * Because a line can be decided by a point and a slope, for a given point i, if the slope between point i and j1 is the 
 * same as the slope between i and j2, then points i, j1, j2 must be on a same line.
 * map.get(dx).get(dy) stores the number of points j that have the slope dy/dx.
 * max stores the max number of slopes with the same value. (the max value in the map)
 * And we also need to consider point i itself, so we have max+1
 * But it will miss the points that overlap with point i. So overlap is counting such points.
 * That's why we have max+overlap+1.
 */
class Solution {
    public int maxPoints(int[][] points) {
    	int res = 0;
    	Map<String, Integer> slope = new HashMap<>();
    	for(int i = 0;i < points.length;i++) {
    		slope.clear();
    		int duplicate = 0, max = 0;
    		for(int j = i+1;j < points.length;j++) {
    			int sx = points[j][0] - points[i][0];
    			int sy = points[j][1] - points[i][1];
    			if(sx == 0 && sy == 0) {
    				duplicate++;
    				continue;
    			}
    			/*
    			 * #### Observation
    			 * - If given n points, we can calculate all possible slopes. O(n^2) times
    			 * - For the two dots that generates the same slope, these dots could be on **parallel** slopes
    			 * - figure out how to prune the parallel dots
    			 * #### Trick: prune parallel dots using greatest common divider
    			 * - GCD: greatest common divider
    			 * - Devide the x and y by their greatest common divider, such that x and y can be reduced to minimum value
    			 * - All other x and y can be reduced to such condition as well
    			 * - track the final reduced (x,y) in a map: they are the key to the count

    			 * If vectors (a, b) and (c, d) are collinear, i.e., there exists a none-zero t (not necessarily integer or positive) 
    			 * such that (c, d) = t * (a, b), then we have gcd(c, d) = t * gcd(a, b).
    			 */
    			int gcd = gcd(sx, sy);
    			sx /= gcd;
    			sy /= gcd;
    			String str = sx + ":" + sy;
    			if(!slope.containsKey(str))
    				slope.put(str, 1);
    			else
    				slope.put(str, slope.get(str) + 1);
    			max = Math.max(max, slope.get(str));
    		}
    		res = Math.max(res, 1 + max + duplicate);
    	}
        return res;
    }
    
    int gcd(int a,int b) {
    	if(b == 0)
    		return a;
    	return gcd(b,a%b);
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]points = {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
		System.out.println(new Solution().maxPoints(points));
	}
}
