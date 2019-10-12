package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair with the 
shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple (worker, bike) 
pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index; if there are multiple 
ways to do that, we choose the pair with the smallest bike index). We repeat this process until there are no available workers.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.


 * https://leetcode.com/problems/campus-bikes/discuss/308738/C%2B%2B-bucket-sort-O(M*N)-time-and-space-solution
 * Since the range of distance is [0, 2000] which is much lower than the # of pairs, which is 1e6. 
 * It's a good time to use bucket sort. Basically, it's to put each pair into the bucket representing its distance. 
 * Eventually, we can loop thru each bucket from lower distance.
 * 
 * in Bucket all bucket[dist] list will be sorted according to worker's id 0-n
 * When pushing_back <worker_id, bike_id> into the buckets, the worker with smaller id will be pushed first, 
 * because we are iterating from worker_id 0 to the workers.size()-1. The case for bike_id is similar. 
 * In the assignment part, for the same Manhattan distance, we always have workers with smaller id be assigned first.
 * 
 * worker[][] and bikes[][] are coordinates of worker0, worker1 ....sorted
 */

class Solution {
	
	public int[] assignBikes(int[][] workers, int[][] bikes) {
    	int m = bikes.length, n = workers.length;
    	// range given in question
    	List<int []> []bucket = new ArrayList[2001];
    	for(int i = 0;i < n;i++) {
    		for(int j = 0;j < m;j++) {
    			int dist = dist(workers[i], bikes[j]);
    			if(bucket[dist] == null)
    				bucket[dist] = new ArrayList<int []>();
    			bucket[dist].add(new int[] {i, j});
    		}
    	}
    	
    	boolean []vis_bike = new boolean[m];
    	int []res = new int[n];
    	Arrays.fill(res, -1);
    	
    	for(int dist = 0;dist <= 2000;dist++) {
    		if(bucket[dist] == null)
    			continue;
    		for(int i = 0;i < bucket[dist].size();i++) {
    			int w = bucket[dist].get(i)[0];
    			int b = bucket[dist].get(i)[1];
    			if(vis_bike[b] || res[w] != -1)
    				continue;
    			res[w] = b;
    			vis_bike[b] = true;
    		}
    	}
    	return res;
    }
    
    private int dist(int []worker, int []bike) {
    	return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]workers = {{0,0},{1,1},{2,0}}, bikes = {{1,0},{2,2},{2,1}};
		System.out.println(Arrays.toString(new Solution().assignBikes(workers, bikes)));
	}
}