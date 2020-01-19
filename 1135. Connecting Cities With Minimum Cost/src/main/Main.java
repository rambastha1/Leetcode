package main;

import java.util.Arrays;
import java.util.Comparator;

/* There are N cities numbered from 1 to N.

You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.
  (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)

Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) 
that connects those two cities together.  The cost is the sum of the connection costs used. If the task is impossible, return -1.

 

Example 1:



Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
Output: 6
Explanation: 
Choosing any 2 edges will connect all cities so we choose the minimum 2.
Example 2:



Input: N = 4, connections = [[1,2,3],[3,4,4]]
Output: -1
Explanation: 
There is no way to connect all cities even if all edges are used.
Note:

1 <= N <= 10000
1 <= connections.length <= 10000
1 <= connections[i][0], connections[i][1] <= N
0 <= connections[i][2] <= 10^5
connections[i][0] != connections[i][1]
 * 
 */
class Solution {
	int count;
    public int minimumCost(int N, int[][] connections) {
    	this.count = N;
    	int []parent = new int[N+1], rank = new int[N+1];
    	for(int i = 1;i <= N;i++) {
    		parent[i] = i;
    		rank[i] = 1;
    	}
    	
    	Arrays.sort(connections, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]-o2[2];
			}
		});
    	
    	int ans = 0;
    	for(int i = 0;i < connections.length;i++) {
    		int []city = connections[i];
    		int a = find(city[0], parent);
    		int b = find(city[1], parent);
    		if(a != b) {
    			union(a, b, parent, rank);
    			ans += city[2];
    		}
    	}
    	return this.count==1?ans:-1;
    }
    
    private int find(int i, int []parent) {
    	if(parent[i] != i)
    		parent[i] = find(parent[i], parent);
    	return parent[i];
    }
    
    private void union(int x, int y, int []parent, int []rank) {
    	int a = find(x, parent);
    	int b = find(y, parent);
    	if(rank[a] > rank[b])
    		parent[b] = a;
    	else if(rank[a] < rank[b])
    		parent[a] = b;
    	else {
    		parent[b] = a;
    		rank[a]++;
    	}
    	this.count--;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int N = 3; 
		int [][]connections = {{1,2,5},{1,3,6},{2,3,1}};*/
		int N = 4; 
		int [][]connections = {{1,2,3},{3,4,4}};
		System.out.println(new Solution().minimumCost(N, connections));
	}
}
