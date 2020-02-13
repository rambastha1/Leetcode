package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// 
class Solution {
	// 0(N)
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
    	List<List<Integer>> res = new ArrayList<>();
    	// group size -> id
    	Map<Integer, List<Integer>> map = new HashMap<>();
    	for(int i = 0;i < groupSizes.length;i++) {
    		if(!map.containsKey(groupSizes[i]))
    			map.put(groupSizes[i], new ArrayList<>());
    		List<Integer> curr = map.get(groupSizes[i]);
    		curr.add(i);
    		if(curr.size() == groupSizes[i]) {
    			res.add(curr);
    			map.remove(groupSizes[i]);
    		}
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []groupSizes = {3,3,3,3,3,1,3};
		System.out.println(new Solution().groupThePeople(groupSizes));
	}
}
