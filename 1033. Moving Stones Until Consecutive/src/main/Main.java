package main;

import java.util.Arrays;

class Solution {
	//0(1)
    public int[] numMovesStones(int a, int b, int c) {
    	int []res = new int [2];
    	int []temp = {a,b,c};
    	Arrays.sort(temp);
    	
    	int left = temp[1] - temp[0] - 1;
    	int right = temp[2] - temp[1] - 1;
    	//                X___XX        XX____X         X_X_X  
    	res[0] = Math.min((left>0?1:0) + (right>0?1:0), (left==1||right==1)?1:2);
    	res[1] = left+right; // X________X_________X
    	System.out.println(res[0] + " " + res[1]);
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int a = 7, b = 4, c = 1;
		new Solution().numMovesStones(a, b, c);
	}
}