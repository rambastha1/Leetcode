package main;

import java.util.Comparator;
import java.util.PriorityQueue;

class MedianFinder {

    /** initialize your data structure here. */
	/* small keeps values smaller or equal to num
	 * large keeps values larger than num
	 * small size is kept 1 larger so that for odd length return small.peek 
	 * for even length peek of both/2
	 */
	PriorityQueue<Long> large, small;
    public MedianFinder() {
        large = new PriorityQueue<>(new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				return o2.compareTo(o1);
			}
		});
        small = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        large.add((long)num);
        small.add(large.poll());
        if(small.size() - large.size() > 1)
        	large.add(small.poll());
    }
    
    public double findMedian() {
    	if(small.size() > large.size())
    		return small.peek();
    	
        return (small.peek() + large.peek())/2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

public class Main {
	public static void main(String[] args) {
		MedianFinder obj = new MedianFinder();
		obj.addNum(1);
		obj.addNum(2);
		System.out.println(obj.findMedian());
		obj.addNum(3);
		System.out.println(obj.findMedian());
	}
}
