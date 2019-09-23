package main;

// https://leetcode.com/problems/valid-tic-tac-toe-state/discuss/117580/Straightforward-Java-solution-with-explaination
class Solution {
    public boolean validTicTacToe(String[] board) {
    	int turn = 0;
    	int []row = new int[3], col = new int[3];
    	int diag = 0, antidiag = 0;
    	
    	for(int i = 0;i < 3;i++) {
    		for(int j = 0;j < 3;j++) {
    			if(board[i].charAt(j) == 'X') {
    				turn++;
    				row[i]++;
    				col[j]++;
    				if(i==j)
    					diag++;
    				if(i+j==2)
    					antidiag++;
    			} else if(board[i].charAt(j) == 'O') {
    				turn--;
    				row[i]--;
    				col[j]--;
    				if(i==j)
    					diag--;
    				if(i+j==2)
    					antidiag--;
    			}
    		}
    	}
    	
    	boolean xwin = false, ywin = false;
    	xwin = row[0]==3 || row[1]==3 || row[2]==3 || col[0]==3 || col[1] == 3 || col[2] == 3 || diag == 3 || antidiag == 3;
    	ywin = row[0]==-3 || row[1]==-3 || row[2]==-3 || col[0]==-3 || col[1]==-3 || col[2]== -3 || diag == -3 || antidiag == -3;
    	if((turn != 1 && xwin) || (turn != 0 && ywin))
    		return false;
    	return (turn == 0 || turn == 1);
    }
}

public class Main {
	public static void main(String[] args) {
		//String []board = {"XOX", " X ", "   "};
		//String []board = {"XXX", "   ", "OOO"};
		String []board = {"XOX", "O O", "XOX"};
		System.out.println(new Solution().validTicTacToe(board));
	}
}
