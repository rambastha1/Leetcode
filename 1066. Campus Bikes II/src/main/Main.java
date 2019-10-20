package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. 
 * Each worker and bike is a 2D coordinate on this grid.

We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and 
their assigned bike is minimized.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.
 * 
 * Note:

0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
All worker and bike locations are distinct.
1 <= workers.length <= bikes.length <= 10


 * 
 */

/* hungarian Algorithm
 * https://gist.github.com/pathikrit/bc09b720f476c79a3553#file-library-java-L1396
 * https://www.youtube.com/watch?v=BUGIhEecipE&t=803s
 * https://www.topcoder.com/community/competitive-programming/tutorials/assignment-problem-and-hungarian-algorithm/
 * https://www.youtube.com/watch?v=dQDZNHwuuOY&t=14s
 * https://www.geeksforgeeks.org/hungarian-algorithm-assignment-problem-set-1-introduction/
 * https://www.youtube.com/watch?v=cQ5MsiGaDY8&t=456s
 * 
 * 
 * Minimum cost flow algorithm
 * https://leetcode.com/problems/campus-bikes-ii/discuss/329208/Java-1ms-10-line-dfs-memorization-solution-and-max-flow-min-cost-polynomial-solution
 * https://www.youtube.com/watch?v=UtSrgTsKUfU&list=PL004010FEA702502F&index=23&t=421s
 * best video
 * https://www.youtube.com/watch?v=UdtwpgjfR3g&t=2794s
 */

/* max Flow Min cost using bellman ford 
 * 0(M * N * (M + N + 1) * N) Time
 * 
 */
class Solution {
	
	class Edge {
		int flow, capacity, cost;
		public Edge(int flow, int capacity, int cost) {
			this.flow = flow;
			this.capacity = capacity;
			this.cost = cost;
		}
	}
	
	public int assignBikes(int[][] workers, int[][] bikes) {
    	int m = bikes.length, n = workers.length;
    	Map<Integer, Map<Integer, Edge>> graph = new HashMap<>();
    	boolean []visited = new boolean[m+n+2];
    	int []dp = new int[m+n+2];
    	Arrays.fill(dp, Integer.MAX_VALUE);
    	
    	// init; start 0 end m + n + 1
    	int cost = 0, flow = 0;
    	for(int i = 0;i < n;i++) {
    		graph.put(0	, value)
    	}
    }
    
    private int dist(int []worker, int []bike) {
    	return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}

public class Main {
	public static void main(String[] args) {
		//int [][]workers = {{0,0},{1,1},{2,0}}, bikes = {{1,0},{2,2},{2,1}};
		int [][]workers = {{0,0},{2,1}}, bikes = {{1,2},{3,3}};
		System.out.println(new Solution().assignBikes(workers, bikes));
	}
}