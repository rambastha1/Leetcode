package main;

import java.util.ArrayList;
import java.util.List;
// https://leetcode.com/problems/insert-interval/discuss/21602/Short-and-straight-forward-Java-solution
// add intervals before new interval start, merge, then add rest
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
    	int n = intervals.length;
    	List<int []> list = new ArrayList<>();
    	int index = 0;
    	while(index < n && intervals[index][1] < newInterval[0]) {
    		list.add(new int[] {intervals[index][0], intervals[index][1]});
    		index++;
    	}
    	
    	while(index < n && intervals[index][0] <= newInterval[1]) {
    		newInterval = new int[] {Math.min(newInterval[0], intervals[index][0]), Math.max(newInterval[1], intervals[index][1])};
    		index++;
    	}
    	
    	list.add(newInterval);
    	while(index < n) {
    		list.add(new int[] {intervals[index][0], intervals[index][1]});
			index++;
    	}
    	int [][]res = new int[list.size()][2];
    	for(int i = 0;i < list.size();i++) {
    		res[i][0] = list.get(i)[0];
    		res[i][1] = list.get(i)[1];
    	}
    	print(res);
    	return res;
    }
    
    void print(int [][]intervals) {
    	for(int []a : intervals) 
    		System.out.println(a[0] + "-" + a[1]);
    }
}

public class Main {
	public static void main(String[] args) {
		int [][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
		int []newInterval = {4,8};
		new Solution().insert(intervals, newInterval);
	}
}
