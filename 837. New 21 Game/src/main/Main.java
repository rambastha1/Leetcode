package main;

// https://leetcode.com/problems/new-21-game/discuss/132503/My-take-on-how-to-reach-at-Solution
class Solution {
    public double new21Game(int N, int K, int W) {
    	if(N >= K+W)
    		return 1.00;
    	double []dp = new double[K+W];
    	double prob = 1/(W+0.00);
    	dp[0] = 1;
    	double prev = 0;

    	/* probability to reach x should be from x-W to x-1
    	 * thus delete x-W-1 from probability and add x-1
    	 */
    	for(int i = 1;i <= K;i++) {
    		prev = prev - (i-W-1 >= 0?dp[i-W-1]:0) + dp[i-1];
    		dp[i] = prev * prob;
    	}
    	
    	double ans = dp[K];
    	/* for x > K, we stop at K, thus don't add new elements
    	 */
    	for(int i = K+1;i<= N;i++) {
    		prev = prev - (i-W-1 >= 0?dp[i-W-1]:0);
    		dp[i] = prev * prob;
    		ans+= dp[i];
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		 int N = 6, K = 1, W = 10;
		 System.out.println(new Solution().new21Game(N, K, W));
	}
}
