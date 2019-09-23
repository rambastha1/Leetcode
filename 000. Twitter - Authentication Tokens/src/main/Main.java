package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
	
	Map<Integer, Integer> map = new HashMap<>();
	int count = 0, time = 0;
	
	public int tokens(int [][]command, int limit) {
		for(int []c : command) {
			time = Math.max(time, c[2]);
			if((c[0] == 0 && map.containsKey(c[1])) || (c[0] == 1 && (!map.containsKey(c[1]) || map.get(c[1]) < c[2])))
				continue;
			map.put(c[1], c[2]+limit);
		}
		for(int value : map.values()) {
			if(value >= time)
				count++;
		}
		return count;
	}
}

public class Main {
	public static void main(String[] args) {
		int [][]command = {{0,1,1}, {0,2,2}, {1,1,5}, {1,2,7}};
		int limit = 4;
		System.out.println(new Solution().tokens(command, limit));
	}
}