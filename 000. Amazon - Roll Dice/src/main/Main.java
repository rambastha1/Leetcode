package main;

/* https://leetcode.com/discuss/interview-question/331158
 * A six-sided die is a small cube with a different number of pips on each face (side), ranging from 1 to 6.
 * On any two opposite side of the cube, the number of pips adds up to 7; 
 * that is, there are three pairs of opposite sides: 1 and 6, 2 and 5, and 3 and 4.
 * There are N dice lying on a table, each showing the pips on its top face. 
 * In one move, you can take one die and rotate it to an adjacent face.
 * For example, you can rotate a die that shows 1 s that it shows 2, 3, 4 or 5. 
 * However, it cannot show 6 in a single move, because the faces with one pip and six pips visible are 
 * opposite sides rather than adjacent.
 * You want to show the same number of pips on the top face of all N dice. 
 * Given that each of the dice can be moved multiple times, count the minimum number of moves needed to get equal faces.
 * Write a function that, given an array A consisting of N integers describing the number of pips (from 1 to 6) 
 * shown on each die's top face, returns the minimum number of moves necessary for each die show the same number of pips.
 */

class Solution {
	
	private int dist(int a, int b) {
		if(a == b)
			return 0;
		if(a+b == 7)
			return 2;
		return 1;
	}
	
	public int minMoves(int[] A){
		int ans = Integer.MAX_VALUE;
		// minimum number of moves to get i
		// check all outcomes - 1,2,3,4,5,6
		for(int i = 1;i < 7;i++) {
			int currmove = 0;
			for(int move : A)
				currmove += dist(move, i);
			ans = Math.min(ans, currmove);
		}
		return ans;
	}
}

public class Main {
	public static void main(String[] args) {
		//int []A = {1, 2, 3};
		//int []A = {1, 1, 6};
		int []A = {1, 6,2,3};
		System.out.println(new Solution().minMoves(A));
	}
}
