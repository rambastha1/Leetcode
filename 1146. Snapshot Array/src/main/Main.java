package main;

import java.util.TreeMap;
// https://leetcode.com/problems/snapshot-array/discuss/350562/JavaPython-Binary-Search
class SnapshotArray {
	private TreeMap<Integer, Integer> []map;
	private int snap_id = 0, length;
	
	public SnapshotArray(int length) {
		this.length = length;
		map = new TreeMap[length];
		for(int i = 0;i < length;i++) {
			map[i] = new TreeMap<>();
			map[i].put(0, 0);
		}
	}
	//0(lgN) time
	public void set(int index, int val) {
		map[index].put(this.snap_id, val);
	}
	// 0(1) time
	public int snap() {
		return this.snap_id++;
	}
	//0(lgN) time
	public int get(int index, int snap_id) {
		// floor because snap_id asked might be present, so lesser value than that
		// if present return value of same id
		return map[index].floorEntry(snap_id).getValue();
	}
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */

public class Main {
	public static void main(String[] args) {
		int length = 3;
		SnapshotArray s = new SnapshotArray(length);
		s.set(0, 5);
		System.out.println(s.snap());
		s.set(0, 6);
		System.out.println(s.get(0, 0));
	}
}
