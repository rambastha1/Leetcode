package main;

/* https://leetcode.com/articles/predict-the-winner/?page=1
 * If the current turn belongs to, say Player 1, we pick up an element, say xx, 
 * from either end, and give the turn to Player 2. Thus, if we obtain the score for the 
 * remaining elements(leaving xx), this score, now belongs to Player 2. 
 * Thus, since Player 2 is competing against Player 1, this score should be subtracted from 
 * Player 1's current(local) score(xx) to obtain the effective score of Player 1 at the 
 * current instant.
 * 
 * Similar argument holds true for Player 2's turn as well i.e. we can subtract 
 * Player 1's score for the remaining subarray from Player 2's current score to 
 * obtain its effective score. By making use of this observation, we can omit the 
 * use of turnturn from winner to find the required result by making the slight change 
 * discussed above in the winner's implementation.
 */

class Solution {
	/*
	 * dp[i] stores difference in scores of player1 - player2
	 */
	public boolean PredictTheWinner(int[] nums) {
		if(nums == null || nums.length == 0)
			return true;
		int n = nums.length;
		int []dp = new int[n];
		for(int s = n-1;s >= 0;s--) {
			dp[s] = nums[s];
			for(int e = s+1;e < n;e++) {
				//equations are derived from above comment
				/* in [x,y]
				 * say plr1 picks x plr2 can pick [x+1] or [y] so diff = subtract two values
				 */
				int firstplr = nums[s] - dp[e];
				int secplr = nums[e] - dp[e-1];
				dp[e] = Math.max(firstplr, secplr);
			}
		}
		return dp[n-1]>=0;
	}
	
	//2 D solution
	public boolean PredictTheWinner1(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        for (int s = nums.length-1; s >= 0; s--) {       
            dp[s][s] = nums[s];
            for (int e = s+1; e < nums.length; e++) {
                int a = nums[s] - dp[s + 1][e];
                int b = nums[e] - dp[s][e - 1];
                dp[s][e] = Math.max(a, b);
            }
        }
        return dp[0][nums.length - 1] >= 0;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1, 5, 233, 7};
		System.out.println(new Solution().PredictTheWinner(nums));
	}
}