package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



// https://hackernoon.com/google-interview-questions-deconstructed-the-knights-dialer-f780d516f029
// https://math.stackexchange.com/questions/1890620/finding-path-lengths-by-the-power-of-adjacency-matrix-of-an-undirected-graph
// https://leetcode.com/problems/knight-dialer/discuss/190787/How-to-solve-this-problem-explained-for-noobs!!!

class Solution {
	
	// DP solution time 0(N) Space 0(1)
	// since dp requires only previous row, can be done in dp[10]
	public int knightDialer(int N) {
		// no move for 5
		int [][]reach = {{4,6}, {6,8}, {7,9}, {4,8}, {0,3,9}, {}, {0,1,7}, {2,6}, {1,3}, {2,4}};
		int mod = (int)Math.pow(10, 9)+7;
		long []now = new long[10];
		Arrays.fill(now, 1);
		
		for(int i = 1;i < N;i++) {
			long []next = new long[10];
			// we need to calculate total number of distinct number of moves from each number 
			for(int j = 0;j < 10;j++) {
				int []prev = reach[j];
				for(int key : prev)
					next[j] += now[key];
				next[j] %= mod;
			}
			now = next;
		}
		long ans = 0;
		for(int i = 0;i < 10;i++) {
			ans += now[i];
			ans %= mod;
		}
		return (int)ans;
	}
	
	// DP solution time 0(N) Space 0(N)
	public int knightDialer1(int N) {
		long [][]dp = new long[N][10];
		int [][]reach = {{4,6}, {6,8}, {7,9}, {4,8}, {0,3,9}, {}, {0,1,7}, {2,6}, {1,3}, {2,4}};
		
		int mod = (int)Math.pow(10, 9)+7;
		for(int i = 0;i < 10;i++)
			dp[0][i] = 1;
		for(int i = 1;i < N;i++) {
			for(int j = 0;j < 10;j++) {
				int []prev = reach[j];
				for(int key : prev) {
				/* At any index if there are 8 possible positions a knight can hop
				 * it also means there are 8 possible ways to reach to present index
				 * dp[i-1][key] indicates prev index of knight from where it can hop to present index 
				 */
					dp[i][j] = (dp[i][j] + dp[i-1][key]) % mod;
				}
			}
		}
		
		long ans = 0;
		for(int j = 0;j < 10;j++) {
			ans += dp[N-1][j];
			ans %= mod;
		}
		return (int)ans;
	}
	
	
	// DFS 0 (2^N)
    public int knightDialer2(int N) {
    	if(N == 0)
    		return 0;
    	N--;
    	int [][]grid = {{1,2,3}, {4,5,6}, {7,8,9}, {-1,0,-1}};
    	List<String> res = new ArrayList<>();
    	for(int i = 0;i < 4;i++) {
    		for(int j = 0;j < 3;j++) {
    			StringBuilder sb = new StringBuilder();
    			dfs(grid, i, j, res, sb, N);
    		}
    	}
    	return res.size()%((1<<31)+7);
    }
    
    void dfs(int [][]grid, int x, int y, List<String> res, StringBuilder sb, int N) {
    	if(!issafe(grid, x, y) || grid[x][y] == -1)
    		return;
    	if(N == 0) {
    		res.add(sb.toString());
    		return;
    	}
    	
    	N--;
    	sb.append(grid[x][y]);
    	int [][]dirs = {{-1,-2}, {-2,-1}, {-2,1}, {-1,2}, {1,2}, {2,1}, {2,-1}, {1,-2}};
    	for(int i = 0;i < 8;i++) {
    		int X = x + dirs[i][0];
    		int Y = y + dirs[i][1];
    		dfs(grid, X, Y, res, sb, N);
    	}
    }
    
    boolean issafe(int [][]grid, int x, int y) {
    	return x >= 0 && x < 4 && y >= 0 && y < 3;
    }
}

public class Main {
	public static void main(String[] args) {
		int N = 12;
		System.out.println(new Solution().knightDialer(N));
	}
}
