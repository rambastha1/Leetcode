package main;

// https://leetcode.com/problems/reaching-points/discuss/375429/Detailed-explanation.-or-full-through-process-or-Java-100-beat

class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
    	while(sx < tx && sy < ty) {
    		if(tx < ty)
    			ty %= tx;
    		else
    			tx %= ty;
    	}
    	return ((sx==tx && sy <= ty && (ty-sy)%sx==0) || (sy == ty && sx <= tx && (tx-sx)%sy==0)); 
    }
}

public class Main {
	public static void main(String[] args) {
		int sx = 1, sy = 1, tx = 3, ty = 5;
		System.out.println(new Solution().reachingPoints(sx, sy, tx, ty));
	}
}
