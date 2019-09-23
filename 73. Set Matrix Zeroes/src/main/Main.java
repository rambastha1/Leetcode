package main;

class Solution {
    public void setZeroes(int[][] matrix) {
    	boolean iscol = false;    		    	
    	
    	for(int i = 0;i < matrix.length;i++) {
    		if(matrix[i][0] == 0)
    			iscol = true;
    		
    		for(int j = 1;j < matrix[0].length;j++) {
    			if(matrix[i][j] == 0) {
    				matrix[i][0] = 0;
    				matrix[0][j] = 0;
    			}
    		}
    	}
    	
    	for(int i = 1;i < matrix.length;i++) {
    		for(int j = 1;j < matrix[0].length;j++) {
    			if(matrix[i][0] == 0 || matrix[0][j] == 0)
    				matrix[i][j] = 0;
    		}
    	}
    	
    	if(matrix[0][0] == 0) {
    		for(int j = 0;j < matrix[0].length;j++)
    			matrix[0][j] = 0;
    	}
    	
    	if(iscol) {
    		for(int i = 0;i < matrix.length;i++)
    			matrix[i][0] = 0;
    	}
    }
    
    
    public void print(int [][]matrix) {
    	for(int []a : matrix) {
    		for(int b : a)
    			System.out.print(b + " ");
    		System.out.println();
    	}
    }
}

public class Main {

	public static void main(String[] args) {
		/*int [][]matrix = {{1,1,1},
				  		  {1,0,0},
				  		  {1,1,1} };*/
		int [][]matrix = {{1,1,1},
		  		  		  {0,1,2} };
		  		  		  //{1,1,1} };
		Solution s = new Solution();
		s.print(matrix);
		s.setZeroes(matrix);
		System.out.println();
		s.print(matrix);
	}
}