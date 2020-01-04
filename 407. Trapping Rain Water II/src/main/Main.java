package main;

import java.util.Comparator;
import java.util.PriorityQueue;
// https://www.youtube.com/watch?v=cJayBq38VYw&feature=emb_logo&t=288s
class Solution {
	// time and space 0(m*n)
    public int trapRainWater(int[][] heightMap) {
    	if(heightMap.length == 0 || heightMap[0].length == 0)
    		return 0;
    	int m = heightMap.length, n = heightMap[0].length;
    	int max = 0;
    	PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return heightMap[o1[0]][o1[1]] - heightMap[o2[0]][o2[1]];
			}
		});
    	boolean [][]visited = new boolean[m][n];
    	for(int i = 0;i < m;i++) {
    		pq.offer(new int[] {i,0});
    		pq.offer(new int[] {i,n-1});
    		visited[i][0] = true;
    		visited[i][n-1] = true;
    	}
    	
    	for(int j = 1;j < n-1;j++) {
    		pq.offer(new int[] {0,j});
    		pq.offer(new int[] {m-1,j});
    		visited[0][j] = true;
    		visited[m-1][j] = true;
    	}
    	int [][]dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    	
    	int ans = 0;
    	while(!pq.isEmpty()) {
    		int []node = pq.poll();
    		int x = node[0], y = node[1], val = heightMap[x][y];
    		if(val < max) {
    			ans += max - val;
    		}
    		max = Math.max(max, val);
    		for(int j = 0;j < 4;j++) {
    			int X = x + dirs[j][0], Y = y + dirs[j][1];
    			if(X < 0 || X >= m || Y < 0 || Y >= n || visited[X][Y])
    				continue;
    			visited[X][Y] = true;
    			pq.offer(new int[] {X,Y});
    		}
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]heightMap = {{1,4,3,1,3,2}, {3,2,1,3,2,4}, {2,3,3,2,3,1}};
		System.out.println(new Solution().trapRainWater(heightMap));
	}
}
