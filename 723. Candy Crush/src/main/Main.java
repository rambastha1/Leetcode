package main;

//http://shibaili.blogspot.com/2019/01/723-candy-crush.html

class Solution {
    public int[][] candyCrush(int[][] board) {
        boolean found = true;
        int row = board.length, col = board[0].length;
         
        while (found) {
            found = false;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col - 2; j++) {
                    int val = Math.abs(board[i][j]);
                    if (val != 0 && Math.abs(board[i][j + 1]) == val && Math.abs(board[i][j + 2]) == val) {
                        board[i][j] = board[i][j + 1] = board[i][j + 2] = -val;
                        found = true;
                    }
                }
            }
             
            for (int j = 0; j < col; j++) {
                for (int i = 0; i < row - 2; i++) {
                    int val = Math.abs(board[i][j]);
                    if (val != 0 && Math.abs(board[i + 1][j]) == val && Math.abs(board[i + 2][j]) == val) {
                        board[i][j] = board[i + 1][j] = board[i + 2][j] = -val;
                        found = true;
                    }
                }
            }
             
            for (int j = 0; j < col; ++j) {
                int temp = row - 1;
                for (int i = row-1; i >= 0; --i)
                    if (board[i][j] > 0)
                        board[temp--][j] = board[i][j];
                while (temp >= 0)
                    board[temp--][j] = 0;
            }
        }
        print(board); 
        return board;
    }
    
    void print(int [][]board) {
    	for(int i = 0;i < board.length;i++) {
    		for(int j = 0;j < board[0].length;j++)
    			System.out.print(board[i][j] + " ");
    		System.out.println();
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]board = {{110,5,112,113,114},
				         {210,211,5,213,214},
				         {310,311,3,313,314},
				         {410,411,412,5,414},
				         {5,1,512,3,3},
				         {610,4,1,613,614},
				         {710,1,2,713,714},
				         {810,1,2,1,1},
				         {1,1,2,2,2,},
				         {4,1,4,4,1014}};
		Solution s = new Solution();
		board = s.candyCrush(board);
	}
}