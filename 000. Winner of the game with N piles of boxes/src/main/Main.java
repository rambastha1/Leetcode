package main;

// https://www.geeksforgeeks.org/find-the-winner-of-the-game-with-n-piles-of-boxes/

class Solution {
	int winner(String []piles, int player) {
		int n = piles.length;
		int a = 0, b = 0;
		for(String pile : piles) {
			a += pile.charAt(0) == 'W'?1:0;
			b += pile.charAt(0) == 'B'?1:0;
		}
		return (a<=b)?player:1^player;
	}
}

public class Main {
	public static void main(String[] args) {
		//String piles[] = {"WBW", "BWB"};
		String piles[] = {"WWWW", "WBWB", "WBBW"};
		System.out.println(new Solution().winner(piles, 0));
	}
}