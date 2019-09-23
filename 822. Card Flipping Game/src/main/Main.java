package main;

import java.util.HashSet;
import java.util.Set;

/* If fronts[i] == backs[i], it means that fronts[i] is sure to appear on the table, 
 * no matter how we flap this card.
 * In case that fronts[i] and backs[i] are same, fronts[i] become impossible to be good number, 
 * so I add it to a set same.
 * If fronts[i] != backs[i], we can always hide either number by flapping it to back.
 */

class Solution {
	
    public int flipgame(int[] fronts, int[] backs) {
    	Set<Integer> bothsides = new HashSet<>();
    	int n = backs.length;
    	for(int i = 0;i < n;i++) {
    		if(fronts[i] == backs[i])
    			bothsides.add(fronts[i]);
    	}
    	
    	int min = Integer.MAX_VALUE;
    	for(int f : fronts) {
    		if(!bothsides.contains(f))
    			min = Math.min(min, f);
    	}
    	
    	for(int b : backs) {
    		if(!bothsides.contains(b))
    			min = Math.min(min, b);
    	}
    	
    	return min == Integer.MAX_VALUE?0:min;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []fronts = {1,2,4,4,7}, backs = {1,3,4,1,3};
		int []fronts = {1,2}, backs = {2,1};
		System.out.println(new Solution().flipgame(fronts, backs));
	}
}