package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int totalFruit(int[] tree) {
    	if(tree == null || tree.length == 0)
    		return 0;
    	Map<Integer, Integer> map = new HashMap<>();
    	int start = 0, ans = 0;
    	for(int end = 0;end < tree.length;end++) {
    		map.put(tree[end], map.getOrDefault(tree[end], 0)+1);
    		while(map.size() > 2) {
    			if(map.get(tree[start]) == 1)
    				map.remove(tree[start]);
    			else
    				map.put(tree[start], map.get(tree[start])-1);
    			start++;
    		}
    		ans = Math.max(ans, end-start+1);
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []tree = {0,1,2,2};
		//int []tree = {1,2,3,2,2};
		int []tree = {3,3,3,1,2,1,1,2,3,3,4};
		System.out.println(new Solution().totalFruit(tree));
	}
}
