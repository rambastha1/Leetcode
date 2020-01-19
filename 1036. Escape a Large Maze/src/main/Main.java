package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/* https://leetcode.com/problems/escape-a-large-maze/discuss/282849/Python-BFS-and-DFS-Maximum-Blocked-19900
 * https://www.youtube.com/watch?v=bVqxkkZ0a1M&t=583s
 * https://leetcode.com/problems/escape-a-large-maze/discuss/282870/python-solution-with-picture-show-my-thoughts
 * 
 * 
 * setting a maximum limit if can travel beyond that true or can reach target before that ->true
 * two case source and target on same side of block or either side 
 * check from both source and target
 */
// time 0(n^2) n -> 10^6
class Solution {
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
    	Set<String> block = new HashSet<>();
    	for(int []b : blocked) {
    		String s = b[0] + "-" + b[1];
    		block.add(s);
    	}
    	if(block.contains(source[0] + "-" + source[1]) || block.contains(target[0] + "-" + target[1]))
    		return false;
    	return bfs(source, target, block) && bfs(target, source, block);
    }
    
    private boolean bfs(int []source, int []target, Set<String> block) {
    	Queue<int []> q = new LinkedList<>();
    	q.offer(source);
    	Set<String> visited = new HashSet<>();
    	visited.add(source[0] + "-" + source[1]);
    	
    	int limit = (int)2e4;
    	int [][]dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    	while(!q.isEmpty()) {
			int []node = q.poll();
			int x = node[0], y = node[1];
			for(int j = 0;j < 4;j++) {
				int X = x + dirs[j][0], Y = y + dirs[j][1];
				if(X < 0 || X >= 1000000 || Y < 0 || Y >= 1000000)
					continue;
				String path = X + "-" + Y;
				if(visited.contains(path) || block.contains(path))
					continue;
				if(X == target[0] && Y == target[1])
					return true;
				q.offer(new int[] {X,Y});
				visited.add(path);
			}
    		
    		if(visited.size() == limit)
    			return true;
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]blocked = {{10,9},{9,10},{10,11},{11,10}};
		int[] source = {0,0}, target = {10,10};
		System.out.println(new Solution().isEscapePossible(blocked, source, target));
	}
}
