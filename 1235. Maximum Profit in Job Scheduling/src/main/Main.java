package main;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
	// 0(NlgN)
	class Interval {
		int start, end, profit;
		public Interval(int start, int end, int profit) {
			this.start = start;
			this.end = end;
			this.profit = profit;
		}
	}
	// profit and check all subproblems
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
    	int n = profit.length;
    	Interval[] intervals = new Interval[n];
    	for(int i = 0;i < n;i++) {
    		intervals[i] = new Interval(startTime[i], endTime[i], profit[i]);
    	}
    	Arrays.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.end - o2.end;
			}
		});
    	// dp[i] -> maximum profit from 0-i
    	int []dp = new int[n];
    	dp[0] = intervals[0].profit;
    	for(int i = 1;i < n;i++) {
    		int max = intervals[i].profit;
    		int pos = getposition(intervals, i);
    		if(pos != -1)
    			max += dp[pos];
    		dp[i] = Math.max(dp[i-1], max);
    	}
    	return dp[n-1];
    }
    
    private int getposition(Interval[] intervals, int index) {
    	int l = 0, r = index-1;
    	while(l <= r) {
    		int m = l + (r-l)/2;
    		if(intervals[m].end <= intervals[index].start) {
    			//if(intervals[m+1].end <= intervals[index].start)
    				l = m+1;
    			//else
    				//return m;
    		} else
    			r = m-1;
    	}
    	if(intervals[l].end > intervals[index].start)
    		return l-1;
    	return l;
    }
}

public class Main {
	public static void main(String[] args) {
		int []startTime = {1,2,3,3}, endTime = {3,4,5,6}, profit = {50,10,40,70};
		System.out.println(new Solution().jobScheduling(startTime, endTime, profit));
	}
}
