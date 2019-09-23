package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
	int count;
	int find(int []parent, int i) {
		if(parent[i] != i)
			parent[i] = find(parent, parent[i]);
		return parent[i];
	}
	
	void union(int []parent, int []rank, int i, int j) {
		int x = find(parent, i);
		int y = find(parent, j);
		if(x == y)
			return;
		
		if(rank[x] < rank[y]) {
			parent[x] = y;
		} else if(rank[x] > rank[y]){
			parent[y] = x;
		} else {
			parent[y] = x;
			rank[x]++;
		}
		this.count--;
	}
	
    public int findCircleNum(int[][] M) {
    	this.count = M.length;
    	int []parent = new int[M.length];
    	int []rank = new int[M.length];
    	for(int i = 0;i < parent.length;i++) {
    		parent[i] = i;
    		rank[i] = 0;
    	}
    	
    	for(int i = 0;i < M.length;i++) {
    		for(int j = i+1;j < M[0].length;j++) {
    			if(M[i][j] == 1) {
    				int x = find(parent, i);
    				int y = find(parent, j);
    				if(x != y)
    					union(parent, rank, i, j);
    			}
    		}
    	}
    	return count;
    	//print(parent);
    	/*Map<Integer, Integer> map = new HashMap<>();
    	for(int i = 0;i < parent.length;i++)
    		map.put(parent[i], map.getOrDefault(parent[i], 0) + 1);
    	return map.size();*/
    }
    
    void print(int []parent) {
    	for(int i : parent)
    		System.out.print(i + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]M = {{1,1,0}, {1,1,0},{0,0,1}};
		int [][]M1 = {{1,1,0}, {1,1,1}, {0,1,1}};
		
		int [][]M2 = {{1,1,1,0,1,1,1,0,0,0},
					  {1,1,0,0,0,0,0,1,0,0},
					  {1,0,1,0,0,0,0,0,0,0},
					  {0,0,0,1,1,0,0,0,1,0},
					  {1,0,0,1,1,0,0,0,0,0},
					  {1,0,0,0,0,1,0,0,0,0},
					  {1,0,0,0,0,0,1,0,1,0},
					  {0,1,0,0,0,0,0,1,0,1},
					  {0,0,0,1,0,0,1,0,1,1},
					  {0,0,0,0,0,0,0,1,1,1}};
		
		int [][]M3 = {{1,0,0,1}, {0,1,1,0}, {0,1,1,1}, {1,0,1,1}};
		
		//System.out.println(new Solution().findCircleNum(M));
		//System.out.println(new Solution().findCircleNum(M1));
		//System.out.println(new Solution().findCircleNum(M2));
		System.out.println(new Solution().findCircleNum(M3));
	}
}