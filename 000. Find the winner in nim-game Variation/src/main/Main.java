package main;

// https://www.geeksforgeeks.org/find-winner-nim-game/

class Solution {
	int winner(int []A, int player) {
		if(A == null || A.length == 0)
			return -1;
		int res = A[0];
		for(int i = 1;i < A.length;i++)
			res ^= A[i];
		if(res == 0 || A.length%2 == 0)
			return player;
		return player^1;
	}
}

public class Main {
	public static void main(String[] args) {
		int A[] = {3, 3, 2};
		System.out.println(new Solution().winner(A, 0));
	}
}