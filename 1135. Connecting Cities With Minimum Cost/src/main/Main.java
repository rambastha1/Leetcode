package main;

import java.util.Arrays;
import java.util.Comparator;

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
