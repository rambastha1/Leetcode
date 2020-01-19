package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

// https://www.youtube.com/watch?v=GSBLe8cKu0s&feature=emb_logo
// https://github.com/mission-peace/interview/blob/master/src/com/interview/geometry/SkylineDrawing.java
// 0(NlgN) time and 0(N) Space
class Solution {
	// Represents either start or end of building
	class Building {
		int x, height;
		boolean isstart;
		
		public Building(int x,int height, boolean isstart) {
			this.x = x;
			this.height = height;
			this.isstart = isstart;
		}
	}
	
    public List<List<Integer>> getSkyline(int[][] buildings) {
    	List<List<Integer>> res = new ArrayList<>();
    	int n = buildings.length;
    	Building []build = new Building[n*2];
    	int index = 0;
    	for(int []b : buildings) {
    		build[index++] = new Building(b[0], b[2], true);
    		build[index++] = new Building(b[1], b[2], false);
    	}
    	/* first compare by x.
    	 * if two starts are compared then higher height building should be picked first
    	 * if two ends are compared then lower height building should be picked first
    	 * if one start and end is compared then start should appear before end
         */
    	Arrays.sort(build, new Comparator<Building>() {
			@Override
			public int compare(Building o1, Building o2) {
				if(o1.x != o2.x)
					return o1.x - o2.x;
				else {
					if(o1.isstart && o2.isstart)
						return o2.height - o1.height;
					else if(!o1.isstart && !o2.isstart)
						return o1.height - o2.height;
					return o1.isstart?-1:1; 
				}
			}
		});
    	
    	// height -> count
    	TreeMap<Integer, Integer> map = new TreeMap<>();
    	// there is one building with height 0
    	// reduces null pointer checks
    	map.put(0, 1);
    	int prev_max = 0;
    	
    	for(Building b : build) {
    		//if it is start of building then add the height to map. If height already exists then increment
    		if(b.isstart) {
    			map.put(b.height, map.getOrDefault(b.height, 0) + 1);
    		} else {
    			//if it is end of building then decrement or remove the height from map.
    			map.put(b.height, map.getOrDefault(b.height, 0) - 1);
    			if(map.getOrDefault(b.height, 0) <= 0)
    				map.remove(b.height);
    		}
    		//if height changes from previous height then this building x becomes critcal x.
    		int curr_max = map.lastKey();
    		if(curr_max != prev_max) {
    			List<Integer> list = Arrays.asList(b.x, curr_max);
    			res.add(list);
    			prev_max = curr_max;
    		}
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int[][] buildings = {{2,9,10}, {3,7,15}, {5,12,12}, {15,20,10}, {19,24,8}};
		System.out.println(new Solution().getSkyline(buildings));
	}
}
