package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

class RandomizedCollection {
	
	ArrayList<Integer> list;
    HashMap<Integer, LinkedHashSet<Integer>> map;
    
    public RandomizedCollection() {
        list = new ArrayList<Integer>();
        map = new HashMap<Integer, LinkedHashSet<Integer>>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        // Add item to map if it doesn't already exist.
        boolean alreadyExists = map.containsKey(val);
        if(!alreadyExists) {
            map.put(val, new LinkedHashSet<Integer>());
        }
        map.get(val).add(list.size());
        list.add(val);
        return !alreadyExists;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) {
            return false;
        }
        // Get arbitary index of the ArrayList that contains val
        LinkedHashSet<Integer> valSet = map.get(val);
        int indexToReplace = valSet.iterator().next();
        
        // Obtain the set of the number in the last place of the ArrayList
        int numAtLastPlace = list.get(list.size() - 1);
        LinkedHashSet<Integer> replaceWith = map.get(numAtLastPlace);
        
        // Replace val at arbitary index with very last number
        list.set(indexToReplace, numAtLastPlace);
        
        // Remove appropriate index
        valSet.remove(indexToReplace);
        
        // Don't change set if we were replacing the removed item with the same number
        if(indexToReplace != list.size() - 1) {
            replaceWith.remove(list.size() - 1);
            replaceWith.add(indexToReplace);
        }
        list.remove(list.size() - 1);
        
        // Remove map entry if set is now empty, then return
        if(valSet.isEmpty()) {
            map.remove(val);
        }
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        // Get linearly random item
        return list.get((int)(Math.random() * list.size()));
    }
    
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

public class Main {
	public static void main(String[] args) {
		RandomizedCollection obj = new RandomizedCollection();
		System.out.println(obj.insert(1));
		System.out.println(obj.insert(1));
		System.out.println(obj.insert(2));
		System.out.println(obj.getRandom());
		System.out.println(obj.remove(1));
		System.out.println(obj.getRandom());
		
		
	}
}
