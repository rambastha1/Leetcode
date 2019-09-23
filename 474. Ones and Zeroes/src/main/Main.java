package main;
// https://leetcode.com/problems/ones-and-zeroes/discuss/95807/0-1-knapsack-detailed-explanation.
// https://leetcode.com/problems/ones-and-zeroes/discuss/95811/Java-Iterative-DP-Solution-O(mn)-Space
class Solution {
	// knapsack 0-1
	// Time 0(str.len*m*n + strs.len*maxof(strs[i])
    public int findMaxForm(String[] strs, int m, int n) {
    	// maximum number of string that can be formed from i 0's and j 1's 
    	int [][]dp = new int[m+1][n+1];
    	for(String s : strs) {
    		int []count = new int[2];
    		for(char c : s.toCharArray())
    			count[c-'0']++;
    		
    		for(int i = m;i >= count[0];i--) {
    			for(int j = n;j >= count[1];j--) {
    				dp[i][j] = Math.max(dp[i][j], 1 + dp[i - count[0]][j - count[1]]);
    			}
    		}
    	}
    	return dp[m][n];
    }
}

public class Main {
	public static void main(String[] args) {
		/*String []strs = {"10", "0001", "111001", "1", "0"};
		int m = 5, n = 3;*/
		
		/*String []strs = {"10", "0", "1"};
		int m = 1, n = 1;*/
		
		/*String []strs = {"10","0001","111001","1","0"};
		int m = 3, n = 4;*/
		
		String []strs = {"111","1000","1000","1000"};
		int m = 9, n = 3;
		System.out.println(new Solution().findMaxForm(strs,	m, n));
	}
}
