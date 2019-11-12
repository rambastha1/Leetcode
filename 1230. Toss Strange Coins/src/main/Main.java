package main;

/*
 * You have some coins.  The i-th coin has a probability prob[i] of facing heads when tossed.

Return the probability that the number of coins facing heads equals target if you toss every coin exactly once.

 

Example 1:

Input: prob = [0.4], target = 1
Output: 0.40000
Example 2:

Input: prob = [0.5,0.5,0.5,0.5,0.5], target = 0
Output: 0.03125
 

Constraints:

1 <= prob.length <= 1000
0 <= prob[i] <= 1
0 <= target <= prob.length
Answers will be accepted as correct if they are within 10^-5 of the correct answer.
 */

class Solution {
	/* https://leetcode.com/problems/toss-strange-coins/discuss/408485/JavaC%2B%2BPython-DP
	 * dp[c][k] is the prob of tossing c first coins and get k faced up.
	 * dp[c][k] = dp[c - 1][k] * (1 - p) + dp[c - 1][k - 1] * p)
	 * where p is the prob for c-th coin.
	 */
	// Time 0(N^2) Space 0(N)
    public double probabilityOfHeads(double[] prob, int target) {
    	int n = prob.length;
    	double []dp = new double[target + 1];
    	dp[0] = 1.0;
    	for(int i = 0;i < n;i++) {
    		// backward so that doesn't mess with ongoing probability else use temp[] and dp = temp in end for forward
    		for(int j = Math.min(i+1, target);j >= 0;j--) {
    			// j-1 coins jth tail + j-1 coins + jth head
    			dp[j] = dp[j]*(1-prob[i]) + (j>0?dp[j-1]:0)*prob[i];
    		}
    	}
    	return dp[target];
    }
}

public class Main {
	public static void main(String[] args) {
		double []prob = {0.5,0.5,0.5,0.5,0.5};
		int target = 0;
		System.out.println(new Solution().probabilityOfHeads(prob, target));
	}
}