package main;

class Solution {
	
	void print(char [][]board) {
		for(int i = 0;i < board.length;i++) {
			for(int j = 0;j < board[0].length;j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
    public char[][] updateBoard(char[][] board, int[] click) {
    	if(board[click[0]][click[1]] == 'M') {
    		board[click[0]][click[1]] = 'X';
    		return board;
    	}
    	dfs(board, click[0], click[1]);
    	print(board);
    	return board;
    }
    
    void dfs(char [][]board, int x, int y) {
    	if(!issafe(board, x, y) || board[x][y] != 'E')
    		return;
    	
    	if(board[x][y] == 'M') {
			board[x][y] = 'X';
			return;
		} 		
    	
    	int []x_path = {-1,-1,-1,0,0,1,1,1};
    	int []y_path = {-1,0,1,-1,1,-1,0,1};
    	int mines = 0;
    	for(int i = 0;i < 8;i++) {
			int X = x + x_path[i];
			int Y = y + y_path[i];
			if(issafe(board, X, Y) && board[X][Y] == 'M')
				mines++;
    	}
    	if(mines > 0)
    		board[x][y] = (char) ('0' + mines);
    	else {
    		board[x][y] = 'B';
    		for(int i = 0;i < 8;i++) {
    			int X = x + x_path[i];
    			int Y = y + y_path[i];
    			dfs(board, X, Y);
    		}
    	}
    }
    
    boolean issafe(char [][]board, int x,int y) {
    	return (x >= 0 && x < board.length && y >= 0 && y < board[0].length);    			
    }
}

public class Main {
	public static void main(String[] args) {
		/*char [][]board = {{'E', 'E', 'E', 'E', 'E'},
						  {'E', 'E', 'M', 'E', 'E'},
						  {'E', 'E', 'E', 'E', 'E'},
						  {'E', 'E', 'E', 'E', 'E'}};*/
		
		char [][]board = {{'B','1','E','1','B'},
						  {'B','1','M','1','B'},
						  {'B','1','1','1','B'},
						  {'B','B','B','B','B'}};
				
		int []click = {1,2};
		new Solution().updateBoard(board, click);

	}
}