package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

// Two events conflict iff s1 < e2 and s2 < e1 
// https://leetcode.com/articles/my-calendar-i/

class MyCalendar {
	
	//List<int []> list;
	TreeMap<Integer, Integer> map;
    public MyCalendar() {
        //list = new ArrayList<>();
    	map = new TreeMap<>();
    }
    
    /*
     * 0(lgN) method
     * Store in sorted map get floor and ceiling of start.. check for validity
     */
    
    public boolean book(int start, int end) {
    	Integer prev = map.floorKey(start), next = map.ceilingKey(start);
    	if(prev == null || map.get(prev) <= start || next == null || end <= next) {
    		map.put(start, end);
    		return true;
    	}
    	return false;
    }
    
    //Brute Force
    //Time 0(N^2)
    //For each book its 0(N) and there are N book calls
    /*public boolean book(int start, int end) {
    	if(list.size() == 0) {
    		list.add(new int[] {start,end});
    		return true;
    	}
    	
    	for(int []val : list) {
    		if(start < val[1] && val[0] < end)
    			return false;
    	}
    	list.add(new int[] {start,end});
    	return true;
    }*/
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */

public class Main {
	public static void main(String[] args) {
		MyCalendar obj = new MyCalendar();
		System.out.print(obj.book(47, 50) + " ");
		System.out.print(obj.book(33, 41) + " ");
		System.out.print(obj.book(39, 45) + " ");
		System.out.print(obj.book(33, 42) + " ");
		System.out.print(obj.book(25, 32) + " ");
		System.out.print(obj.book(26, 35) + " ");
		System.out.print(obj.book(19, 25) + " ");
		System.out.print(obj.book(3, 8) + " ");
		System.out.print(obj.book(8, 13) + " ");
		System.out.print(obj.book(18, 27) + " ");
		System.out.println();
	}
}