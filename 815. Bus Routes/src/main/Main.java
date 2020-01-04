package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
	public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) 
        	return 0;
        // stop -> bus #
        HashMap<Integer, List<Integer>> stop = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
            	if(!stop.containsKey(routes[i][j]))
            		stop.put(routes[i][j], new ArrayList<>());
                stop.get(routes[i][j]).add(i);
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        q.offer(S);
        int ans = 1;
        
        // add stops from same bus then check other buses from that stop
        while (!q.isEmpty()) {
        	int size = q.size();
            for (int i = 0;i < size;i++) {
            	int s = q.poll();
                for (int bus : stop.get(s)) {
                	if(visited.contains(bus))
                		continue;
                	visited.add(bus);
                    for (int j = 0; j < routes[bus].length; j++) {
                        if (routes[bus][j] == T)
                            return ans;
                        q.offer(routes[bus][j]);
                    }
                }
            }
            ans++;
        }
        return -1;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]routes = {{1, 7}, {3, 5}};
		int S = 5, T = 5;
		System.out.println(new Solution().numBusesToDestination(routes, S, T));
	}
}
