package main;

class Solution {
	
	public void print(int [][]board) {
		for(int []a : board) {
			for(int i : a)
				System.out.print(i + " ");
			System.out.println();
		}
	}
	
	public void gameOfLife(int[][] board) {
        if(board == null || board.length == 0)
        	return;
        
        int M = board.length, N = board[0].length;
        for(int i = 0;i < M;i++) {
        	for(int j = 0;j < N;j++) {
        		int lives = numLives(board, M, N, i, j);
        		if(board[i][j] == 1 && (lives >= 2 && lives <= 3))
        				board[i][j] = 3;
        		if(board[i][j] == 0 && lives == 3)
        			board[i][j] = 2;
        	}
        }
        
        for(int i = 0;i < M;i++) {
        	for(int j = 0;j < N;j++)
        		board[i][j] >>= 1;
        }
        print(board);
    }
	
	public int numLives(int[][] board, int M, int N, int i, int j) {
		int lives = 0;
		for(int x = Math.max(i-1, 0);x <= Math.min(i+1, M-1);x++) {
			for(int y = Math.max(j-1, 0);y <= Math.min(j+1, N-1);y++) {
				lives += board[x][y] & 1;
			}
		}
		lives -= board[i][j] & 1;
		return lives;
    }
}

public class Main {

	public static void main(String[] args) {
		int [][]board = { {0,1,0},
				{0,0,1},
				{1,1,1},
				{0,0,0}
		};
		new Solution().gameOfLife(board);
	}
}