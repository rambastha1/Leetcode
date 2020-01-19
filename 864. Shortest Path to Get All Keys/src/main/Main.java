package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {
	class State {
		int x, y, key;
		public State(int x,int y, int key) {
			this.x = x;
			this.y = y;
			this.key = key;
		}
	}
	
    public int shortestPathAllKeys(String[] grid) {
    	int m = grid.length, n = grid[0].length();
    	int target = 0;
    	int []start = new int[2];
    	
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			if(grid[i].charAt(j) == '.')
    				continue;
    			if(grid[i].charAt(j) == '@') {
    				start[0] = i;
    				start[1] = j;
    			} else if(Character.isLowerCase(grid[i].charAt(j))) {
    				// target is used as bits 
    				target = Math.max(target, grid[i].charAt(j) - 'a' + 1);
    			}
    		}
    	}
    	
    	Queue<State> q = new LinkedList<State>();
    	q.offer(new State(start[0], start[1], 0));
    	Set<String> visited = new HashSet<>();
    	visited.add(start[0] + ":" + start[1] + ":" + 0);
    	int [][]dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    	
    	int step = 0;
    	while(!q.isEmpty()) {
    		int size = q.size();
    		for(int i = 0;i < size;i++) {
    			State s = q.poll();
    			int x = s.x, y = s.y;
    			if(s.key == (1<<target) - 1)
    				return step;
    			
    			for(int j = 0;j < 4;j++) {
    				int X = x + dirs[j][0], Y = y + dirs[j][1], key = s.key;
    				if(X < 0 || X >= m || Y < 0 || Y >= n || grid[X].charAt(Y) == '#')
    					continue;
    				
    				char c = grid[X].charAt(Y);
    				if(c >= 'a' && c <= 'f')
    					key |= 1 << (c-'a');
    				// this key is not present
    				if(c >= 'A' && c <= 'F' && ((key >> (c-'a')) & 1) == 0)
    					continue;
    				String path = X + ":" + Y + ":" + key;
    				if(!visited.contains(path)) {
    					visited.add(path);
    					q.offer(new State(X, Y, key));
    				}
    			}
    		}
    		step++;
    	}
    	return -1;
    }
}

public class Main {
	public static void main(String[] args) {
		//String[] grid = {"@.a.#","###.#","b.A.B"};
		String[] grid = {"@.a.#","###.#","b.A.B"};
		System.out.println(new Solution().shortestPathAllKeys(grid));
	}
}
