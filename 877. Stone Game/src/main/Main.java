package main;

class Solution {
    public boolean stoneGame(int[] piles) {
    	int suma = 0, suml = 0;
    	int l = 0, r= piles.length-1;
    	boolean flag = true;
    	while(l < r) {
    		if(piles[l] <= piles[r]) {
    			if(flag) suma += piles[r--];
    			else suml += piles[r--];
    		} else {
    			if(flag) suma += piles[l++];
    			else suml += piles[l++];
    		}
    	}
    	
    	return suma> suml?true:false;
    }
}

public class Main {
	public static void main(String[] args) {
		int []piles = {5,3,4,5};
		System.out.println(new Solution().stoneGame(piles));
	}
}