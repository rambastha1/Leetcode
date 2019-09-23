package main;

class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
    	if(matrix == null || matrix.length == 0)
    		return new int[] {};
    	int []res = new int[matrix.length*matrix[0].length];
    	int index = 0;
    	int r1 = 0, col1 = 0, r2 = matrix.length-1, col2 = matrix[0].length-1;
    	while(r1 <= r2 && col1 <= col2) {
    		for(;r1 >= 0 && col1 <= col2;r1--,col1++)
    			res[index++] = matrix[r1][col1];
    		r1++;
    		if(col1 > col2) {
    			col1--;
    			r1++;
    		}
    		for(;r1 <= r2 && col1 >= 0;r1++, col1--)
    			res[index++] = matrix[r1][col1];
    		col1++;
    		if(r1 > r2) {
    			r1--;
    			col1++;
    		}
    	}
    	print(res);
    	return res;
    }
    
    void print(int []res) {
    	for(int i : res)
    		System.out.print(i + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
		new Solution().findDiagonalOrder(matrix);
	}
}