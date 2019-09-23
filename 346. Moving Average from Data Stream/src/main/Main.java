package main;

import java.util.ArrayDeque;
import java.util.Deque;

class MovingAverage {

    Deque<Integer> deque;
	double sum, size;
	
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
        this.sum = 0;
        deque = new ArrayDeque<>();
    }
    
    public double next(int val) {
    	sum += val;
    	deque.offerLast(val);
    	if(deque.size() > size) {
    		sum -= deque.peekFirst();
    		deque.pollFirst();
    	}
        return (double)(sum/deque.size());
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */

public class Main {
	public static void main(String[] args) {
		MovingAverage m = new MovingAverage(3);
		System.out.println(m.next(1));
		System.out.println(m.next(10));
		System.out.println(m.next(3));
		System.out.println(m.next(5));
	}
}
