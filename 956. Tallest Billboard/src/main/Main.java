package main;

import java.util.Arrays;

/* suppose {1,2,3,4,5,6}
 * we need to divide these elements into two groups with equal sum and sum should maximum as possible
 * we can omit some elements. let elements omitted multiplied by 0, set1 by 1 and set2 by -1
 * as both subsets sum sum should be equal, overall sum should be 0.
 * j can go from -sum (all multiplied by -1) to sum (all by 1) 
 * this question is to find maximum subset1 sum possible
 * https://leetcode.com/problems/tallest-billboard/discuss/204232/Simplest-DP-solution-which-is-easy-to-understand
 * 
 */

class Solution {
    public int tallestBillboard(int[] rods) {
    	int n = rods.length;
    	if(n == 0)
    		return 0;
    	
    	int sum = 0;
    	for(int a : rods)
    		sum += a;
    	/* dp[i][j] is largest subset1 sum we can achieve using 0-i rods, subset2 sum = j-subset1 sum
    	 * largest sum of left at i-th rod and difference between sum of left and sum of right equals to j-sum
    	 */
    	int [][]dp = new int[n+1][2*sum+1];
    	for(int i = 0;i <= n;i++)
    		// -1 means the value could not be reached.
    		Arrays.fill(dp[i], -1);
    	
    	//it means if there  is no rods, the  largest left sum could be 0, not -1.
    	dp[0][sum] = 0;
    	for(int i = 1;i <= n;i++) {
    		for(int j = 0;j <= 2*sum;j++) {
    			// we can add this rod to left sum thus addition
    			//this means we will add next rod (rods[i-1] to the left, 
				//so the largest left sum should be added by rods[i-1] from previous step
    			if(j - rods[i-1] >= 0 && dp[i-1][j-rods[i-1]] != -1)
    				dp[i][j] = Math.max(dp[i][j], dp[i-1][j-rods[i-1]] + rods[i-1]);
    			
    			// we can add this rod to right sum thus left sum doesn't change
    			//this means we will add next rod(rods[i-1]) to the right, 
				//so largest left sum at previous step stays at dp[i-1][j+rods[i-1]]
    			if(j + rods[i-1] <= 2*sum && dp[i-1][j+rods[i-1]] != -1)
    				dp[i][j] = Math.max(dp[i][j], dp[i-1][j+rods[i-1]]);
    			
    			//this means we don't use rods[i-1] but we need ensure 
				//previous step could be reached, so we can compare.
    			if(dp[i-1][j] != -1)
    				dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
    		}
    	}
    	return dp[n][sum];
    }
}

public class Main {
	public static void main(String[] args) {
		int []rods = {1,2,3,4,5,6};
		System.out.println(new Solution().tallestBillboard(rods));
	}
}
