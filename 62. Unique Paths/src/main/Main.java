package main;

/*
 * This is a combinatorial problem and can be solved without DP. For mxn grid, robot has to move exactly
 * m-1 steps down and n-1 steps right and these can be done in any order.
 * For the eg., given in question, 3x7 matrix, robot needs to take 2+6 = 8 steps with 2 down and 6 right
 * in any order. That is nothing but a permutation problem. Denote down as 'D' and right as 'R',
 * following is one of the path :-
 * D R R R D R R R
 * We have to tell the total number of permutations of the above given word. So, decrease both m & n by
 * 1 and apply following formula:-
 * Total permutations = (m+n)! / (m! * n!)
 */

class Solution {
	
	// Maths method Space 0(1)
	public int uniquePaths(int m, int n) {
        if(m == 1 || n == 1)
            return 1;
        m--;
        n--;
        if(m < n) {              // Swap, so that m is the bigger number
            m = m + n;
            n = m - n;
            m = m - n;
        }
        long res = 1;
        int j = 1;
        for(int i = m+1; i <= m+n; i++, j++){       // Instead of taking factorial, keep on multiply & divide
            res *= i;
            res /= j;
        }
            
        return (int)res;
    }
	
	//DP 0(M*N) Space 0(M*N)
	/*public int uniquePaths(int m, int n) {
	    int[][] grid = new int[m][n];
	    for(int i = 0; i<m; i++){
	        for(int j = 0; j<n; j++){
	            if(i==0||j==0)
	                grid[i][j] = 1;
	            else
	                grid[i][j] = grid[i][j-1] + grid[i-1][j];
	        }
	    }
	    return grid[m-1][n-1];
	}*/
	
	//DP 0(MN) Time and 0(N) space
	//Optimized Solution
    
	/*public int uniquePaths(int m, int n) {
    	int []arr = new int[n];
    	arr[0] = 1;
    	for(int i = 0;i < m;i++) {
    		for(int j = 1;j < n;j++) {
    			arr[j] += arr[j-1];
    		}
    	}
    	return arr[n-1];
    }*/
}

public class Main {

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.uniquePaths(3, 7));
	}
}