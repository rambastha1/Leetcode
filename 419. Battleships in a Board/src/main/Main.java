package main;

class Solution {
	/*
	 * Just count the top left ship as there are no adjacent ships
	 */
    public int countBattleships(char[][] board) {
    	int count = 0;
    	for(int i = 0;i < board.length;i++) {
	    	for(int j = 0;j < board[0].length;j++) {
	    		if(board[i][j] == '.') continue;
	    		if(i > 0 && board[i-1][j] == 'X') continue;
	    		if(j > 0 && board[i][j-1] == 'X') continue;
	    		count++;
	    	}
    	}
    	return count;
    }
}

public class Main {
	public static void main(String[] args) {
		char [][]board = {{'X','.','.','X'},{'.','.','.','X'},{'.','.','.','X'}};
		System.out.println(new Solution().countBattleships(board));
	}
}