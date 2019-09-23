package main;
// https://leetcode.com/problems/guess-number-higher-or-lower-ii/discuss/84764/Simple-DP-solution-with-explanation~~
class Solution {
	
	// Optimal method using MCM coding style
	public int getMoneyAmount(int n) {
// dp[i][j] represents the minimum number of money to guarantee a win for the number from i to j
		int [][]dp = new int[n+1][n+1];
		// gap and i are starting position as j = gap
		for(int gap = 2;gap <= n;gap++) {
			for(int i = 1, j = gap;j <= n;i++, j++) {
				if(j < i+1)
					dp[i][j] = 0;
				else {
					dp[i][j] = Integer.MAX_VALUE;
					for(int k = i;k < j;k++) {
						// k is added as k is picked, we pay fee
						int temp = k + Math.max(dp[i][k-1], dp[k+1][j]);
						dp[i][j] = Math.min(dp[i][j], temp);
					}
				}
			}
		}
		return dp[1][n];
	}
	
	// Minimax Algorithm with Top-Down DP with Memoization
// https://leetcode.com/problems/guess-number-higher-or-lower-ii/discuss/278098/Java-Solution-minimax-explained
	// question is to minimum amount from a given amounts
	// similar to maximum amount from given outer max inner min(of both childs)
	public int getMoneyAmount1(int n) {
    	int [][]dp = new int[n+1][n+1];
    	return minimax(dp, 1, n);
    }
    
    public int minimax(int [][]dp, int start, int end) {
    	if(start >= end)
    		return 0;
    	if(dp[start][end] != 0)
    		return dp[start][end];
    	
    	int min = Integer.MAX_VALUE;
    	// plus i is cost of making the guess
    	// we have to minimize the loss its second level from top i.e where we take min 
    	for(int i = start;i <= end;i++) {
    		// max(left child of tree, right child of tree)
    		min = Math.min(min, Math.max(minimax(dp, start, i-1), minimax(dp, i+1, end)) + i);
    	}
    	dp[start][end] = min;
    	return min;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 10;
		System.out.println(new Solution().getMoneyAmount(n));
	}
}
