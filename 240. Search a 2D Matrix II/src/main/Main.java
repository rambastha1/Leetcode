package main;

class Solution {
	
	int max = 0;
    public boolean searchMatrix(int[][] matrix, int target) {
    	if(matrix.length == 0 || matrix[0].length == 0)
    		return false;
    	int i = matrix.length-1, j = 0;
    	while(i >= 0 && j < matrix[0].length) {
    		if(matrix[i][j] == target)
    			return true;
    		if(matrix[i][j] < target)
    			j++;
    		else
    			i--;
    	}
    	return false;
    }
}

public class Main {

	public static void main(String[] args) {
		int [][]matrix = { {1,   4,  7, 11, 15},
						   {2,   5,  8, 12, 19},
						   {3,   6,  9, 16, 22},
						   {10, 13, 14, 17, 24},
						   {18, 21, 23, 26, 30} };
		
		System.out.println(new Solution().searchMatrix(matrix, 5));
	}
}