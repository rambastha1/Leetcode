package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import main.Solution.Interval;

/* We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation:
There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
 

Example 2:

Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]
 

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. 
For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)

Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

Note:

schedule and schedule[i] are lists with lengths in range [1, 50].
0 <= schedule[i].start < schedule[i].end <= 10^8.
NOTE: input types have been changed on June 17, 2019. Please reset to default code definition to get new method signature.
 * 
 */

class Solution {
	
	class Interval {
		public int start;
		public int end;

		public Interval() {}

		public Interval(int _start, int _end) {
			start = _start;
			end = _end;
		}
	}
	
	public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
		List<Interval> list = new ArrayList<>();
		for(List<Interval> i : schedule) {
			for(Interval j : i) {
				list.add(j);
			}
		}
		
		Collections.sort(list, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});
		// Add all intervals, sort according to start and check for free time
		List<Interval> ans = new ArrayList<>();
		
		Interval temp = list.get(0);
		for(Interval interval : list) {
			if(temp.end < interval.start) {
				ans.add(new Interval(temp.end, interval.start));
				temp = interval;
			} else {
				// important line
				temp = temp.end < interval.end ? interval : temp;
			}
		}
		return ans;
	}
	// Also tried merge logic of merge sort
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		Interval i1 = s.new Interval(1,2);
		Interval i2 = s.new Interval(5,6);
		Interval i3 = s.new Interval(1,3);
		Interval i4 = s.new Interval(4,10);
		//Interval i5 = s.new Interval(9, 12);
		List<List<Interval>> schedule = Arrays.asList(Arrays.asList(i1, i2), Arrays.asList(i3, i4));
		System.out.println(new Solution().employeeFreeTime(schedule));
	}
}