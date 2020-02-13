package main;

/* Given an integer array arr, in one move you can select a palindromic subarray arr[i], arr[i+1], ..., arr[j] 
 * where i <= j, and remove that subarray from the given array. Note that after removing a subarray, 
 * the elements on the left and on the right of that subarray move to fill the gap left by the removal.

Return the minimum number of moves needed to remove all numbers from the array.

Example 1:

Input: arr = [1,2]
Output: 2
Example 2:

Input: arr = [1,3,4,1,5]
Output: 3
Explanation: Remove [4] then remove [1,3,1] then remove [5].
 

Constraints:

1 <= arr.length <= 100
1 <= arr[i] <= 20
 * 
 * https://www.geeksforgeeks.org/minimum-steps-to-delete-a-string-after-repeated-deletion-of-palindrome-substrings/
 */

class Solution {
	/* Just like MCM
	 * len = 0 means single digit len = 1 means double digit
	 * if(arr[i] == arr[j] ) -> subproblem i+1 to j-1
	 * For eg. 1231 for 23 -> 2 
	 * for 1231 -> 2
	 * for rest check two MCM value
	 */
    public int minimumMoves(int[] arr) {
    	int n = arr.length;
    	int [][]dp = new int[n][n];
    	
    	// loop for subarray length we are considering
    	for(int len = 0;len < n;len++) {
    		// loop with two variables i and j, denoting starting and ending of subarray
    		for(int i = 0, j = len;j < n;i++,j++) {
    			// If subarray length is 1, then 1 step will be needed
    			if(len == 0) {
    				dp[i][j] = 1;
    			// for length == 2, check if both the characters are same or not
    			} else if(len == 1) {
    				dp[i][j] = arr[i] == arr[j]?1:2;
    			} else {
    				if(arr[i] == arr[j]) {
    					dp[i][j] = dp[i+1][j-1];
    				} else {
    					dp[i][j] = Integer.MAX_VALUE;
	    				for(int k = i;k < j;k++) {
	    					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j]);
	    				}
    				}
    			}
    		}
    	}
    	return dp[0][n-1];
    }
}

public class Main {
	public static void main(String[] args) {
		int []arr = {1,3,4,1,5};
		System.out.println(new Solution().minimumMoves(arr));
	}
}