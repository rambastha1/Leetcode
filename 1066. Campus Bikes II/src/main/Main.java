package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

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

/* hungarian/ Knuth Munkres Algorithm
 * https://gist.github.com/pathikrit/bc09b720f476c79a3553#file-library-java-L1396
 * https://www.youtube.com/watch?v=BUGIhEecipE&t=803s
 * https://www.topcoder.com/community/competitive-programming/tutorials/assignment-problem-and-hungarian-algorithm/
 * https://www.youtube.com/watch?v=dQDZNHwuuOY&t=14s
 * https://www.geeksforgeeks.org/hungarian-algorithm-assignment-problem-set-1-introduction/
 * https://www.youtube.com/watch?v=cQ5MsiGaDY8&t=456s
https://github.com/yixuaz/algorithm4-princeton-cos226/blob/master/princetonSolution/src/part2/week2/shortestpath/extracredit/CampusBikesII.java
 * 
 * Minimum cost flow algorithm
 * https://leetcode.com/problems/campus-bikes-ii/discuss/329208/Java-1ms-10-line-dfs-memorization-solution-and-max-flow-min-cost-polynomial-solution
 * https://www.youtube.com/watch?v=UtSrgTsKUfU&list=PL004010FEA702502F&index=23&t=421s
 * best video
 * https://www.youtube.com/watch?v=UdtwpgjfR3g&t=2794s
 */

/* max Flow Min cost using bellman ford Kuhn Munkres
 * 0(M * N * (M + N + 1) * N) Time
 * 
 * Dijkstra as scope of this problem is small
 * 
 * 
 */
class Solution {
	
	class Node {
		int worker, mask, cost;
		public Node(int worker, int mask, int cost) {
			this.worker = worker;
			this.mask = mask;
			this.cost = cost;
		}
	}
	
	public int assignBikes(int[][] workers, int[][] bikes) {
    	PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.cost - o2.cost;
			}
		});  
    	
    	Set<String> seen = new HashSet<>();
    	pq.offer(new Node(0, 0, 0));
    	
    	while(!pq.isEmpty()) {
    		Node curr = pq.poll();
    		String key = curr.worker + ":" + curr.mask;
    		// check if worker is already assigned a bike
    		// reason - you can skip if you have already seen this mask
            // is because this is a PQ - and lower cost has already been seen
            // with this exact mask (i.e., those bikes used in some order)
            // then there is no point to consider a higher cost one 
    		if(seen.contains(key))
    			continue;
    		seen.add(key);
    		if(curr.worker == workers.length)
    			return curr.cost;
    		// scan all bikes - and create new nodes into the PQ for next worker.
    		/* mask is running mask of all bikes
    		 * thus if running mask & i == 0 means ith bike is not used up
    		 * depends in this question number of bikes = 10 if larger then problem
    		 * use boolean array visited create a tuple to add to seen set
    		 */
    		for(int i = 0;i < bikes.length;i++) {
    			if((curr.mask & (1<<i)) == 0) {
    				pq.offer(new Node(curr.worker+1, curr.mask | (1<<i), curr.cost + dist(workers[curr.worker], bikes[i])));
    			}
    		}
    	}
    	return -1;
    }
    
    private int dist(int []worker, int []bike) {
    	return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
    
    // Kuhn-Munkres Algorithm for optimal matching of bipartite graph.
    // match[i] = j means i-th bike assigned to j-th worker
    int [][]dist;
    int []costW, costB, match;
    
    public int kuhn_munkres(int [][]workers, int [][]bikes) {
    	match = new int[bikes.length];
    	Arrays.fill(match, -1);
    	initdist(workers, bikes);
    	initcost(workers, bikes);
    	boolean []visitedW = new boolean[workers.length];
    	boolean []visitedB = new boolean[bikes.length];
    	
    	for(int w = 0;w < workers.length;w++) {
    		while(true) {
    			Arrays.fill(visitedW, false);
    			Arrays.fill(visitedB, false);
    			if(augment(w, visitedW, visitedB))
    				break;
    			else
    				update(visitedW, visitedB);
    		}
    	}
    	
    	int ans = 0;
    	for(int b = 0;b < bikes.length;b++) {
    		if(match[b] >= 0) {
    			ans += dist[match[b]][b];
    		}
    	}
    	return ans;
    }
    
    private void initdist(int [][]workers, int [][]bikes) {
    	dist = new int[workers.length][bikes.length];
    	for(int i = 0;i < workers.length;i++) {
    		for(int j = 0;j < bikes.length;j++) {
    			dist[i][j] = dist(workers[i], bikes[j]);
    		}
    	}
    }
    
    private void initcost(int [][]workers, int [][]bikes) {
    	costW = new int[workers.length];
    	costB = new int[bikes.length];
    	Arrays.fill(costW, Integer.MAX_VALUE);
    	for(int i = 0;i < workers.length;i++) {
    		for(int j = 0;j < bikes.length;j++) {
    			// different from maximum matching
    			costW[i] = Math.min(costW[i], dist[i][j]);
    		}
    	}
    }
    
    private boolean augment(int worker, boolean []visitedW, boolean []visitedB) {
    	visitedW[worker] = true;
    	for(int bike = 0;bike < dist[worker].length;bike++) {
    		// This line varies in maximum matching
    		if(visitedB[bike] || costW[worker] + costB[bike] < dist[worker][bike])
    			continue;
    		visitedB[bike] = true;
    		if(match[bike] < 0 || augment(match[bike], visitedW, visitedB)) {
    			match[bike] = worker;
    			return true;
    		}
    	}
    	return false;
    }
    
    private void update(boolean []visitedW, boolean []visitedB) {
    	int dist = Integer.MAX_VALUE;
    	for(int w = 0;w < costW.length;w++) {
    		if(visitedW[w]) {
    			for(int b = 0;b < costB.length;b++) {
    				if(!visitedB[b]) {
    					// This line varies in maximum matching
    					dist = Math.min(dist, this.dist[w][b] - costW[w] - costB[b]);
    				}
    			}
    		}
    	}
    	
    	for(int w = 0;w < costW.length;w++) {
    		if(visitedW[w])
    			// This line varies in maximum matching
    			costW[w] += dist;
    	}
    	
    	for(int b = 0;b < costB.length;b++) {
			if(visitedB[b])
				// This line varies in maximum matching
				costB[b] -= dist;
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		//int [][]workers = {{0,0},{1,1},{2,0}}, bikes = {{1,0},{2,2},{2,1}};
		int [][]workers = {{0,0},{2,1}}, bikes = {{1,2},{3,3}};
		System.out.println(new Solution().assignBikes(workers, bikes));
		System.out.println(new Solution().kuhn_munkres(workers, bikes));
	}
}