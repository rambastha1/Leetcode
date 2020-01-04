package main;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
    	int n = status.length; // count of boxes
        boolean[] opened = new boolean[n]; // this are used once
        boolean[] tobeopened = new boolean[n];// new box we found
        
        Queue<Integer> q = new LinkedList<>();
        
        for (int v : initialBoxes) {
            q.add(v);
            tobeopened[v] = true; // initial boxes
        }
        
        int candy = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (status[cur] == 1 && !opened[cur]) { // not used and box open
                candy += candies[cur];
                opened[cur] = true;
                for (int k : keys[cur]) { // all keys in that box
                    status[k] = 1;
                    if (tobeopened[k]) 
                    	q.add(k); // box was found and we have the key
                }
                
                for (int k : containedBoxes[cur]) {// all boxes in cur box
                    tobeopened[k] = true;
                    q.add(k);
                }
            }
        }
        return candy;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int []status = {1,0,1,0}, candies = {7,5,4,100}, initialBoxes = {0};
		int [][]keys = {{},{},{1},{}}, containedBoxes = {{1,2},{3},{},{}};*/
		int []status = {1,1,1}, candies = {100,1,100}, initialBoxes = {1};
		int [][]keys = {{},{0,2},{}}, containedBoxes = {{},{},{}};
		System.out.println(new Solution().maxCandies(status, candies, keys, containedBoxes, initialBoxes));
	}
}
