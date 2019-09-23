package main;

class Solution {
	
	class Move {
		int row, col;
		public Move(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	//Time 
	Move findBestMove(char [][]board) {
		int n = board.length;
		int bestVal = -1000;
		Move bestmove = new Move(-1, -1);
		for(int i = 0;i < 3;i++) {
			for(int j = 0;j < 3;j++) {
				if(board[i][j] == '_') {
					board[i][j] = 'X';
					int moveval = minimax(board, 0, false);
					board[i][j] = '_';
					if(moveval > bestVal) {
						bestmove.row = i;
						bestmove.col = j;
						bestVal = moveval;
					}
				}
			}
		}
		System.out.println(bestmove.row + " " + bestmove.col);
		System.out.println("bestval: " + bestVal);
		//print(board);
		return bestmove;
	}
	
	
	int minimax(char [][]board, int depth, boolean ismax) {
		int n = board.length;
		int score = evaluate(board);
		
		if(score == 10)
			return score - depth;
		
		if(score == -10)
			return score + depth;
		
		if(!ismovesleft(board))
			return 0;
		
		if(ismax) {
			int best = -1000;
			for(int i = 0;i < 3;i++) {
				for(int j = 0;j < 3;j++) {
					if(board[i][j] == '_') {
						board[i][j] = 'X';
						best = Math.max(best, minimax(board, depth+1, !ismax));
						board[i][j] = '_';
					}
						
				}
			}
			return best;
		} else {
			int best = 1000;
			for(int i = 0;i < 3;i++) {
				for(int j = 0;j < 3;j++) {
					if(board[i][j] == '_') {
						board[i][j] = 'O';
						best = Math.min(best, minimax(board, depth+1, !ismax));
						board[i][j] = '_';
					}
				}
			}
			return best;
		}
	}
	
	
	boolean ismovesleft(char [][]board) {
		int n = board.length;
		for(int i = 0;i < 3;i++) {
			for(int j = 0;j < 3;j++)
				if(board[i][j] == '_')
					return true;
		}
		return false;
	}
	
	int evaluate(char [][]board) {
		for(int row = 0;row < 3;row++) {
			if(board[row][0] == board[row][1] && board[row][0] == board[row][2]) {
				return board[row][0] == 'X'?10:-10;
			}
		}
		
		for(int col = 0;col < 3;col++) {
			if(board[0][col] == board[1][col] && board[0][col] == board[2][col]) {
				return board[0][col] == 'X'?10:-10;
			}
		}
		
		if(board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
			return board[0][0] == 'X'?10:-10;
		}
		
		if(board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return board[0][2] == 'X'?10:-10;
		}
		
		return 0;
	}
	
	void print(char [][]board) {
		for(int i = 0;i < 3;i++) {
			for(int j = 0;j < 3;j++)
				System.out.print(board[i][j] + " ");
			System.out.println();
		}
	}
	
}

public class Main {
	public static void main(String[] args) {
		char [][]board =  { { 'X', 'O', 'X' }, { 'O', 'O', 'X' }, { '_', '_', '_' } };
		Solution s = new Solution();
		Solution.Move move = s.findBestMove(board);
	}
}