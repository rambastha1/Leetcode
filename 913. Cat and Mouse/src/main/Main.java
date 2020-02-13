package main;

import java.util.Arrays;
/* https://www.acwing.com/solution/leetcode/content/556/
 * https://leetcode.com/problems/cat-and-mouse/discuss/298937/DP-memory-status-search-search-strait-forward-and-easy-to-understand
 */
class Solution {
    public int catMouseGame(int[][] graph) {
    	int n = graph.length;
    	int [][][]dp = new int[2*n][n][n];
    	for(int i = 0;i < 2*n;i++) {
    		for(int j = 0;j < n;j++)
    			Arrays.fill(dp[i][j], -1);
    	}
    	return dfs(dp, graph, 0, 1, 2);
    }
    
    private int dfs(int [][][]dp, int [][]graph, int t, int x, int y) {
    	if(t == dp.length)
    		return 0;
    	if(x == 0)
    		return dp[t][x][y] = 1;
    	if(x == y)
    		return dp[t][x][y] = 2;
    	if(dp[t][x][y] != -1)
    		return dp[t][x][y];
    	boolean flag = false;
    	
    	int turn = t%2;
    	// mouse turn
    	if(turn == 0) {
    		// initially cat wins default
    		flag = true;
    		for(int dest : graph[x]) {
    			int val = dfs(dp, graph, t+1, dest, y);
    			if(val == 1)
    				return dp[t][x][y] = 1;
    			else if(val != 2)
    				flag = false;
    		}
    		if(flag)
    			return dp[t][x][y] = 2;
    		return dp[t][x][y] = 0;
    	} else {
    		// cat turn
    		// initially mouse wins default
    		flag = true;
    		for(int dest : graph[y]) {
    			if(dest == 0)
    				continue;
    			int val = dfs(dp, graph, t+1, x, dest);
    			if(val == 2)
    				return dp[t][x][y] = 2;
    			else if(val != 1)
    				flag = false;
    		}
    		if(flag)
    			return dp[t][x][y] = 1;
    		return dp[t][x][y] = 0;
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]graph = {{2,3},{2},{0,1},{0,4},{3}};
		System.out.println(new Solution().catMouseGame(graph));
	}
}
