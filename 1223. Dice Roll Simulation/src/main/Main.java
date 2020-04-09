package main;

import java.util.Arrays;
// https://leetcode.com/problems/dice-roll-simulation/discuss/403638/simple-java-2d-DP-solution-with-detailed-explaination-and-comments
// https://leetcode.com/problems/dice-roll-simulation/discuss/403756/Java-Share-my-DP-solution
class Solution {
    public int dieSimulator(int n, int[] rollMax) {
    	// dp[i][j] number of valid combination with i number of rolls and dice end with j+1 value.
        // dp[i][6] total number of valid combinations with i number of rolls.
    	long [][]dp = new long[n+1][7];
    	Arrays.fill(dp[1], 1);
    	// store total sum of sequence with i rolls at dp[i][6]
    	// when there is 0 rolls there is only one combination (empty)
    	dp[0][6] = 1;
    	// total number with 1 roll; 
    	dp[1][6] = 6;
    	int mod = (int)1e9 + 7;
    	long total = 0, totalendwithcurrnum = 0;
    	
    	for(int i = 2;i <= n;i++) {
    		total = 0;
    		for(int j = 0;j < 6;j++) {
    			totalendwithcurrnum = 0;
    			// based on max allowed occurence to calculate the possible combinations end with j + 1
    			for(int k = 1;k <= rollMax[j] && i >= k;k++) {
    				// how many valid combination will end with k succsccive rolls with value of j + 1? 
					// dp[i-k][rollMax.length] - dp[i-k][j]  is number of combinations that i-k rolls have that does not end with j+1
                    // the rest k rolls with have consistent value of j + 1
    				totalendwithcurrnum += (dp[i-k][6] - dp[i-k][j] + mod)%mod;
    				totalendwithcurrnum %= mod;
    			}
    			total = (total + totalendwithcurrnum)%mod;
    			dp[i][j] = totalendwithcurrnum;
    		}
    		dp[i][6] = total;
    	}
    	return (int)total;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 2;
		int []rollMax = {1,1,2,2,2,3};
		System.out.println(new Solution().dieSimulator(n, rollMax));
	}
}
