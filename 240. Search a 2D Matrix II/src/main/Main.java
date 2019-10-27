package main;

class Solution {
	
	public boolean searchMatrix(int[][] a, int target) {
        if(a == null || a.length == 0) return false;
        
        for(int i = 0, j = a[0].length - 1; i < a.length && j >= 0; ) {
            if(a[i][j] > target) j--;
            else if(a[i][j] < target) i++;
            else return true;
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