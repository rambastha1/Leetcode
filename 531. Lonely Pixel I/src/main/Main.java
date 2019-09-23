package main;

/*
 * Given a picture consisting of black and white pixels, find the number of black lonely pixels.
 * The picture is represented by a 2D char array consisting of  
 * which means black and white pixels respectively.
 * A black lonely pixel is character ‘B’ that located at a specific position where 
 * the same row and same column don’t have any other black pixels.
 * 
 * Example:
 * 
 * Input: 
 * [['W', 'W', 'B'],
 * ['W', 'B', 'W'],
 * ['B', 'W', 'W']]
 * 
 * Output: 3
 */

class Solution {
	
	/*
	 * One Pass Solution
	 * Time 0(M*N) Space 0(M+N)
	 */
	public int findLonelyPixel(char[][] picture) {
		int []row = new int[picture.length], col = new int[picture[0].length];
		int count = 0;
		for(int i = 0;i < picture.length;i++) {
			for(int j = 0;j < picture[0].length;j++) {
				if(picture[i][j] == 'B') {
					// Keeps track of number of 'B' in row i
					row[i]++;
					//If there is first 'B' in column j
					if(col[j] == 0) {
						//why i+1?
						/* col[j] stores the row index from which that column 'j' has 'B'
						 * 
						 */
						col[j] = i+1;
					}
					else {
						//More than 1 'B' in column.None is lonely
						col[j] = -1;
					}
				}
			}
		}
		
		/*
		 * Iterate col array if col[c] = -1 means more than one 'B' in column 
		 * none is lonely
		 * just check in corresponding row for that column
		 * if row count == 1 means only one 'B' in that row thus lonely
		 * This one loop is working because it is sqaure matrix. If not then have to think 
		 */
		
		for(int c = 0;c < col.length;c++) {
			//col[c] - 1 gives the row index, just check in that row if it contains only one 'B'
			if(col[c] > 0 && row[col[c] - 1] == 1)
				count++;
		}
		
		return count;
	}
	
	/* Two pass Solution
	 * Time 0(M*N) Space 0(M+N)
	 * Loop through array store the number of B in row and col arrays
	 * Loop again check if both row and col are 1 
	 */ 
	
	/*public int findLonelyPixel(char[][] picture) {		
		
		
		int []row = new int[picture.length], col = new int[picture[0].length];
		int count = 0;
		for(int i = 0;i < picture.length;i++) {
			for(int j = 0;j < picture[0].length;j++) {
				if(picture[i][j] == 'B') {
					row[i]++;
					col[j]++;
				}
			}
		}
		
		for(int i = 0;i < picture.length;i++) {
			for(int j = 0;j < picture[0].length;j++) {
				//row[i] == 1 && col[j] == 1 shows its the only one in that row and column 
				if(picture[i][j] == 'B' && row[i] == 1 && col[j] == 1)
					count++;
				
			}
		}		
		return count;
	}*/
}

public class Main {
	public static void main(String[] args) {
		char [][]picture = {{'W', 'W', 'B'}, {'W', 'B', 'W'}, {'B', 'W', 'W'}};
		System.out.println(new Solution().findLonelyPixel(picture));
	}
}