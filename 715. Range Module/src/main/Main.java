package main;

import java.util.TreeMap;

//https://leetcode.com/problems/range-module/discuss/108910/Java-TreeMap 
// check first comment

class RangeModule {
    TreeMap<Integer, Integer> map;
    public RangeModule() {
        map = new TreeMap<>();
    }
    
    public void addRange(int left, int right) {
        if (right <= left) return;
        Integer start = map.floorKey(left);
        Integer end = map.floorKey(right);
        if(start != null && map.get(start) >= left){
            left = start;
        }
        if(end != null && map.get(end) > right){
            right = map.get(end);
        }
        map.put(left, right);
        map.subMap(left, false, right, true).clear();
    }
    
    public boolean queryRange(int left, int right) {
        Integer start = map.floorKey(left);
        if (start == null) return false;
        return map.get(start) >= right;
    }
    
    public void removeRange(int left, int right) {
    	Integer start = map.floorKey(left);
        Integer end = map.floorKey(right);

        if(end != null && map.get(end) > right){
            map.put(right, map.get(end));
        }
        if(start != null && map.get(start) > left){
            map.put(start, left);
        }
        map.subMap(left, true, right, false).clear();
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */

public class Main {
	public static void main(String[] args) {
		RangeModule obj = new RangeModule();
		obj.addRange(10,20);
		obj.removeRange(14, 16);
		System.out.println(obj.queryRange(10, 14));
		System.out.println(obj.queryRange(13, 15));
		System.out.println(obj.queryRange(16, 17));
		
	}
}
