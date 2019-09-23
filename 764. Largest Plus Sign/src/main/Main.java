package main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
	
	/* They are the mines as explained in the problem description.
	 * They represent 0's in the matrix which is filled by all 1's.
	 * Suppose if the input is N=3 and Mines=[[1,1]]
	 * Then matrix will look like this.
	 * 111
	 * 101
	 * 111
	 * Then banned will contain the [1,1] which is converted into 1D index as 4 (3*1+1)
	 */
	
	public int orderOfLargestPlusSign(int N, int[][] mines) {
        Set<Integer> banned = new HashSet();
        int[][] dp = new int[N][N];
        
        for (int[] mine: mines)
            banned.add(mine[0] * N + mine[1]);
        int ans = 0, count;
        
        for (int r = 0; r < N; ++r) {
        	//left
            count = 0;
            for (int c = 0; c < N; ++c) {
                count = banned.contains(r*N + c) ? 0 : count + 1;
                dp[r][c] = count;
            }
            //right
            count = 0;
            for (int c = N-1; c >= 0; --c) {
                count = banned.contains(r*N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }
        }
        
        for (int c = 0; c < N; ++c) {
        	//up
            count = 0;
            for (int r = 0; r < N; ++r) {
                count = banned.contains(r*N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }
            //down
            count = 0;
            for (int r = N-1; r >= 0; --r) {
                count = banned.contains(r*N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
                ans = Math.max(ans, dp[r][c]);
            }
        }
        
        return ans;
    }
	
	// Time 0(N^2) Space 0(N^2)
	// Need four auxiliary matrices
    public int orderOfLargestPlusSign1(int N, int[][] mines) {
    	int [][]matrix = new int[N][N];
    	
    	for(int[] mat:matrix){
            Arrays.fill(mat, 1);
        }
        
    	for(int[] mine: mines){
            matrix[mine[0]][mine[1]] = 0;
        }  	
    	
    	int [][]top = new int[N][N], down = new int[N][N];
    	int [][]left = new int[N][N], right = new int[N][N];
    	
    	int ans = 0;
    	if(mines.length < N*N) ans = 1;
    	
    	for(int i = 0;i < N;i++) {
    		for(int j = 0;j < N;j++) {
    			if(matrix[i][j] == 1) {
					top[i][j] = (i>0)?top[i-1][j]+1:1;
					left[i][j] = (j>0)?left[i][j-1]+1:1;
    			}
    		}    		
    	}
    	
    	for(int i = N-1;i >= 0;i--) {
    		for(int j = N-1;j >= 0;j--) {
    			if(matrix[i][j] == 1) {
    				down[i][j] = (i < N-1)?down[i+1][j]+1:1;
    				right[i][j] = (j < N-1)?right[i][j+1]+1:1;
    			}
    			ans = Math.max(ans, Math.min(Math.min(left[i][j],down[i][j]), Math.min(right[i][j],top[i][j])));
    		}
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]mines = {{4,2}};
		int N = 5;
		System.out.println(new Solution().orderOfLargestPlusSign(N, mines));
	}
}
