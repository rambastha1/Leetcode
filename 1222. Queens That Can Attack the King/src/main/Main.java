package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
	// 0(N)
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
    	// X -> list of Y coordinates 
    	Map<Integer, Set<Integer>> map = new HashMap<>();
    	for(int []q : queens) {
    		if(!map.containsKey(q[0]))
    			map.put(q[0], new HashSet<>());
    		map.get(q[0]).add(q[1]);
    	}
    	
    	int [][]dirs = {{0,1}, {0,-1}, {-1,0},{1,0}, {-1,1}, {1,-1}, {-1,-1}, {1,1}};
    	List<List<Integer>> res = new ArrayList<>();
    	
    	Search:
    	for(int i = 0;i < 8;i++) {
    		int x = king[0];
    		int y = king[1];
    		while(x < 8 && x >= 0 && y >= 0 && y < 8) {
    			x += dirs[i][0];
    			y += dirs[i][1];
    			if(map.containsKey(x) && map.get(x).contains(y)) {
    				res.add(Arrays.asList(x, y));
    				continue Search;
    			}
    		}
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]queens = {{0,1},{1,0},{4,0},{0,4},{3,3},{2,4}};
		int[] king = {0,0};
		System.out.println(new Solution().queensAttacktheKing(queens, king));
	}
}
