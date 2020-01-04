package main;

import java.util.Arrays;
// https://leetcode.com/problems/freedom-trail/discuss/98900/O(RK)-time-DP-solution
class Solution {
    public int findRotateSteps(String ring, String key) {
    	int r = ring.length(), k = key.length();
    	int [][]prev = new int[r][26], next = new int[r][26];
    	
    	// store indices of next occurring characters after i and before i
    	for(int i = 0;i < r;i++) {
    		Arrays.fill(prev[i], -1);
    		Arrays.fill(next[i], -1);
    		for(int j = (i+1)%r;j!=i;j = (j+1)%r) {
    			char c = ring.charAt(j);
    			if(next[i][c-'a'] == -1)
    				next[i][c-'a'] = j;
    		}
    		
    		for(int j = (i-1+r)%r;i != j;j = (j-1+r)%r) {
    			char c = ring.charAt(j);
    			if(prev[i][c-'a'] == -1)
    				prev[i][c-'a'] = j;
    		}
    		prev[i][ring.charAt(i)-'a'] = next[i][ring.charAt(i)-'a'] = i;
    	}
    	
    	int [][]dp = new int[k][r];
    	int ans = Integer.MAX_VALUE;
    	/* for each character in key, match next index in ring
    	 * calculate minimum distance of key[i] in previous of j (key[i] occurring in ring before j and after j) 
    	 * get minimum
    	 * in answer +k for pushing button 
    	 */
    	for(int i = 0;i < k;i++) {
    		for(int j = 0;j < r;j++) {
    			dp[i][j] = Integer.MAX_VALUE/2;
    			if(key.charAt(i) == ring.charAt(j)) {
    				if(i == 0) {
    					dp[i][j] = Math.min(dp[i][j], dist(0, j, r));
    				} else {
    					char pre = key.charAt(i-1);
    					dp[i][j] = Math.min(dp[i][j], dp[i-1][prev[j][pre-'a']] + dist(prev[j][pre-'a'], j, r));
    					dp[i][j] = Math.min(dp[i][j], dp[i-1][next[j][pre-'a']] + dist(next[j][pre-'a'], j, r));
    				}
    			}
    			if(i == k-1) {
    				ans = Math.min(ans, dp[i][j]);
    			}
    		}
    	}
    	return ans+k;
    }
    
    // minimum distance clockwise and anti-clockwise 
    private int dist(int i, int j, int r) {
    	int d = Math.abs(j-i);
    	d = Math.min(d, r-d);
    	return d;
    }
}

public class Main {
	public static void main(String[] args) {
		String ring = "godding", key = "gd";
		System.out.println(new Solution().findRotateSteps(ring, key));
	}
}
