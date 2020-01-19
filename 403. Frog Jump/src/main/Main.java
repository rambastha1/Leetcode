package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
	/* get all the jumps frog can make from this point, mark true false in the end
	 * time 0(n^2)
	 */
    public boolean canCross(int[] stones) {
    	int n = stones.length;
    	if(n == 1)
    		return true;
    	// num -> set of jumps it can take from this position
    	Map<Integer, Set<Integer>> map = new HashMap<>();
    	map.put(0, new HashSet<>());
    	map.get(0).add(1);
    	
    	for(int i = 1;i < n;i++) {
    		if(!map.containsKey(stones[i]))
    			map.put(stones[i], new HashSet<>());
    		for(int j = i-1;j >= 0;j--) {
    			int diff = stones[i] - stones[j];
    			if(map.get(stones[j]).contains(diff)) {
    				if(i == n-1)
    					return true;
    				map.get(stones[i]).add(diff-1);
    				map.get(stones[i]).add(diff);
    				map.get(stones[i]).add(diff+1);    				
    			}
    		}
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		int[] stones = {0,1,3,4,8,9,11};
		System.out.println(new Solution().canCross(stones));
	}
}
