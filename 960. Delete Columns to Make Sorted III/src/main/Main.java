package main;

import java.util.Arrays;

// https://leetcode.com/problems/delete-columns-to-make-sorted-iii/discuss/205679/C%2B%2BJavaPython-Maximum-Increasing-Subsequence
class Solution {
	/* Find LIS of each row, delete col = w - LIS 
	 * take min of delete col
	 * Time 0(word length * word length * A.length)
	 * Space 0(word length)
	 * Find LIS substract from length
	 */
    public int minDeletionSize(String[] A) {
    	int n = A.length, w = A[0].length();
    	int []dp = new int[w];
    	dp[0] = 1;
    	
    	int lis = 1;
    	for(int c = 1;c < w;c++) {
    		dp[c] = 1;
    		for(int i = c-1;i >= 0;i--) {
    			int r = 0;
    			for(;r < n;r++) {
    				if(A[r].charAt(i) > A[r].charAt(c))
    					break;
    				
    			}
    			if(r == n && dp[i] + 1 > dp[c]) {
    				dp[c] = 1 + dp[i];
    			}
    		}
    		lis = Math.max(lis, dp[c]);
    	}
    	return w - lis;
    }
}

public class Main {
	public static void main(String[] args) {
		//String []A = {"babca","bbazb"};
		String []A = {"aabbaa","baabab","aaabab"};
		System.out.println(new Solution().minDeletionSize(A));
	}
}
