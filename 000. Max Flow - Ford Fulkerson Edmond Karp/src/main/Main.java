package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
// https://www.youtube.com/watch?v=GiN3jRdgxU4

class Solution {
	
	public int maxflow(int [][]capacity, int src, int sink) {
		int m = capacity.length, n = capacity[0].length;
		int [][]residual = new int[m][n];
		for(int i = 0;i < m;i++) {
			for(int j = 0;j < n;j++) {
				residual[i][j] = capacity[i][j];
			}
		}
		
		// store parent of node
		Map<Integer, Integer> parent = new HashMap<Integer, Integer>();
		List<List<Integer>> augpath = new ArrayList<List<Integer>>();
		int maxflow = 0;
		
		// keep checking whether aug path exists
		while(bfs(residual, parent, src, sink)) {
			List<Integer> augmentedpath = new ArrayList<>();
			int flow = Integer.MAX_VALUE;
			int v = sink;
			//move backward using parent to trace path till src
			while(v != src) {
				augmentedpath.add(0, v);
				int u = parent.get(v);
				flow = Math.min(flow, residual[u][v]);
				v = u;
			}
			augmentedpath.add(0, v);
			augpath.add(augmentedpath);
			maxflow += flow;
			
			// 
			v = sink;
			while(v != src) {
				int u = parent.get(v);
				residual[u][v] -= flow;
				residual[v][u] += flow;
				v = u; 
			}
		}
		print(augpath);
		return maxflow;
	}
	
	
	/* parent to track parent of node
	 * check whether augmented path exists from src to sink
	 */
	private boolean bfs(int [][]residual, Map<Integer, Integer> parent, int src, int sink) {
		Set<Integer> visited = new HashSet<>();
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(src);
		visited.add(src);
		boolean augmentedpath = false;
		
		while(!q.isEmpty()) {
			int u = q.poll();
			for(int v = 0;v < residual.length;v++) {
				if(!visited.contains(v) && residual[u][v] > 0) {
					parent.put(v, u);
					visited.add(v);
					q.offer(v);
					if(v == sink) {
						augmentedpath = true;
						break;
					}
				}
			}
		}
		return augmentedpath;
	}
	
	private void print(List<List<Integer>> augpath) {
		for(List<Integer> list : augpath) {
			System.out.println(list);
		}
	}
}

public class Main {
	public static void main(String[] args) {
		int [][]capacity = {{0,16,13,0,0,0}, {0,0,10,12,0,0}, {0,4,0,0,14,0}, {0,0,9,0,0,20}, {0,0,0,7,0,4}, {0,0,0,0,0,0}};
		int src = 0, sink = 5;
		System.out.println(new Solution().maxflow(capacity, src, sink));
	}
}