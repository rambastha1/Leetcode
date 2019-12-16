package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
	//Time 0(n^3 * n!)
    public List<List<String>> solveNQueens(int n) {
    	char [][]board = new char[n][n];
    	for(char []c : board) {
    		Arrays.fill(c, '.');
    	}
    	List<List<String>> res = new ArrayList<>();
    	backtrack(res, board, 0);
    	return res;
    }
    
    private void backtrack(List<List<String>> res, char [][]board, int col) {
    	if(col == board.length) {
    		res.add(construct(board));
    		return;
    	}
    	
    	for(int i = 0;i < board.length;i++) {
    		if(issafe(board, i, col)) {
    			board[i][col] = 'Q';
    			backtrack(res, board, col+1);
    			board[i][col] = '.';
    		}
    	}
    }
    
    private List<String> construct(char [][]board) {
    	List<String> res = new ArrayList<>();
    	for(int i = 0;i < board.length;i++) {
    		res.add(new String(board[i]));
    	}
    	return res;
    }
    
    private boolean issafe(char[][]board, int x, int y) {
    	for(int i = 0;i < board.length;i++) {
    		for(int j = 0;j < y;j++) {
    			/* slopes of line 
    			 * x == i horizontal
    			 * both diagonals
    			 */
    			if(board[i][j] == 'Q' && (y == -x+i+j || y == x-i+j || x == i))
    				return false;
    		}
    	}
    	return true;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 4;
		System.out.println(new Solution().solveNQueens(n));
	}
}
