package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {
	
	class State {
		int x, y, count;
		Set<Integer> visited = new HashSet<>();
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
    			} else if(Character.isLowerCase(grid[i].charAt(j)))
    				target++;
    		}
    	}
    	
    	Queue<int[]> q = new LinkedList<int[]>();
    	q.offer(start);
    	int count = 0, path = 0;
    	int [][]dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    	boolean [][]visited = new boolean[m][n];
    	Set<Character> keys = new HashSet<>();
    	
    	while(!q.isEmpty()) {
    		int []node = q.poll();
			int x = node[0], y = node[1];
			if(visited[x][y])
				continue;
			visited[x][y] = true;

			for(int j = 0;j < 4;j++) {
				int X = x + dirs[j][0], Y = y + dirs[j][1];
				if(X < 0 || X >= m || Y < 0 || Y >= n || visited[X][Y] || grid[X].charAt(Y) == '#')
					continue;
				if(Character.isUpperCase(grid[X].charAt(Y)) && !keys.contains(Character.toLowerCase(grid[X].charAt(Y))))
					continue;
				
				if(Character.isLowerCase(grid[X].charAt(Y))) {
					keys.add(grid[X].charAt(Y));
					count++;
                    if(count == target)
				        return path+1;
				}
				path++;
				q.offer(new int[] {X, Y});
			}
    	}
    	return count == target?path:-1;
    }
}

public class Main {
	public static void main(String[] args) {
		//String[] grid = {"@.a.#","###.#","b.A.B"};
		String[] grid = {"@...a",".###A","b.BCc"};
		System.out.println(new Solution().shortestPathAllKeys(grid));
	}
}
