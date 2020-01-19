package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
/* https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location/discuss/431061/A-star-search
 * https://www.geeksforgeeks.org/a-search-algorithm/
 */
class Solution {
	
	class AStar {
		int f, bx, by, sx, sy, moves;
		public AStar(int f, int moves, int bx, int by, int sx, int sy) {
			this.f = f;
			this.moves = moves;
			this.bx = bx;
			this.by = by;
			this.sx = sx;
			this.sy = sy;
		}
	}
	
    public int minPushBox(char[][] grid) {
    	int m =grid.length, n = grid[0].length;
    	int []s = new int[2], t = new int[2], b = new int[2];
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			char c = grid[i][j];
    			if(c == '.' || c == '#')
    				continue;
    			if(c == 'B') {
    				b[0] = i;
    				b[1] = j;
    			} else if(c == 'S') {
    				s[0] = i;
    				s[1] = j;
    			} else if(c == 'T'){
    				t[0] = i;
    				t[1] = j;
    			}
    		}
    	}
    	int[][] dirs = new int[][]{{0,-1}, {0,1}, {-1,0}, {1,0}};
    	PriorityQueue<AStar> pq = new PriorityQueue<>((p,q)->(p.f-q.f));
    	pq.offer(new AStar(dist(b[0],b[1],t[0],t[1]), 0, b[0], b[1], s[0], s[1]));
    	Set<String> visited = new HashSet<>();
    	
    	// use one with smallest f
    	while(!pq.isEmpty()) {
    		AStar curr = pq.poll();
    		int moves = curr.moves;
    		int sx = curr.sx, sy = curr.sy;
    		int bx = curr.bx, by = curr.by;
    		if(bx == t[0] && by == t[1])
    			return moves;
    		String path = sx + ":" + sy + ":" + bx + ":" + by;
    		if(visited.contains(path))
    			continue;
    		visited.add(path);
    		
    		for(int j = 0;j < 4;j++) {
    			int SX = sx + dirs[j][0], SY = sy + dirs[j][1];
    			if(!issafe(SX, SY, grid))
    				continue;
    			AStar state;
    			if(SX == bx && SY == by) {
    				int BX = bx + dirs[j][0], BY = by + dirs[j][1];
    				if(!issafe(BX, BY, grid))
    					continue;
    				state = new AStar(dist(BX, BY, t[0], t[1]) + moves + 1, moves + 1, BX, BY, SX, SY);
    			} else {
    				state = new AStar(curr.f, moves, bx, by, SX, SY);
    			}
    			pq.offer(state);
    		}
    	}
    	return -1;
    }
    
    private boolean issafe(int x, int y, char[][] grid){
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] != '#';         		
    }
    
    private int dist(int sx, int sy, int tx, int ty) {
    	return Math.abs(sx-tx) + Math.abs(sy-ty);
    }
}
	

public class Main {
	public static void main(String[] args) {
		char[][] grid = {{'#','#','#','#','#','#'}, {'#','T','#','#','#','#'}, {'#','.','.','B','.','#'},
				{'#','.','#','#','.','#'}, {'#','.','.','.','S','#'},{'#','#','#','#','#','#'}};
		System.out.println(new Solution().minPushBox(grid));
	}
}
