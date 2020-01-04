package main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {
	
	// DP Approach same time as BFS
	// Time 0(2^n * n) space 0(2^n * n)
	public int shortestPathLength1(int[][] graph) {
		int n = graph.length;
		// 1<<n all states
		int [][]dp = new int[n][1<<n];
		Queue<int[]> q = new LinkedList<>();
		for(int i = 0;i < n;i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
			dp[i][1<<i] = 0;
			// similar to BFS node, current state
			q.offer(new int[] {i, 1<<i});
		}
		
		while(!q.isEmpty()) {
			int []node = q.poll();
			int src = node[0], state = node[1];
			for(int dest : graph[src]) {
				int nextstate = state | (1<<dest);
				// Dijkstra like MST
				if(dp[src][state] + 1 < dp[dest][nextstate]) {
					dp[dest][nextstate] = 1 + dp[src][state];
					q.offer(new int[] {dest, nextstate});
				}
			}
		}
		int ans = Integer.MAX_VALUE;
		for(int i = 0;i < n;i++) {
			ans = Math.min(ans, dp[i][(1<<n)-1]);
		}
		return ans;
	}
	
	// Time 0(2^n * n) space 0(2^n * n)
	// https://leetcode.com/problems/shortest-path-visiting-all-nodes/discuss/135712/Java-BFS
    public int shortestPathLength(int[][] graph) {
    	int n = graph.length;
    	Queue<int[]> q = new LinkedList<>();
    	Set<String> visited = new HashSet<>();
    	int target = 0;
    	for(int i = 0;i < n;i++) {
    		int state = (1<<i);
    		target |= (1<<i);
    		// helps to avoid visited in same state as earlier
    		// can visit this node again but with different state
    		visited.add(i + ":" + state);
    		// This will add every vertex with current state
    		q.offer(new int[] {i, state});
    	}
    	
    	int ans = 0;
    	while(!q.isEmpty()) {
    		int size = q.size();
    		for(int i = 0;i < size;i++) {
    			int []node = q.poll();
    			int src = node[0], state = node[1];
    			if(state == target)
    				return ans;
    			
    			for(int dest : graph[src]) {
    				int deststate = state | (1<<dest);
    				if(deststate == target)
    					return 1+ans;
    				if(visited.contains(dest + ":" + deststate))
    					continue;
    				visited.add(dest + ":" + deststate);
    				q.offer(new int[] {dest, deststate});
    			}
    		}
    		ans++;
    	}
    	return -1;
    }
}

public class Main {
	public static void main(String[] args) {
		//int [][]graph = {{1}, {0,2,4}, {1,3,4}, {2}, {1,2}};
		int [][]graph = {{1,2,3}, {0}, {0}, {0}}; 
		System.out.println(new Solution().shortestPathLength(graph));
		System.out.println(new Solution().shortestPathLength1(graph));
	}
}
