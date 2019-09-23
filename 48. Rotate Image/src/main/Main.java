package main;

class Solution {
	
	public void transpose(int[][] matrix, int m, int n) {
		for(int i = 0;i < m;i++) {
			for(int j = i;j < n;j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
		print(matrix, m, n);
		System.out.println();
	}
	
    public void rotate(int[][] matrix) {
    	transpose(matrix, matrix.length, matrix.length);
    	reverse_row(matrix, matrix.length, matrix.length);
    }
    
    public void reverse_row(int [][]matrix, int m, int n) {
    	int i = 0;
    	while(i < m) {
    		int start = 0, end = n-1;
    		while(start < end) {
	    		int temp = matrix[i][start];
	    		matrix[i][start] = matrix[i][end];
	    		matrix[i][end] = temp;
	    		start++;
	    		end--;
    		}
    		i++;
    	}
    }
    
    public void print(int [][]matrix, int m, int n) {
    	for(int i = 0;i < m;i++) {
    		for(int j = 0;j < n;j++) {
    			System.out.print(matrix[i][j] + " ");
    		}
    		System.out.println();
    	}
    }
}

public class Main {

	public static void main(String[] args) {
		int [][]matrix = {	{1, 2, 3},
							{4, 5, 6},
							{7, 8, 9}					
						 };
		Solution s = new Solution();
		s.print(matrix, matrix.length, matrix.length);
		System.out.println();
		s.rotate(matrix);
		s.print(matrix, matrix.length, matrix.length);
	}
}