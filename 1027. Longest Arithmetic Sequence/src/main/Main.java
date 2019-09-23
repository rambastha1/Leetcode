package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int longestArithSeqLength(int[] A) {
    	int n = A.length;
    	Map<Integer, Integer> []dp = new HashMap[n];
    	int maxlen = 0;
    	for(int i = 0;i < n;i++) {
    		dp[i] = new HashMap<>();
    		for(int j = 0;j < i;j++) {
    			//Get common difference
    			int common_diff = A[i] - A[j];
    			/*
    			 * If this difference exists at j, it means there is already a sequence of this
    			 * common difference ending at j. Extend this sequence
    			 * Else since two numbers are there, start new sequence of length 2
    			 */
    			
    			dp[i].put(common_diff, dp[j].getOrDefault(common_diff, 1) + 1);
    			//get maxlen for all common difference
    			maxlen = Math.max(maxlen, dp[i].get(common_diff));    			
    		}
    	}    	
    	
    	return maxlen;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {9,4,7,2,10};
		System.out.println(new Solution().longestArithSeqLength(A));
	}
}