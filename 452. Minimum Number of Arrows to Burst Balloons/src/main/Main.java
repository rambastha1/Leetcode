package main;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int findMinArrowShots(int[][] points) {
    	if(points.length == 0 || points[0].length == 0)
            return 0;
    	
    	/* Sort in increasing order of end. Hot at end point because its sorted and most
    	 * will at end point
    	 * Second, sort in increasing order of start, hit at start 
    	 */    	
    	
    	Arrays.sort(points, new Comparator<int []>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
    	int count = 1, end = points[0][1];
    	for(int i = 1;i < points.length;i++) {
    		/* 
    		 * continue when start[i] < end[0] because count is initialized with 1
    		 * means one arrow have been shot and all balloons with start[i] < end[0]
    		 * are already considered 
    		 */
    		if(points[i][0] <= end)
    			continue;
    		count++;
    		end = points[i][1]; 
    	}    	
    	return count;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]points = {{10,16}, {2,8}, {1,6}, {7,12}};
		System.out.println(new Solution().findMinArrowShots(points));
	}
}