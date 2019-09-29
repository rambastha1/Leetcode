package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// https://leetcode.com/problems/minimum-knight-moves/discuss/387071/JavaPython-3-BFS-code-using-symmetry
// https://leetcode.com/problems/minimum-knight-moves/discuss/387036/O(1)-formula.
// https://math.stackexchange.com/questions/1135683/minimum-number-of-steps-for-knight-in-chess

class Solution {
	
    public int minKnightMoves(int x, int y) {
    	int [][]dirs = {{-2,1}, {-1,2}, {1,2}, {2,1}, {2,-1}, {1,-2}, {-1,-2}, {-2,-1}};
    	// x+ y <= 300 constraint given
    	int hash = 601;
    	x = Math.abs(x);
    	y = Math.abs(y);
    	Queue<Integer> queue = new LinkedList<>();
    	Set<Integer> set = new HashSet<>();
    	// 2D array represented as 1D
    	queue.offer(0*hash+0);
    	int moves = 0;
    	
    	while(!queue.isEmpty()) {
    		int size = queue.size();
    		for(int i = 0;i < size;i++) {
    			int val = queue.poll();
    			int r = val/hash;
    			int c = val%hash;
    			if(r == x && c == y)
    				return moves;
    			
    			for(int j = 0;j < dirs.length;j++) {
    				int X = r + dirs[j][0];
    				int Y = c + dirs[j][1];
    				if(X >= -2 && Y >= -2 && !set.contains(X*hash+Y)) {
    					queue.offer(X*hash+Y);
    					set.add(X*hash+Y);
    				}
    			}
    		}
    		moves++;
    	}
    	return -1;
    }
}

public class Main {
	public static void main(String[] args) {
		//int x = 2, y = 1;
		int x = 5, y = 5;
		System.out.println(new Solution().minKnightMoves(x, y));
	}
}