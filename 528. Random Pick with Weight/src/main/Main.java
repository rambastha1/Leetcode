package main;

import java.util.Random;

/* for the array of {1, 3, 4, 6}
 * the pickIndex() call will have 1/(1+3+4+6) = 1/14 = ~7% chance of picking index 0; 3/14 = 21% change of 
 * picking index 1; 4/14 = 29% change of picking index 2; and 6/14 = 43% of picking index 3.
 */

// https://leetcode.com/problems/random-pick-with-weight/discuss/154044/Java-accumulated-freq-sum-and-binary-search
class Solution {
	
	int []prefix;
    public Solution(int[] w) {
        for(int i = 1;i < w.length;i++)
        	w[i] += w[i-1];
        this.prefix = w;
    }
    
    public int pickIndex() {
    	int wt = new Random().nextInt(prefix[prefix.length-1]+1);
    	int l = 0, r = prefix.length-1;
    	while(l < r) {
    		int m = l + (r-l)/2;
    		if(prefix[m] <= wt)
    			return m;
    		else r = m;
    	}    	
    	return l;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */

public class Main {
	public static void main(String[] args) {

	}
}
