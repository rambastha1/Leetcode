package main;

class Solution {
    public void solveSudoku(char[][] board) {
    	if(board == null || board.length == 0)
            return;
    	boolean [][]row = new boolean[9][10];
    	boolean [][]col = new boolean[10][9];
    	for(int i = 0;i < 9;i++) {
    		for(int j = 0;j < 9;j++) {
    			if(board[i][j] == '.')
    				continue;
    			int num = board[i][j] - '0';
    			row[i][num] = true;
    			col[num][j] = true;
    		}
    	}
    	dfs(board, row, col);
    	print(board);
    }
    
    private boolean dfs(char [][]board, boolean [][]row, boolean [][]col) {
    	for(int i = 0;i < 9;i++) {
    		for(int j = 0;j < 9;j++) {
    			if(board[i][j] == '.') {
    				for(char val = '1';val <= '9';val++) {
    					if(check(board, i, j, val, row, col)) {
    						board[i][j] = val;
    						row[i][val-'0'] = true;
    						col[val-'0'][j] = true;
    						if(dfs(board, row, col))
    							return true;
    						board[i][j] = '.';
    						row[i][val-'0'] = false;
    						col[val-'0'][j] = false;
    					}
        			}
    			}
    		}
    	}
    	return false;
    }
    
    private boolean check(char [][]board, int x, int y, char val, boolean [][]row, boolean [][]col) {
    	for(int j = 0;j < 9;j++)
    		if(board[x][j] == val || row[x][val-'0'])
    			return false;
    	
    	for(int i = 0;i < 9;i++)
    		if(board[i][y] == val || col[val-'0'][y])
    			return false;
    	for(int i = 3*(x/3); i< 3 + 3*(x/3);i++) {
    		for(int j = 3*(y/3);j < 3 + 3*(y/3);j++) {
    			if(i == x && j == y || board[i][j] == '.')
    				continue;
    			if(board[i][j] == val)
    				return false;
    		}
    	}
    	return true;
    }
    
    void print(char [][]board) {
    	for(int i = 0;i < 9;i++) {
    		for(int j = 0;j < 9;j++) {
    			System.out.print(board[i][j] + " ");
    		}
    		System.out.println();
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		
	}
}
