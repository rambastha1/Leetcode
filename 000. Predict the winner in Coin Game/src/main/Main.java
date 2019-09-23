package main;

// https://www.geeksforgeeks.org/predict-the-winner-in-coin-game/

class Solution {
	int winner(int []piles, int player) {
		if(piles == null || piles.length == 0)
			return -1;
		for(int pile : piles)
			if(pile%2==0)
				return player;
		return player^1;
	}
}

public class Main {
	public static void main(String[] args) {
		int []piles= {4,4};
		System.out.println(new Solution().winner(piles, 0));
	}
}