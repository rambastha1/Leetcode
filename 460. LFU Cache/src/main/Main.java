package main;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

class LFUCache {
	
	// min frequency
	int capacity, min;
	// key -> value
	Map<Integer, Integer> map;
	// key -> freq
	Map<Integer, Integer> freq;
	// freq -> list of keys
	// linkedhashset maintains order of elements like array list
	Map<Integer, LinkedHashSet<Integer>> lfu;
	
    
	public LFUCache(int capacity) {
		this.capacity = capacity;
		this.min = -1;
		this.map = new HashMap<>();
		this.lfu = new HashMap<>();
		this.freq = new HashMap<>();
		lfu.put(1, new LinkedHashSet<>());
    }
    
    public int get(int key) {
    	if(!map.containsKey(key))
    		return -1;
    	int f = freq.get(key);
    	lfu.get(f).remove(key);
    	freq.put(key, f+1);
    	if(f == min && lfu.get(f).size() == 0)
    		min++;
    	if(!lfu.containsKey(f+1))
    		lfu.put(f+1, new LinkedHashSet<>());
    	lfu.get(f+1).add(key);
        return map.get(key);
    }
    
    public void put(int key, int value) {
    	if(this.capacity <= 0)
    		return;
        if(map.containsKey(key)) {
        	map.put(key, value);
        	get(key);
        	return;
        }
        
        if(map.size() >= this.capacity) {
        	int ele = lfu.get(min).iterator().next();
        	freq.remove(ele);
        	map.remove(ele);
        	lfu.get(min).remove(ele);
        }
        
        map.put(key, value);
        freq.put(key, 1);
        min = 1;
        lfu.get(1).add(key);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

public class Main {
	public static void main(String[] args) {
		int capacity = 2;
		LFUCache l = new LFUCache(capacity);
		l.put(1, 1);
		l.put(2, 2);
		System.out.println(l.get(1));       
		l.put(3, 3);    // evicts key 2
		System.out.println(l.get(2));       
		System.out.println(l.get(3));       // returns 3.
		l.put(4, 4);    // evicts key 1.
		System.out.println(l.get(1));       // returns -1 (not found)
		System.out.println(l.get(3));       // returns 3
		System.out.println(l.get(4)); 
	}
}
