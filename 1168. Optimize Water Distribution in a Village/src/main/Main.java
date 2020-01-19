package main;

import java.util.Arrays;
import java.util.Comparator;
//https://leetcode.com/problems/optimize-water-distribution-in-a-village/discuss/365892/Java-Simplified-Kruskal-MST-%2B-Union-Find
/* There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.

For each house i, we can either build a well inside it directly with cost wells[i], or pipe in water from another well to it. 
The costs to lay pipes between houses are given by the array pipes, where each pipes[i] = [house1, house2, cost] 
represents the cost to connect house1 and house2 together using a pipe. Connections are bidirectional.

Find the minimum total cost to supply water to all houses.

Example 1:



Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
Output: 3
Explanation: 
The image shows the costs of connecting houses using pipes.
The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 
so the total cost is 3.
Constraints:

1 <= n <= 10000
wells.length == n
0 <= wells[i] <= 10^5
1 <= pipes.length <= 10000
1 <= pipes[i][0], pipes[i][1] <= n
0 <= pipes[i][2] <= 10^5
pipes[i][0] != pipes[i][1]
 * 
 */
class Solution {
	
	class Node implements Comparable<Node>{
		int src, dest, price;
		
		@Override
		public int compareTo(Node o) {
			return this.price - o.price;
		}
	}
	
	private int find(int i, int []parent) {
		if(parent[i] != i)
			parent[i] = find(parent[i], parent);
		return parent[i];
	}
	
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
    	int p = pipes.length; 
    	Node []arr = new Node[p];
    	for(int i = 0;i < p;i++) {
    		arr[i] = new Node();
    		arr[i].src = pipes[i][0];
    		arr[i].dest = pipes[i][1];
    		arr[i].price = pipes[i][2];
    	}
    	// find total sum and subtract pipe price if it is lower
    	int sum = 0;
    	int []parent = new int[n+1];
    	for(int i = 1;i <= n;i++) {
    		parent[i] = i;
    		sum += wells[i-1];
    	}
    	
    	Arrays.sort(arr);
    	for(Node node : arr) {
    		int x = find(node.src, parent);
    		int y = find(node.dest, parent);
    		// already included
    		if(x == y) 
    			continue;
    		// pipe more expensive
    		// -1 due to array indexing nodes start from 1 to n
    		if(Math.max(wells[x-1], wells[y-1]) < node.price)
    			continue;
    		// subtract higher price well from sum and add pipe price
    		if(wells[x-1] < wells[y-1]) {
    			parent[y] = x;
    			sum = sum - wells[y-1] + node.price;
    		} else {
    			parent[x] = y;
    			sum = sum - wells[x-1] + node.price;
    		}
    	}
    	return sum;
    }
}

public class Main {
	public static void main(String[] args) {
		 int n = 3;
		 int []wells = {1,2,2};
		 int [][]pipes = {{1,2,1},{2,3,1}};
		 System.out.println(new Solution().minCostToSupplyWater(n, wells, pipes));
	}
}