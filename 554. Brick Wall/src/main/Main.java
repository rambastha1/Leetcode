package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int leastBricks(List<List<Integer>> wall) {
    	if(wall == null || wall.size() == 0)
    		return 0;
    	if(wall.size() == 1) {
    		if(wall.get(0).size() == 1) return 1;
    		return 0;
    	}
    	
    	
    	Map<Integer, Integer> map = new HashMap<>();
    	for(List<Integer> list : wall) {
    		int sum = 0;
    		for(int i = 0;i < list.size()-1;i++) {
    			sum += list.get(i);
    			map.put(sum, map.getOrDefault(sum, 0) + 1);
    		}
    	}
    	int max = 0;
    	for(int val : map.values())
    		max = Math.max(max, val);
    	//max is maximum gaps at sum 
    	return wall.size() - max;
    }
}

public class Main {
	public static void main(String[] args) {
		List<List<Integer>> wall = Arrays.asList(Arrays.asList(1,2,2,1) , Arrays.asList(3,1,2), 
				Arrays.asList(1,3,2), Arrays.asList(2,4), Arrays.asList(3,1,2), Arrays.asList(1,3,1,1));
		System.out.println(new Solution().leastBricks(wall));
	}
}