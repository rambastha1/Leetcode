package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// https://leetcode.com/problems/shortest-path-with-alternating-colors/discuss/339997/Intuitive-Java-Solution-With-Explanation

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
    	// graph of blue and red nodes
    	List<Integer> []reds = new ArrayList[n], blues = new ArrayList[n];
    	for(int []arr : red_edges) {
    		if(reds[arr[0]] == null)
    			reds[arr[0]] = new ArrayList<>();
    		reds[arr[0]].add(arr[1]);
    	}
    	
    	for(int []arr : blue_edges) {
    		if(blues[arr[0]] == null)
    			blues[arr[0]] = new ArrayList<>();
    		blues[arr[0]].add(arr[1]);
    	}
    	
    	Set<String> seen = new HashSet<>();
    	int []res = new int[n];
    	Arrays.fill(res, -1);
    	Queue<int[]> q = new LinkedList<int[]>();
    	q.offer(new int[] {0,0});
    	/* 0 -> root color 1 -> red 2 -> blue
    	 * queue int []-> node number , color of incoming edge 
    	 */
    	int dist = 0;
    	while(!q.isEmpty()) {
    		int size = q.size();
    		for(int i = 0;i < size;i++) {
    			int []curr = q.poll();
    			String key = curr[0] + "," + curr[1];
    			if(seen.contains(key))
    				continue;
    			seen.add(key);
    			if(res[curr[0]] == -1)
    				res[curr[0]] = dist;
    			// add red if incoming is blue and vice versa
    			if(curr[1] == 2 || curr[1] == 0) {
    				if(reds[curr[0]] != null) {
						for(int dest : reds[curr[0]])
							q.offer(new int[] {dest, 1});
    				}
    			}
    			
    			if(curr[1] == 1 || curr[1] == 0) {
    				if(blues[curr[0]] != null) {
    					for(int dest : blues[curr[0]])
    						q.offer(new int[] {dest, 2});
    				}
    			}
    		}
    		dist++;
    	}
    	print(res);
    	return res;
    }
    
    void print(int []res) {
    	for(int i : res)
    		System.out.print(i + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 3;
		int [][]red_edges = {{0,1},{0,2}}, blue_edges = {{1,0}};
		new Solution().shortestAlternatingPaths(n, red_edges, blue_edges);
	}
}
