package main;

import java.util.Random;
import java.util.TreeMap;

/* First pick a rectangle at random then pick a point inside that rectangle
 * https://leetcode.com/problems/random-point-in-non-overlapping-rectangles/discuss/154130/Java-Solution.-Randomly-pick-a-rectangle-then-pick-a-point-inside.
 */
class Solution {
	// sum -> index of rectangle
	TreeMap<Integer, Integer> map;
	int [][]rect;
	int sum = 0;
	Random rand;
	
    public Solution(int[][] rects) {
        this.rect = rects;
        map = new TreeMap<>();
        rand = new Random();
        for(int i = 0;i < rect.length;i++) {
        	int []arr = rect[i];
        	sum += (arr[2]-arr[0]+1)*(arr[3]-arr[1]+1);
        	map.put(sum, i);
        }
    }
    
    public int[] pick() {
    	int r = map.ceilingKey(rand.nextInt(sum)+1); 
    	return pickfromrect(map.get(r));
    }
    
    private int[]pickfromrect(int index) {
    	int []arr = rect[index]; 
    	int left = arr[0], right = arr[2], bottom = arr[1], top = arr[3];
    	int []res = new int[2];
    	res[0] = left + rand.nextInt(right-left+1);
    	res[1] = bottom + rand.nextInt(top-bottom+1);
    	return res;
    	
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */

public class Main {
	public static void main(String[] args) {

	}
}
