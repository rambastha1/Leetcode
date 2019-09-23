package main;

import java.util.Arrays;
import java.util.List;

class Solution {
    public int findMinDifference(List<String> timePoints) {
    	boolean []arr = new boolean[24*60];
    	int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    	for(int i = 0;i < timePoints.size();i++) {
    		String []str = timePoints.get(i).split(":");
    		int h = Integer.parseInt(str[0]);
    		int m = Integer.valueOf(str[1]);
    		if(arr[h*60+m])
    			return 0;
    		
    		arr[h*60+m] = true;
    		min = Math.min(min, h*60+m);
    		max = Math.max(max, h*60+m);
    	}
    	
    	int distance = Integer.MAX_VALUE;
    	int prev = 0;
    	for(int i = min;i <= max;i++) {
    		if(arr[i]) {
	    		if(i == min) {
	    			distance = Math.min(distance, Math.min(max-min, 1440-max+min));
	    		} /*else if(i == max) {
	    			distance = Math.min(distance, 1440 - max);
	    		}*/ else 
	    			distance = Math.min(distance, i - prev);
	    		prev = i;
    		}
    	}
    	return distance;
    }
}

public class Main {
	public static void main(String[] args) {
		List<String> timePoints = Arrays.asList("23:59","00.00");
		System.out.println(new Solution().findMinDifference(timePoints));
	}
}