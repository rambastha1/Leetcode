package main;

import java.util.Arrays;

class Solution {
    public int lastStoneWeight(int[] stones) {
    
    	if(stones == null || stones.length == 0)
    		return 0;
    	if(stones.length == 1) return stones[0];
    	Arrays.sort(stones);
    	if(stones.length == 2)
    		return stones[0]==stones[1]?0:stones[1]-stones[0];
    	
    	int size = stones.length-1;
    	while(size >= 1) {
    		int a = stones[size];
    		int b = stones[size-1];
    		
    		if(a == b) {
    			size -= 2;
    		} else {
    			stones[size-1] = a-b;
    			size--;
    			Arrays.sort(stones);
    		}
    	}
    	if(size == 0) return stones[0];
    	return 0;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []stones = {2,7,4,1,8,1};
		int []stones = {3,7,2};
		System.out.println(new Solution().lastStoneWeight(stones));
	}
}