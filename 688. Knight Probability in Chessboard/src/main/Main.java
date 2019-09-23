package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
	
	/* DP solution 
     * https://leetcode.com/problems/knight-probability-in-chessboard/discuss/140286/c-dp-solution-with-explanations-beats-100
 	 * https://leetcode.com/articles/knight-probability-in-chessboard/
 	 * Time 0 (N^2*K) Space 0(N^2)
	 */ 	
	public double knightProbability(int N, int K, int r, int c) {
		double [][]dp = new double[N][N];
		int [][]dir = {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
    	dp[r][c] = 1;
    	for(;K > 0;K--) {
    		double [][]temp = new double[N][N];
    		for(int i = 0; i < N;i++) {
    			for(int j = 0;j < N;j++) {
    				for(int k = 0;k < 8;k++) {
    					int x = i + dir[k][0];
    					int y = j + dir[k][1];
    					if(x >= 0 && x < N && y >= 0 && y < N)
    						temp[x][y] += dp[i][j]/8.0;
    				}
    			}
    		}
    		dp = temp;
    	}
		
		double ans = 0;
		for(int i = 0; i < N;i++) {
			for(int j = 0;j < N;j++) {
				ans += dp[i][j];
			}
		}
		return ans;
	}
	
	// DFS Time 0 (8^K)
// https://leetcode.com/problems/knight-probability-in-chessboard/discuss/206720/Share-My-Intuitive-Recursive-Java-Solution
    public double knightProbability1(int N, int K, int r, int c) {
    	if(N == 1)
    		return (r == 0 && c == 0)?1:0;
    	
    	if(K == 0)
    		return issafe(N, r, c)?1:0;
    	if(r < 0 || c < 0) return 0;
    	Map<String, Double> map = new HashMap<>();
    	return dfs(N, K, r, c, map);
    }
    
    double dfs(int N, int K, int r, int c, Map<String, Double> map) {
    	if(!issafe(N, r, c) || K < 0)
    		return 0;
    	
    	if(K == 0)
    		return 1;
    	String key = K + "+" + r + "+" + c;
    	if(map.containsKey(key))
    		return map.get(key);
    	
    	int []x_dir = {-2,-1,1,2,2,1,-1,-2};
    	int []y_dir = {1,2,2,1,-1,-2,-2,-1};
    	
    	double res = 0;
    	for(int i = 0;i < 8;i++) 
    		res += dfs(N, K-1, r+x_dir[i], c+y_dir[i], map);
    	
    	map.put(key, 0.125*res);
    	return (0.125*res);
    }
    
    boolean issafe(int N, int r, int c) {
    	return r >= 0 && r < N && c >= 0 && c < N;
    }
}

public class Main {
	public static void main(String[] args) {
		int N = 3, K = 2, r = 0, c = 0;
		System.out.println(new Solution().knightProbability(N, K, r, c));
		System.out.println(new Solution().knightProbability1(N, K, r, c));
	}
}
