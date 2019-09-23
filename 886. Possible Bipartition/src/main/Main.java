package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
    	List<Integer> []graph = new ArrayList[N+1];
    	for(int i = 0;i <= N;i++)
    		graph[i] = new ArrayList<>();
    	
    	for(int []d : dislikes) {
    		graph[d[0]].add(d[1]);
    		graph[d[1]].add(d[0]);
    	}
    	
    	int []color = new int[N+1];
    	for(int i = 1;i <= N;i++) {
    		if(color[i] != 0)
    			continue;
    		Queue<Integer> q = new LinkedList<>();
    		q.offer(i);
        	color[i] = 1;
    		while(!q.isEmpty()) {
        		int node = q.poll();
        		for(int dest : graph[node]) {
        			if(color[dest] == 0) {
        				color[dest] = -color[node];
        				q.offer(dest);
        			} else {
        				if(color[dest] == color[node])
        					return false;
        			}
        		}
        	}
    	}
    	return true;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int N = 4;
		int [][]dislikes = {{1,2},{1,3},{2,4}};*/

		int N = 5;
		int [][]dislikes = {{1,2},{2,3},{3,4},{4,5},{1,5}};

		/*int N = 3;
		int [][]dislikes = {{1,2},{1,3},{2,3}};*/
		
		System.out.println(new Solution().possibleBipartition(N, dislikes));
	}
}
