package main;

// https://www.geeksforgeeks.org/find-water-in-a-glass/

class Solution {
	
	public double champagneTower(int poured, int query_row, int query_glass) {
    	// number of columns in row r is r+1
    	double []dp = new double[query_row + 2];
    	dp[0] = poured;
    	for(int i = 1;i <= query_row;i++) {
    		// if want to use from j = 0 to i, use curr array update using dp and dp = curr
    		// difficult from j = 0 to i
    		for(int j = i;j >= 0;j--) {
    		/* In pascal triangle dp[j] += dp[j-1] + dp[j];
    		 * Here, it there is excess at previous row, then dp[j] for curr row 
    		 * will be temp. dp[j+1] will be dp[j+1]+dp[j] based on above equation
    		 * if no excess for current row dp[j] = 0 and dp[j+1] = dp[j+1] + 0 = dp[j+1] 
    		 */
    			if(dp[j] > 1.0) {
    				double temp = (dp[j]-1)/2;
    				dp[j] = temp;
    				dp[j+1] += dp[j];
    			} else {
    				dp[j] = 0;
    			}
    		}
    	}
    	return Math.min(dp[query_glass], 1.0);
    }
}

public class Main {
	public static void main(String[] args) {
		int poured = 4, query_glass = 2, query_row = 1;
		System.out.println(new Solution().champagneTower(poured, query_row, query_glass));
	}
}
