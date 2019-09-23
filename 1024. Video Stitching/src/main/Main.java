package main;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int videoStitching(int[][] clips, int T) {
    	if(clips == null || clips.length == 0 || clips[0].length == 0 || T <= 0)
    		return -1;
    	Arrays.sort(clips, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		});
    	int res = 0;
    	
    	int start = 0, end = 0, i = 0;
    	
    	while(start < T) {
    		/*
    		 * This will select farthest end that it can reach with current starting point
    		 * will take care of intervals with overlapping times
    		 * 
    		 */
    		while(i < clips.length && clips[i][0] <= start)
    			end = Math.max(end, clips[i++][1]);
    		
    		// If cannot mocve further return -1
    		if(start == end) return -1;
    		// Start with new end as there is no point in checking earlier points
    		start = end;
    		// with every iteration it adds one interval that move pointer closest to T
    		res++;
    	}
    	return res;
    	
    }
}

public class Main {
	public static void main(String[] args) {
		/*int [][]clips = {{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}};
		int T = 10;*/
		/*int [][]clips = {{0,1},{1,2}};
		int T = 5;*/
		int [][]clips = {{0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},{1,3},{4,7},{1,4},{2,5},{2,6},{3,4},{4,5},{5,7},{6,9}};
		int T = 9;
		System.out.println(new Solution().videoStitching(clips, T));
	}
}