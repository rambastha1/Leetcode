/*
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] 
 * (si < ei),
 * find the minimum number of conference rooms required.
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
 */

package main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


// sort according to end and use pq
/*class Solution {  
	  
}*/  

// sort both start and end
class Solution {
	public int minMeetingRooms(int[][] intervals) {
        int []start = new int[intervals.length];
		int []end = new int[intervals.length];
		for(int i = 0;i < intervals.length;i++) {
			start[i] = intervals[i][0];
			end[i] = intervals[i][1];
		}
		Arrays.sort(start);
		Arrays.sort(end);
		
		int rooms = 0, activemeeting = 0;
		int i = 0, j = 0;
		while(i < intervals.length && j < intervals.length) {
			if(start[i] < end[j]) {
				activemeeting++;
				i++;
			} else {
				activemeeting--;
				j++;
			}
			rooms = Math.max(rooms, activemeeting);
		}
		return rooms;
    } 
	/*public int minMeetingRooms(int[][] intervals) {  
		int n=intervals.length;  
		Arrays.sort(intervals);  
		PriorityQueue<Integer> pq=new PriorityQueue<>();  
		
		for (int i=0; i<n; i++) {  
			while(i>0 && intervals[i].start>=pq.peek()) 
				pq.poll();  
			pq.add(intervals[i].end);  
		}  
		return pq.size();  
	}*/
	
	
	public void print(int [][]arr, int m, int n) {
		for(int i = 0;i < m;i++) {
			for(int j = 0;j < n;j++)
				System.out.print(arr[i][j] + " ");
			System.out.println();
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		/*Solution.Interval[] intervals = new Solution.Interval[3];
		intervals[0] = s.new Interval(0, 30);
		intervals[1] = s.new Interval(5, 10);
		intervals[2] = s.new Interval(15, 20);
		System.out.println(s.minMeetingRooms(intervals));
		System.out.println(s.rooms(intervals));*/
	}
}