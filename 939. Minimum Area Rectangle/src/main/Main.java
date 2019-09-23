package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * find two possible diagonal points
 * the other points are symmetric thus calculate area
 */

class Solution {
    public int minAreaRect(int[][] points) {
    	if(points == null || points.length < 4)
    		return 0;
    	//key is x-coordinate value is set of y coordinates
    	Map<Integer, Set<Integer>> map = new HashMap<>();
    	for(int []point : points) {
    		if(!map.containsKey(point[0]))
    			map.put(point[0], new HashSet<>());
    		map.get(point[0]).add(point[1]);
    	}
    	int min = Integer.MAX_VALUE;
    	for(int []p1 : points) {
    		for(int []p2 : points) {
    			//find two diagonal points
    			if(p1[0] == p2[0] || p1[1] == p2[1])
    				continue;
    			if(map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) {
    				min = Math.min(min, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]));
    			}
    		}
    	}
    	return min == Integer.MAX_VALUE?0:min;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][] points = {{1,1},{1,3},{3,1},{3,3},{2,2}};
		System.out.println(new Solution().minAreaRect(points));
	}
}