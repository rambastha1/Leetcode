package main;

class Solution {
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
    	int [][]ans = new int[R*C][2];
    	// move len distance in d direction
    	int len = 0, c = 1, d = 0;
    	int [][]dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
		System.out.println(r0 + "-" + c0);
    	ans[0] = new int[] {r0,c0};
    	
    	while(c < R*C) {
    		if(d == 0 || d == 2)
    			len++;
    		for(int i = 0;i < len;i++) {
    			r0 += dir[d][0];
    			c0 += dir[d][1];
    			if(r0 >= 0 && r0 < R && c0 >= 0 && c0 < C) {
    				System.out.println(r0 + "-" + c0);
    				ans[c++] = new int[]{r0,c0};
    			}
    		}
    		d = (d+1)%4;
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int R = 1, C = 4, r0 = 0, c0 = 0;
		new Solution().spiralMatrixIII(R, C, r0, c0);
	}
}
