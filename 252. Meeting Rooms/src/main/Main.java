package main;


import java.util.Arrays;
import java.util.Comparator;


/* Given an array of meeting time intervals consisting of start and end times 
 * [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 * Example 1:
 * 
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 * 
 * Input: [[7,10],[2,4]]
 * Output: true
 */

class Solution {
	
	public boolean canAttendMeetings(int[][] intervals) {
    	Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
    	
    	for(int i = 1;i < intervals.length;i++) {
    		if(intervals[i-1][1] > intervals[i][0])
				return false;
    	}
    	return true;
    }
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		/*Interval i1 = s.new Interval(7,10);
		Interval i2 = s.new Interval(2,4);
		Interval [] intervals = {i1,i2};*/
		
		/*Interval i1 = s.new Interval(0,30);
		Interval i2 = s.new Interval(5,10);
		Interval i3 = s.new Interval(15,20);
		Interval [] intervals = {i1,i2,i3};
		System.out.println(s.canAttendMeetings(intervals));*/
	}
}
