package main;

// https://www.geeksforgeeks.org/optimal-strategy-for-a-game-dp-31/
// https://www.geeksforgeeks.org/optimal-strategy-for-a-game-set-2/ Understand
// fills like matrix chain multiplication 

/* There are always two ways to predict winner
 * Use Minimax Algorithm or this.
 */


class Solution {
	
	/* Difference between scores of two players
	 * If question is to predict if first player is winner, just use final difference >= 0 
	 */
	int difference(int arr[]) {
		int n = arr.length;
		int []dp = new int[n];
		for(int s = n-1;s >= 0;s--) {
			dp[s] = arr[s]; 
			for(int e = s+1;e < n;e++) {
				int a = arr[s] - dp[e];
				int b = arr[e] - dp[e-1];
				dp[e] = Math.max(a, b);
			}
		}
		return dp[n-1];
	} 
	
	// This is maximum value of one player
	int optimalStrategyOfGame(int arr[]) {
		int n = arr.length;
		int [][]dp = new int[n][n];
		for(int gap = 0;gap < n;gap++) {
			
			 /* Player whose turn is picks i or j
			 * x is when player picks i and other player picks i+1 
			 * y is when player picks i and other player picks j or 
			 * player picks j and other player picks i either case value is same 
			 * z is when player picks j and other player picks j-1  
			 */
			
			for(int i = 0,j = gap;j < n;i++, j++) {
				
				int x = (i+2<=j)?dp[i+2][j]:0;
				int y = (i+1 <=j-1)?dp[i+1][j-1]:0;
				int z = (i<=j-2)?dp[i][j-2]:0;
				dp[i][j] = Math.max(arr[i] + Math.min(x, y), arr[j] + Math.min(y, z));
			}
 		}
		return dp[0][n-1];
	}
}

public class Main {
	public static void main(String[] args) {
		int []arr = {8,15,3,7};
		//int arr[] = { 2, 2, 2, 2 };
		//int arr[] = { 20, 30, 2, 2, 2, 10 };
		System.out.println(new Solution().optimalStrategyOfGame(arr));
		System.out.println(new Solution().difference(arr));
	}
}