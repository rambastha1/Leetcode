package main;

import java.util.Arrays;
// https://leetcode.com/problems/stickers-to-spell-word/discuss/108334/Explaining-StefanPochmann's-Rewrite-of-contest-winner's-solution-and-%2Bjava
class Solution {
	
	// Time 0(m * n * 2^m) Space 0(2^m)
    public int minStickers(String[] stickers, String target) {
    	// each subset of target
    	int m = target.length(), n = (1<<m);
    	int []dp = new int[n];
    	Arrays.fill(dp, Integer.MAX_VALUE);
    	// number of stickers required to get empty set
    	dp[0] = 0;
    	
    	// which index can be formed by i 
    	for(int i = 0;i < n;i++) {
    		if(dp[i] == Integer.MAX_VALUE)
    			continue;
    		for(String sticker : stickers) {
    			int subset = i;
    			for(char c : sticker.toCharArray()) {
    				for(int r = 0;r < m;r++) {
    					if(target.charAt(r) == c && ((subset >> r) & 1) == 0) {
    						// subset increases
    						subset |= (1<<r);
    						break;
    					}
    				}
    			}
    			dp[subset] = Math.min(dp[subset], dp[i] + 1);
    		}
    	}
    	return dp[n-1] == Integer.MAX_VALUE?-1:dp[n-1];
    }
}

public class Main {
	public static void main(String[] args) {
		String []stickers = {"with", "example", "science"};
		String target = "thehat";
		System.out.println(new Solution().minStickers(stickers, target));
	}
}
