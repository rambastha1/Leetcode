package main;
import java.util.TreeMap;

// just count maximum ongoing events
// similar to meeting rooms 2
class MyCalendarThree {
	
	// time -> count
	TreeMap<Integer, Integer> map;
    public MyCalendarThree() {
    	this.map = new TreeMap<>();
    }
    
    public int book(int start, int end) {
    	map.put(start, map.getOrDefault(start, 0) + 1);
    	map.put(end, map.getOrDefault(end, 0) - 1);
    	
    	int ans = 0, ongoing = 0;
    	for(int val : map.values()) {
    		ongoing += val;
    		ans = Math.max(ans, ongoing);
    	}
    	return ans;
    }
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(start,end);
 */

public class Main {
	public static void main(String[] args) {
		MyCalendarThree m = new MyCalendarThree();
		System.out.println(m.book(24, 40));
		System.out.println(m.book(43, 50));
		System.out.println(m.book(27, 43));
		System.out.println(m.book(5, 21));
		System.out.println(m.book(30, 40));
		System.out.println(m.book(14, 29));
		System.out.println(m.book(3, 19));
		System.out.println(m.book(3, 14));
		System.out.println(m.book(25, 39));
		System.out.println(m.book(6, 19));
	}
}