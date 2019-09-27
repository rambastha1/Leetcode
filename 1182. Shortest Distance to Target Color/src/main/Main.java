package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/shortest-distance-to-target-color/discuss/376925/A-Novel-Method-using-DP-O(n)-or-O(n)-Explained

class Solution {
	
	public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
    	int n = colors.length;
    	/* two options do for all colors one by one and store in map color -> int[]
    	 * or build 2D array
    	 * create an array to store minimum distance from that index to target color.
    	 * each query is then constant time.
    	 */
    	
    	int [][]temp = new int[4][n];
    	
    	for(int i = 1;i <= 3;i++) {
    		Arrays.fill(temp[i], -1);
    	}
    	
    	/* first calculate left distance of indices, if left temp[i-1] == -1element has no target element on its left, temp[i] = -1
    	 * else left distance should be temp[i-1] + 1
    	 * similarly traverse from back and adjust minimum distance
    	 */
    	
    	for(int color = 1;color <= 3;color++) {
    		temp[color][0] = colors[0] == color?0:-1;
    		for(int i = 1;i < n;i++) {
    			if(temp[color][i-1] != -1)
    				temp[color][i] = temp[color][i-1] + 1;
    			if(colors[i] == color)
    				temp[color][i] = 0;
    		}
    		
    		for(int i = n-2;i >= 0;i--) {
    			if(temp[color][i+1] != -1) {
    				if(temp[color][i] == -1)
    					temp[color][i] = 1 + temp[color][i+1];
    				else
    					temp[color][i] = Math.min(temp[color][i], 1 + temp[color][i+1]);
    			}
    			if(colors[i] == color)
    				temp[color][i] = 0;
    		}
    	}
    	
    	List<Integer> res = new ArrayList<>();
    	for(int []query : queries) {
    		int index = query[0];
    		int color = query[1];
    		res.add(temp[color][index]);
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []colors = {1,1,2,1,3,2,2,3,3};
		int [][]queries = {{1,3},{2,2},{6,1}};
		System.out.println(new Solution().shortestDistanceColor(colors, queries));
	}
}
