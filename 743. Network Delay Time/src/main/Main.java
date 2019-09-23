package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
	
	// Time Complexity 0(ElgV) Dijkstra with Priority Queue
    public int networkDelayTime(int[][] times, int N, int K) {
    	if(N == 0)
    		return 0;
    	// runs E times
    	Map<Integer, List<int[]>> map = new HashMap<>();
    	for(int []edge : times) {
    		if(!map.containsKey(edge[0]))
    			map.put(edge[0], new ArrayList<>());
    		map.get(edge[0]).add(new int[] {edge[1], edge[2]});
    	}
    	
    	PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1]-o2[1];
			}
		});
    	int []dist = new int[N+1];
    	Arrays.fill(dist, Integer.MAX_VALUE);
    	dist[K] = 0;
    	
    	boolean []visited = new boolean[N+1];
    	
    	// node distance
    	pq.offer(new int[] {K,0});
    	int ans = 0;
    	
    	while(!pq.isEmpty()) {
    		int []arr = pq.poll();
    		int node = arr[0];
    		
    		if(visited[node])
    			continue;
    		int d = arr[1];
			visited[node] = true;
			N--;
			// since its pq it gives shortest distance moreover ans should be largest of such dist
			ans = d;
			if(!map.containsKey(node))
				continue;
			
			//runs E+V times
			for(int []next : map.get(node)) {
				if(!visited[next[0]] && d + next[1] < dist[next[0]]) {
					pq.offer(new int[] {next[0], d + next[1]});
				}
			}
    	}
    	return N == 0?ans:-1;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]times = {{2,1,1},{2,3,1},{3,4,1}};
		int N = 4, K = 2;
		System.out.println(new Solution().networkDelayTime(times, N, K));
	}
}
