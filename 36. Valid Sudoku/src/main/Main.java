package main;

import java.util.HashSet;
import java.util.Set;

class Solution {
    
	//Using Extra Space
	/*public boolean isValidSudoku(char[][] board) {
    	Set seen = new HashSet();
        for (int i=0; i<9; ++i) {
            for (int j=0; j<9; ++j) {
                char number = board[i][j];
                if (number != '.')
                    if (!seen.add(number + " in row " + i) ||
                        !seen.add(number + " in column " + j) ||
                        !seen.add(number + " in block " + i/3 + "-" + j/3))
                        return false;
            }
        }
        return true;
    }*/
    
	//Constant Space
    public boolean isValidSudoku(char[][] board) {
        
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				char a = board[i][j];
				if (Character.valueOf('.').equals(a)) {
					continue;
				}

				// check horizontal
				for (int k = j + 1; k < 9; k++) {
					if (a == board[i][k]) {
						return false;
					}
				}

				// check vertical
				for (int k = i + 1; k < 9; k++) {
					if (a == board[k][j]) {
						return false;
					}
				}

				// check 3x3
				for (int ki = 3 * (i / 3); ki < 3 + 3* (i / 3); ki++) {
					for (int kj = 3 * (j / 3); kj < 3 + 3 * (j / 3); kj++) {
						if (ki == i && kj == j) 
							continue;
						if (a == board[ki][kj]) {
							return false;
						}
					}
				}
				
			}
		}
		
		return true;
    }
    
}

public class Main {

	public static void main(String[] args) {
		char [][] board = {
				{'5','3','.','.','7','.','.','.','.'},
				{'6','.','.','1','9','5','.','.','.'},
				{'.','9','8','.','.','.','.','6','.'},
				{'8','.','.','.','6','.','.','.','3'},
				{'4','.','.','8','.','3','.','.','1'},
				{'7','.','.','.','2','.','.','.','6'},
				{'.','6','.','.','.','.','2','8','.'},
				{'.','.','.','4','1','9','.','.','5'},
				{'.','.','.','.','8','.','.','7','9'}};
		
		System.out.println(new Solution().isValidSudoku(board));
	}
}