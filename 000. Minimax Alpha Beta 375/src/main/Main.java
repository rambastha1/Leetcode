package main;
/* https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-4-alpha-beta-pruning/
 * https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-5-zobrist-hashing/
 */
class Solution {
	int minimax(int []values, int nodeindex, int depth, boolean player, int alpha, int beta) {
		int height = getheight(values);
		if(depth == height)
			return values[nodeindex];
		
		if(player) {
			int best = Integer.MIN_VALUE;
			for(int i = 0;i < 2;i++) {
				int val = minimax(values, nodeindex*2+i, depth+1, !player, alpha, beta);
				best = Math.max(val, best);
				alpha = Math.max(alpha, best);
				
				if(beta <= alpha)
					break;
			}
			return best;
		} else {
			int best = Integer.MAX_VALUE;
			for(int i = 0;i < 2;i++) {
				int val = minimax(values, nodeindex*2+i, depth+1, !player, alpha, beta);
				best = Math.min(val, best);
				beta = Math.min(beta, best);
				
				if(beta <= alpha)
					break;
			}
			return best;
		}
	}
	
	int getheight(int []values) {
		int val = 1;
		int count = 0;
		while(val < values.length) {
			val *= 2;
			count++;
		}
		return count;
	}
}

public class Main {
	public static void main(String[] args) {
		int values[] = {3, 5, 6, 9, 1, 2, 0, -1}; 
		System.out.println(new Solution().minimax(values, 0, 0, true, Integer.MIN_VALUE, Integer.MAX_VALUE));
	}
}