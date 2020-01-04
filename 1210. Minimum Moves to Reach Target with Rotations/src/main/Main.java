package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/* suppose (0,0,0,1)
 * move down means down for both positions i.e. (1,0,1,1) whereas move right means (0,1,0,2)
 * similarly (1,0,2,0) moving right here will means (1,1,2,1) move down means (2,0,3,0)
 * clockwise movement is possible from horizontal only and anti-clockwise from vertical only (given in question)
 */
class Solution {
	// Time 0(n*n) have to visit all nodes
    public int minimumMoves(int[][] grid) {
    	int n = grid.length;
    	Queue<int[]> q = new LinkedList<>();
    	q.offer(new int[] {0, 0, 0, 1});
    	Set<String> visited = new HashSet<>();
    	visited.add(getstring(new int[] {0,0,0,1}));
    	int steps = 0;
    	
    	while(!q.isEmpty()) {
    		int size = q.size();
    		for(int i = 0;i < size;i++) {
    			int []s = q.poll();
    			if(s[0] == n-1 && s[1] == n-2 && s[2] == n-1 && s[3] == n-1)
    				return steps;
    			List<int[]> next = nextmove(grid, s);
    			for(int []arr : next) {
    				if(!visited.contains(getstring(arr))) {
    					visited.add(getstring(arr));
    					q.offer(arr);
    				}
    			}
    		}
    		steps++;
    	}
    	return -1;
    }
    
    private List<int[]> nextmove(int [][]grid, int []s) {
    	int n = grid.length;
    	List<int[]> res = new ArrayList<>();
    	int x1 = s[0], y1 = s[1], x2 = s[2], y2 = s[3];
		// horizontal
		if(x1 == x2) {
			//right
			if(y2+1 < n && grid[x1][y2+1] == 0)
				res.add(new int[] {x2,y2,x2,y2+1});
			
			if(x1+1 < n && grid[x1+1][y1] == 0 && grid[x2+1][y2] == 0) {
				//down
				res.add(new int[] {x1+1,y1,x2+1,y2});
				//clockwise
				res.add(new int[] {x1,y1,x1+1,y1});
			}
		}
		
		//vertical
		if(y1 == y2) {
			//down
			if(x2+1 < n && grid[x2+1][y2] == 0)
				res.add(new int[] {x2,y2,x2+1,y2});
			if(y1+1 < n && grid[x1][y1+1] == 0 && grid[x2][y2+1] == 0) {
				//right
				res.add(new int[] {x1,y1+1,x2,y2+1});
				//anti-clockwise
				res.add(new int[] {x1,y1,x1,y1+1});
			}
		}
		return res;
    }
    
    private String getstring(int []arr) {
    	StringBuilder sb = new StringBuilder();
    	for(int a : arr) {
    		sb.append(a).append(",");
    	}
    	sb.deleteCharAt(sb.length()-1);
    	return sb.toString(); 
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]grid = {{0,0,0,0,0,1}, {1,1,0,0,1,0}, {0,0,0,0,1,1}, {0,0,1,0,1,0}, {0,1,1,0,0,0}, {0,1,1,0,0,0}};
		System.out.println(new Solution().minimumMoves(grid));
	}
}
