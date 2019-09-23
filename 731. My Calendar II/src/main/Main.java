package main;

import java.util.ArrayList;
import java.util.List;

class MyCalendarTwo {

	class Interval {
		int start, end;
		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	List<Interval> list;
	List<Interval> doublebook;
	
    public MyCalendarTwo() {
        list = new ArrayList<>();
        doublebook = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
    	if(start > end) return false;
    	Interval interval = new Interval(start, end);
    	for(Interval i : doublebook)
    		if(i.start < end && start < i.end)
    			return false;
    	for(Interval i : list) {
    		if(i.start < end && start < i.end) {
    			doublebook.add(new Interval(Math.max(start, i.start), Math.min(end, i.end)));
    		}
    	}
    	list.add(interval);
    	return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */

public class Main {
	public static void main(String[] args) {
		MyCalendarTwo m = new MyCalendarTwo();
		System.out.print(m.book(10, 20) + " ");
		System.out.print(m.book(50, 60) + " ");
		System.out.print(m.book(10, 40) + " ");
		System.out.print(m.book(5, 15) + " ");
		System.out.print(m.book(5, 10) + " ");
		System.out.print(m.book(25, 55) + " ");
		System.out.println();
	}
}
