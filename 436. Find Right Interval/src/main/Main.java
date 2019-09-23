package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
// https://leetcode.com/problems/find-right-interval/discuss/91793/Java-Concise-Binary-Search
class Solution {
    public int[] findRightInterval(int[][] intervals) {
    	
    	int n = intervals.length;
    	if(n == 1) 
    		return new int[] {-1};
    	Map<Integer, Integer> map = new HashMap<>();
    	int []starts = new int[n];
    	
    	for(int i = 0;i < n;i++) {
    		map.put(intervals[i][0], i);
    		starts[i] = intervals[i][0];
    	}
    	
    	Arrays.sort(starts);
    	int []res = new int[n];
    	for(int i = 0;i < n;i++) {
    		int end = intervals[i][1];
    		int start = ceil(starts, end);
    		if(start < end)
    			res[i] = -1;
    		else
    			res[i] = map.get(start);
    	}
    	print(res);
    	return res;
    }
    
    int ceil(int []starts, int val) {
    	int l = 0, r = starts.length-1;
    	while(l < r) {
    		int m = l + (r-l)/2;
    		if(starts[m] < val)
    			l = m+1;
    		else
    			r = m;
    	}
		return starts[l];
    }
    
    void print(int []res) {
    	for(int i : res)
    		System.out.print(i + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		//int [][]intervals = {{1,4}, {2,3}, {3,4}};
		int [][]intervals = {{3,4}, {2,3}, {1,2}};
		new Solution().findRightInterval(intervals);
	}
}
