package main;

import java.util.TreeMap;

class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
    	if(hand == null || hand.length == 0 || hand.length%W!= 0)
    		return false;
    	TreeMap<Integer, Integer> map = new TreeMap<>();
    	for(int i : hand) 
    		map.put(i, map.getOrDefault(i, 0) + 1);
    	
    	while(map.size() > 0) {
			int key = map.firstKey();
			for(int i = key;i < key + W;i++) {
				if(!map.containsKey(i))
					return false;
				map.put(i, map.get(i) - 1);
				if(map.get(i) == 0)
					map.remove(i);
			}
		}
    	return true;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []hand = {1,2,3,6,2,3,4,7,8};
		int []hand = {1,1,2,2,3,3};
		int W = 3;
		System.out.println(new Solution().isNStraightHand(hand, W));
	}
}