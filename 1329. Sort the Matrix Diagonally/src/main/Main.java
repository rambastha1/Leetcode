package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
	/* for primary diagonal elements, difference of i-j is equal
	 * 0(m*n*lgn)
	 */ 
    public int[][] diagonalSort(int[][] mat) {
    	int m = mat.length, n = mat[0].length;
    	// i-j -> elements pq will keep sorted
    	Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			if(!map.containsKey(i-j))
    				map.put(i-j, new PriorityQueue<>());
    			map.get(i-j).offer(mat[i][j]);
    		}
    	}
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			mat[i][j] = map.get(i-j).poll();
    		}
    	}
    	return mat;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]mat = {{3,3,1,1},{2,2,1,2},{1,1,1,2}};
		new Solution().diagonalSort(mat);
	}
}
