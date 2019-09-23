package main;

class Solution {
	
	/*
	 * It is flipping (XOR operation) all bits in char at board[y][x], 
	 * while making sure the flipped bit will not conflict with original chars at board
	 * 256 is the number of all possible extended ASCII chars, 
	 * and here is used as a mask as 11 in below example
     * Like:
     * 01^11 -> 111...10
     * 10^11 -> 111...01
     * XOR a number twice equals no actions taken
     * Time 0(M*N) Constant Space
	 */
	
	public boolean exist(char[][] board, String word) {
	    char[] w = word.toCharArray();
	    for (int x=0; x<board.length; x++) {
	    	for (int y=0; y<board[x].length; y++) {
	    		if (exist(board, x, y, w, 0)) return true;
	    	}
	    }
	    return false;
	}

	private boolean exist(char[][] board, int x, int y, char[] word, int i) {
		if (i == word.length) return true;
		if (x<0 || y<0 || x == board.length || y == board[x].length) return false;
		if (board[x][y] != word[i]) return false;
		board[x][y] ^= 256;
		boolean exist = exist(board, x, y+1, word, i+1) || exist(board, x, y-1, word, i+1)
			|| exist(board, x+1, y, word, i+1) || exist(board, x-1, y, word, i+1);
		board[x][y] ^= 256;
		return exist;
	}
}

public class Main {

	public static void main(String[] args) {
		char [][]board = { {'A','B','C','E'},
						   {'S','F','C','S'},
						   {'A','D','E','E'} };
		
		String word = "ABCCED";
		System.out.println(new Solution().exist(board, word));
	}
}