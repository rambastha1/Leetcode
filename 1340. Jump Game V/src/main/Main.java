package main;
/* 0(n*d) time and 0(n) space
 * https://leetcode.com/problems/jump-game-v/discuss/496520/Top-Down-DP-O(nd)
 * dp[i] = maximum jump from that index
 */
class Solution {
    public int maxJumps(int[] arr, int d) {
    	if(arr.length <= 1)
    		return arr.length;
    	int []dp = new int[arr.length];
    	int max = 0;
    	for(int i = 0;i < arr.length;i++)
    		max = Math.max(max, dfs(arr, d, i, dp));
    	return max;
    }
    
    int dfs(int []arr, int d, int index, int []dp) {
    	if(dp[index] != 0)
    		return dp[index];
    	int res = 1;
    	for(int i = index + 1;i <= Math.min(arr.length-1, index+d) && arr[i] < arr[index];i++)
    		res = Math.max(res, 1 + dfs(arr, d, i, dp));
    	for(int i = index -1;i >= Math.max(0, index-d) && arr[i] < arr[index];i--)
    		res = Math.max(res, 1 + dfs(arr, d, i, dp));
    	return dp[index] = res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []arr = {6,4,14,6,8,13,9,7,10,6,12};
		int d = 2;
		System.out.println(new Solution().maxJumps(arr, d));
	}
}
