package main;

// https://leetcode.com/problems/remove-boxes/discuss/101310/Java-top-down-and-bottom-up-DP-solutions
/* When facing this problem, I am keeping thinking how to simulate the case when boxes[i] == boxes[j] 
 * when i and j are not consecutive. It turns out that the dp matrix needs one more dimension to store such state. 
 * So we are going to define the state as

dp[i][j][k] represents the max points from box[i] to box[j] with k boxes whose values equal to box[i]

 * 
 */
class Solution {
	// Time 0(n^4) Space 0(n^3)
    public int removeBoxes(int[] boxes) {
    	int n = boxes.length;
    	int [][][]dp = new int[n][n][n];
    	
    	// diagonal elements of array
    	// cost of removing one element
    	for(int j = 0;j < n;j++) {
    		for(int k = 0;k <= j;k++) {
    			dp[j][j][k] = (k+1)*(k+1);
    		}
    	}
    	
    	for(int len = 1;len < n;len++) {
    		for(int i = 0, j = len;j < n;i++, j++) {
    			for(int k = 0;k <= i;k++) {
    				int res = (k+1)*(k+1) + dp[i+1][j][0];
    				
    				for(int m = i+1;m <= j;m++) {
    					if(boxes[m] == boxes[i]) {
    						res = Math.max(res, dp[i+1][m-1][0] + dp[m][j][k+1]);
    					}
    				}
    				dp[i][j][k] = res;
    			}
    		}
    	}
    	return n == 0?0:dp[0][n-1][0];
    }
}

public class Main {
	public static void main(String[] args) {
		int []boxes = {1, 3, 2, 2, 2, 3, 4, 3, 1};
		System.out.println(new Solution().removeBoxes(boxes));
	}
}
