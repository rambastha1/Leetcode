package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/* https://github.com/awangdev/LintCode/blob/master/Java/Exam%20Room.java
 * Pq maintains interval(xcoord, ycoord, dist) in decreasing order of distance then
 * increasing order of x
 * when seat poll maximum distance(just poll its max heap)
 * marking this seat as occupied is done by adding two intervals
 * of split interval
 * 
 *  In leave, two intervals contains x or y
 *  delete both intervals and add new interval of remaining x and y of two intervals.
 *  To make leave 0(logn) design custom heap to know its index as well
 */

class ExamRoom {
	
	PriorityQueue<Interval> pq;
	int N;
	
	class Interval {
		int x, y, dist;
		public Interval(int x, int y) {
			this.x = x;
			this.y = y;
			if(x == -1)
				this.dist = y;
			else if(y == N)
				dist = N - 1 - x;
			else
				dist = (int)Math.abs((y-x)/2);
		}
	}
	
    public ExamRoom(int N) {
        pq = new PriorityQueue<>(new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return (o1.dist != o2.dist)?o2.dist-o1.dist:o1.x-o2.x;
			}
		});
        
        this.N = N;
        pq.offer(new Interval(-1, N));
    }
    
    // Time 0(lgN)
    public int seat() {
    	int seat = 0;
    	Interval interval = pq.poll();
    	if(interval.x == -1)
    		seat = 0;
    	else if(interval.y == N)
    		seat = N-1;
    	else {
    		seat = interval.x + (interval.y - interval.x)/2;
    	}
    	
    	pq.offer(new Interval(interval.x, seat));
    	pq.offer(new Interval(seat, interval.y));
    	return seat;
    }
    
    // Time 0(N)
    public void leave(int p) {
    	Interval head = null, tail = null;
    	List<Interval> list = new ArrayList<>(pq);
    	for(Interval interval : list) {
    		if(interval.x == p)
    			tail = interval;
    		if(interval.y == p)
    			head = interval;
    		if(head != null && tail != null)
    			break;
    	}
    	
    	pq.remove(head);
    	pq.remove(tail);
    	pq.offer(new Interval(head.x, tail.y));
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */

public class Main {
	public static void main(String[] args) {
		int N = 10;
		ExamRoom obj = new ExamRoom(N);
		System.out.print(obj.seat() + " ");
		System.out.print(obj.seat() + " ");
		System.out.print(obj.seat() + " ");
		obj.leave(0);
		obj.leave(4);
		System.out.print(obj.seat() + " ");
		System.out.print(obj.seat() + " ");
		System.out.print(obj.seat() + " ");
		System.out.print(obj.seat() + " ");
		System.out.print(obj.seat() + " ");
		System.out.print(obj.seat() + " ");
		System.out.print(obj.seat() + " ");
		System.out.print(obj.seat() + " ");
		System.out.print(obj.seat() + " ");
		obj.leave(0);
		System.out.println();
	}
}
