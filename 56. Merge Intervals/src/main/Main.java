package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/*
 * https://leetcode.com/articles/merge-intervals/
 */

class Solution {
	
	public class Interval {
		int start;
		int end;
		Interval() { start = 0; end = 0; }
		Interval(int s, int e) { start = s; end = e; }
	}
	
	public List<Interval> merge(List<Interval> intervals) {
        List<Interval> list = new ArrayList<Interval>();
        int n = intervals.size();
        int[] start = new int[n];
        int[] end = new int[n];
        for(int i = 0; i < n; i++){
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        for(int i = 1; i <= n; i++){
            if(i != n && start[i] <= end[i - 1]){
                start[i] = start[i - 1];
            }else{
                list.add(new Interval(start[i - 1], end[i - 1]));
            }
        }
            
        return list;
    }
}

public class Main {

	public static void main(String[] args) {
		Solution s = new Solution();
		List<Solution.Interval> intervals = new ArrayList<>();
		intervals.add(s.new Interval(1,3));
		intervals.add(s.new Interval(2,6));
		intervals.add(s.new Interval(8,10));
		intervals.add(s.new Interval(15,18));
		List<Solution.Interval> res = s.merge(intervals);
		for(int i = 0;i < res.size();i++) {
			System.out.println(res.get(i).start + "\t" + res.get(i).end);
		}		
	}
}