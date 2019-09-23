package main;

// https://www.geeksforgeeks.org/coin-game-of-two-corners-greedy-approach/
/*
 * Note that this problem is different from Optimal Strategy for a Game Set 1-2. 
 * There the target is to get maximum value. Here the target is to not loose.
 */

class Solution {
	void winner(int []coins) {
		if(coins == null || coins.length == 0)
			return;
		int n = coins.length;
		int oddsum = 0, evensum = 0;
		for(int i = 0;i < n;i += 2)
			evensum += coins[i];
		for(int i = 1;i < n;i += 2)
			oddsum += coins[i];
		if(evensum >= oddsum)
			for(int i = 0;i < n;i += 2)
				System.out.print(coins[i] + " ");
		else
			for(int i = 1;i < n;i += 2)
				System.out.print(coins[i] + " ");
		
		System.out.println();
	}
}

public class Main {
	public static void main(String[] args) {
		//int coins[] = { 8, 15, 3, 7 };
		//int coins[] = { 2, 2, 2, 2 };
		int coins[] = { 20, 30, 2, 2, 2, 10 };
		new Solution().winner(coins);
	}
}