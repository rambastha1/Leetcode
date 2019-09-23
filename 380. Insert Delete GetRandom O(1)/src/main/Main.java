package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class RandomizedSet {

	Map<Integer, Integer> map;
	List<Integer> list;
    
	/** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
    	if(map.containsKey(val))
    		return false;
    	map.put(val, list.size());
    	list.add(val);
    	return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
    	if(!map.containsKey(val))
    		return false;
    	int index = map.get(val);
    	/* by the time this operation is called list size might change
    	 * if this is not the last element in list
    	 * replace it with element at the last index
    	 * and place last element in this index
    	 * delete last index
    	 */
    	if(index < list.size()) {
    		int last_num = list.get(list.size()-1);
    		// this operation is 0(N) but average time is 0(1)
    		list.set(index, last_num);
    		map.put(last_num, index);
    	}
    	map.remove(val);
    	list.remove(list.size()-1);
    	return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
    	return list.get(new Random().nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

public class Main {
	public static void main(String[] args) {

	}
}
