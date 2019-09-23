package main;

import java.util.Arrays;

/* Bellman ford finds shortest path from single source even in negative cycle.
 * it runs for all V, relaxes all edges, if it runs for K+1 times, it relaxes all edges K times
 * meaning shortest path form source to destination in K path  
 * Code for Bellman ford
 */

class Solution {
	
	// Time 0(KN) space 0(N)
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		int []prices = new int[n];
		Arrays.fill(prices, Integer.MAX_VALUE);
		prices[src] = 0;
		// Not all V only all K
		for(int i = 0;i <= K;i++) {
			int []temp = Arrays.copyOf(prices, n);
			// runs for all edges from vertex v
			for(int []flight : flights) {
				int curr = flight[0], next = flight[1], price = flight[2];
				if(prices[curr] == Integer.MAX_VALUE)
					continue;
				temp[next] = Math.min(temp[next], prices[curr] + price);
			}
			prices = temp;
		}
    	return prices[dst]==Integer.MAX_VALUE?-1:prices[dst];
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]flights = {{0,1,100}, {1,2,100}, {0,2,500}};
		int n = 3, src = 0, dst = 2, K = 1;
		System.out.println(new Solution().findCheapestPrice(n, flights, src, dst, K));
	}
}
