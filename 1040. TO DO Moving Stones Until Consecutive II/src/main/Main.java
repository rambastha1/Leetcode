package main;

import java.util.Arrays;

class Solution {
    public int[] numMovesStonesII(int[] stones) {
    	int []res = new int[2];
    	if(stones == null || stones.length == 0)
    		return res;
    	Arrays.sort(stones);
    	int gmin = 0, gmax = 0;
    	for(int i = 2;i < stones.length;i++) {
    		int left = stones[i-1] - stones[i-2] - 1;
    		int right = stones[i] - stones[i-1] - 1;
    		int lmin = Math.min((left>0?1:0) + (right>0?1:0), (left==1||right==1)?1:2);
    		int lmax = left + right;
    		gmin += lmin;
    		gmax += lmax;
    	}
    	res[0] = gmin;
    	res[1] = gmax;
    	System.out.println(res[0] + " " + res[1]);
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []stones = {6,5,4,3,10};
		new Solution().numMovesStonesII(stones);
	}
}