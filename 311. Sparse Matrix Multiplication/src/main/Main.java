package main;

/* Given two sparse matrices A and B, return the result of AB.
 * You may assume that A's column number is equal to B's row number.
 * Example:
 * A = [ 
 * 		[ 1, 0, 0],
 * 		[-1, 0, 3]]
 * 
 * B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]]
  
     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
 * 
 */


class Solution {
	public int[][] multiply(int[][] A, int[][] B) {
    	int [][]res = new int[A.length][B[0].length];
    	for(int i = 0;i < A.length;i++) {
    		for(int j = 0;j < A[0].length;j++) {
    			if(A[i][j] == 0)
    				continue;
    			
    			for(int k = 0;k < B[0].length;k++) {
    				//Different column of Matrix B 
    				res[i][k] += A[i][j] * B[j][k]; 
    			}	
    		}
    	}   	
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][] A = {{1, 0, 0}, {-1, 0, 3}};
		int [][] B = {{7, 0, 0}, {0, 0, 0}, {0, 0, 1}};
		int [][]res = new Solution().multiply(A, B);
		for(int i = 0;i < res.length;i++) {
			for(int j = 0;j < res[i].length;j++) {
				System.out.print(res[i][j] + " ");
			}
			System.out.println();
		}
	}
}