package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/* https://leetcode.com/problems/random-flip-matrix/discuss/154053/Java-AC-Solution-call-Least-times-of-Random.nextInt()-function
 * Here we are modeling the matrix as a 1d array with initial size of row*cols.
 * For each flip, randomly pick an index from 0 to size-1 and flip it.
 * 
 * int r = rand.nextInt(total--);
 * Then swap that flipped element with the tail element (index: size-1), 
 * store that mapping info (key: origin index, value: index of the tail) into a Map and 
 * decrease the size.
 * 
 * map.put(r, map.getOrDefault(total, total));
 * Next time when we randomly pick a same index we can go to the map and find the 
 * actual element stores in that index
 * int x = map.getOrDefault(r, r);
 */

class Solution {
    Map<Integer, Integer> map;
    int rows, cols, total;
    Random rand;
    
    public Solution(int n_rows, int n_cols) {
        map = new HashMap<>();
        rand = new Random();
        rows = n_rows; 
        cols = n_cols; 
        total = rows * cols;
    }
    
    public int[] flip() {
        int r = rand.nextInt(total--);
        int x = map.getOrDefault(r, r);
        map.put(r, map.getOrDefault(total, total));
        return new int[]{x / cols, x % cols};
    }
    
    public void reset() {
        map.clear();
        total = rows * cols;
    }
}

public class Main {
	public static void main(String[] args) {

	}
}
