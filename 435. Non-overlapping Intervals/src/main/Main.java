package main;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
    	if(intervals == null || intervals.length == 0 || intervals[0].length == 0)
    		return 0;
    	
    	Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
    	int count = 0, end = intervals[0][1];
    	for(int i = 1;i < intervals.length;i++) {
    		if(intervals[i][0] >= end) {
    			end = intervals[i][1];
    			continue;
    		}
    		count++;
    		end = Math.min(end, intervals[i][1]);
    	}
    	return count;
    }
}

public class Main {
	public static void main(String[] args) {
		//int [][]intervals = {{1,2}, {2,3}, {3,4}, {1,3}};
		int [][]intervals = {{0,2},{1,3},{2,4},{3,5},{4,6}};
		System.out.println(new Solution().eraseOverlapIntervals(intervals));
	}
}