package main;

import java.util.Arrays;
import java.util.TreeMap;

// https://leetcode.com/problems/data-stream-as-disjoint-intervals/discuss/82553/Java-solution-using-TreeMap-real-O(logN)-per-adding.
class SummaryRanges {

    /** Initialize your data structure here. */
	// value -> start, end
	// map key will always point to lower key
	TreeMap<Integer, int[]> map;
    public SummaryRanges() {
        this.map = new TreeMap<>();
    }
    
    // amazing use of higher bound
    // r will always be lower bound of interval
    public void addNum(int val) {
        if(map.containsKey(val))
        	return;
        Integer l = map.lowerKey(val), r = map.higherKey(val);
        // notice r == val+1
        if(l != null && r != null && map.get(l)[1] == val-1 && r == val+1) {
        	map.get(l)[1] = map.get(r)[1];
        	map.remove(r);
        } else if(l != null && map.get(l)[1] >= val - 1) {
        		map.get(l)[1] = Math.max(map.get(l)[1], val);
        } else if(r != null && val + 1 == r) {
        	map.put(val, new int[] {val, map.get(r)[1]});
        	// this will keep r as small as possible for first condition
        	map.remove(r);
        } else {
        	map.put(val, new int[] {val, val});
        }
    }
    
    public int[][] getIntervals() {
    	int [][]res = new int[map.size()][2];
    	int index = 0;
    	for(int []val : map.values()) {
    		res[index][0] = val[0];
    		res[index++][1] = val[1];
    	}
    	return res;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */

public class Main {
	public static void main(String[] args) {
		SummaryRanges s = new SummaryRanges();
		s.addNum(1);
		s.addNum(3);
		s.addNum(7);
		s.addNum(2);
		s.addNum(6);
	}
}
