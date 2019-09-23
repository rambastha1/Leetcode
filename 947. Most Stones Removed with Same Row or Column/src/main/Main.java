package main;

import java.util.HashSet;
import java.util.Set;

/*
 * The input is such that it is difficult to form 2d matrix of 1 for stones and 0 for nothing
 * once its done row = int[graph.length] col = int[graph[0].length]
 * calculate number of stones in row and col
 * for(int i = 0;i < g.len;i++) {
 * 		for(int j = 0;j < g[0].len;j++) {
 * 			if(g[i][j] == 1) row[i]++ col[j]++;
 * 		}
 * }
 * 
 * for(int i = 0;i < g.len;i++) {
 * 		for(int j = 0;j < g[0].len;j++) {
 * 			if(g[i][j] == 1) {
 * 				if(row[i] == 0 || col[j] == 0) row[i] = 0; col[j] = 0; continue;
 * 				
 * 				count++;
 * 			}
 * 		}
 * }
 * return count
 */

/* Input is perfect for graph edge formation
 * Using Disjoint Set
 * Time 0(NlgN)  
 */

class Solution {
	
	int find(int []parent, int i) {
		if(parent[i] != i)
			parent[i] = find(parent, parent[i]);
		return parent[i];
	}
	
	void union(int []parent, int x, int y) {
		int a = find(parent, x);
		int b = find(parent, y);
		parent[b] = a;
	}
	
	/*
	 * because the 0 <= stones[i][j] < 10000, it means both the number of rows/columns 
	 * is less than 10000. by adding 10000 to col, we make sure the minimum col (0) is 
	 * larger than maximum row (9999), so that there is no overlap in the parent array.
	 * same reason for choosing the parent array size to be 20000 (10000 * 2)
	 */
	
    public int removeStones(int[][] stones) {   	
    	int []parent = new int[20000];
    	for(int i = 0;i < parent.length;i++) 
    		parent[i] = i;
    	
    	for(int []stone : stones)
    		union(parent, stone[0], stone[1]+10000);
    	
    	//This will add all sets with only one node(parent)
    	Set<Integer> set = new HashSet<>();
    	for(int i = 0;i < stones.length;i++) {
    		set.add(find(parent, stones[i][0]));
    	}
    	//return all nodes which are not parent
    	return stones.length - set.size();
    }
}

public class Main {
	public static void main(String[] args) {
		//int [][]stones = {{0,0},{0,1}, {1,0}, {1,2}, {2,1}, {2,2}};
		int [][]stones = {{1,2},{1,3}, {3,3}, {3,1}, {2,1}, {1,0}};
		System.out.println(new Solution().removeStones(stones));
	}
}