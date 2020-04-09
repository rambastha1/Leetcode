package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {
	// https://leetcode.com/problems/frog-position-after-t-seconds/discuss/532505/Java-Straightforward-BFS-Clean-code-O(N)
	// time 0(N)
    public double frogPosition(int n, int[][] edges, int t, int target) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 1;i <= n;i++)
        	graph.put(i, new ArrayList<>());
        for(int []e : edges) {
            int src = e[0], dest = e[1];
            graph.get(src).add(dest);
            graph.get(dest).add(src);
        }
        
        double []dp = new double[n+1];
        dp[1] = 1.0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        Set<Integer> visited = new HashSet<>();
        visited.add(1);
        
        while(!q.isEmpty() && t-- > 0) {
        	int size = q.size();
        	for(int i = 0;i < size;i++) {
        		int src = q.poll();
        		int count = 0;
        		for(int dest : graph.get(src)) {
        			if(!visited.contains(dest))
        				count++;
        		}
        		
        		for(int dest : graph.get(src)) {
        			if(visited.contains(dest))
        				continue;
        			visited.add(dest);
        			q.offer(dest);
        			dp[dest] += dp[src] * (1/(count*1.0));
        		}
        		if(count > 0)
        			dp[src] = 0;
        	}
        }
        return dp[target];
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 7, t = 2, target = 4;
		int [][]edges = {{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}};
		System.out.println(new Solution().frogPosition(n, edges, t, target));
	}
}
