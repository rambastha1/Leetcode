package main;

import java.util.Arrays;

class Solution {
	/*
	 * Greedy Method
	 * Cannot be applied as there may be smaller denomination that add up to target
	 * while higher ones don't 
	 */
    
	/*public int coinChange(int[] coins, int amount) {
    	if(coins.length == 0 || amount <= 0)
    		return 0;
    	Arrays.sort(coins);
    	int i = coins.length-1, count = 0;
    	while(amount > 0 && i >= 0) {
    		if(amount >= coins[i]) {
    			amount -= coins[i];
    			count++;
    		} else {
    			i--;
    		}
    	}
    	return (amount == 0)?count:-1;
    }*/
    
	/*
     * DP method
     * 0(M*N)
     */
    public int coinChange(int[] coins, int amount) {
    	if (amount < 1) return 0;
        int[] dp = new int[amount + 1]; 
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}

public class Main {

	public static void main(String[] args) {
		/*int []coins = {1,5,5};
		int amount = 11;*/
				
		int []coins = {186,419,83,408};
		int amount = 6249;
		System.out.println(new Solution().coinChange(coins, amount));
	}
}