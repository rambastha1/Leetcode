package main;

class Solution {
	
// 39. Combination Sum
// https://leetcode.com/problems/coin-change-2/discuss/99212/Knapsack-problem-Java-solution-with-thinking-process-O(nm)-Time-and-O(m)-Space
	
/* The difference is that if you update dp while:
 * increasing i then the previous partial result dp[i - coin] is the result that 
 * has considered coin already
 * decreasing i then the previous partial result dp[i - coin] is the result 
 * that has not considered coin yet
 */
	
/* In a knapsack problem we try to either maximize or minimize (in a dual problem) 
 * the total value constrained by weight limit. However in this problem we just 
 * calculate the number of possible combinations, which is not an optimization problem. 
 * But this solution is similar to 0/1 knapsack problem indeed.
 * 
 * I think dp[i][0] means how many ways we have to use first i coins to make up the amount of 0 
 * , so it's clear that we can have 1 way that we use none of them.
 */
	

	public int change(int amount, int[] coins) {
		int n = coins.length;
		int []dp = new int[amount+1];
		for(int i = 0;i < n;i++) {
			dp[0] = 1;
			for(int j = coins[i];j <= amount;j++) {
				dp[j] += dp[j - coins[i]];
			}
		}
		return dp[amount];
	}
	
	
    public int change1(int amount, int[] coins) {
    	if(coins == null || coins.length == 0 || amount == 0)
    		return 0;
    	int n = coins.length;    			
    	int [][]dp = new int[n+1][amount+1];
    	dp[0][0] = 1;
    	for(int i = 1;i <= n;i++) {
    		dp[i][0] = 1;
    		for(int j = 1;j <= amount;j++) {
    			dp[i][j] += dp[i-1][j];
    			if(j-coins[i-1] >= 0)
    				dp[i][j] += dp[i][j-coins[i-1]];
    		}
    	}
    	return dp[n][amount];
    }
}

public class Main {
	public static void main(String[] args) {
		int amount = 5; 
		int []coins = {1, 2, 5};
		System.out.println(new Solution().change(amount, coins));
		System.out.println(new Solution().change1(amount, coins));
	}
}
