package main;

/* Given Android 9 key lock screen and numbers m and n, where 1 <= m <= n <= 9 .
 * Count the total number of patterns of Android lock screen, which consist of 
 * minimum of m keys and maximum n keys.
 * Rules for valid pattern
 * Each pattern must connect at least m keys and at most n keys
 * All the keys must be distinct
 * If the line connecting two consecutive keys in the pattern passes through any other keys, 
 * the other keys must have previously selected in the pattern. No jumps through non selected 
 * key is allowed
 * The order of keys used matters.
 * 
 * Example:
 * | 1 | 2 | 3 |
 * | 4 | 5 | 6 |
 * | 7 | 8 | 9 |
 * Valid move : 6 - 5 - 4 - 1 - 9 - 2
 * Line 1 - 9 is valid because it pass through key 5, which has been already selected 
 * in the pattern
 * Valid move : 2 - 4 - 1 - 3 - 6
 * Line 1 - 3 is valid because it pass through key 2, which has been already selected 
 * in the pattern
 * Invalid move : 4 - 1 - 3 - 6
 * Line 1 - 3 pass through key 2 which is not still selected in the pattern
 * Invalid move : 4 - 1 - 9 - 2
 * Line 1 - 9 pass through key 5 which is not still selected in the pattern
 */

// geeksforgeeks is better
// http://massivealgorithms.blogspot.com/2016/06/leetcode-351-android-unlock-patterns.html
// https://www.geeksforgeeks.org/number-of-ways-to-make-mobile-lock-pattern/

/* To take care of such invalid moves, a 2D jump array is taken in below code which stores 
 * possible jump cells in jump array. When we call recursively we impose an extra condition 
 * that if we are moving from one cell to another which involves some jumping cell, 
 * then this cell should be visited already to ignore invalid moves.
 */

class Solution {
    public int numberOfPatterns(int m, int n) {
    	if(n == 0)
    		return 0;
    	
    	int [][]skip = new int[10][10];
    	// 2 lies between 1 and 3
    	skip[1][3] = skip[3][1] = 2;
    	// 4 lies between 1 and 7
    	skip[1][7] = skip[7][1] = 4;
    	// 6 lies between 3 and 9
    	skip[3][9] = skip[9][3] = 6;
    	// 8 lies between 7 and 9
    	skip[7][9] = skip[9][7] = 8;
    	// 5 lies between 1, 9  2, 8  3, 7 and 4, 6
    	skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = 5;
    	skip[4][6] = skip[6][4] = 5;
    	
    	boolean []visited = new boolean[10];
    	int res = 0;
    	
    	// symmetry is used because all these will result in same number of keys
    	for(int i = m;i <= n;i++) {
    		res += dfs(visited, skip, 1, i-1) * 4; // 1, 3, 7, 9 are symmetric
    		res += dfs(visited, skip, 2, i-1) * 4; // 2, 4, 6, 8 are symmetric
    		res += dfs(visited, skip, 5, i-1);  // 5
    	}
    	return res;
    }
    
    int dfs(boolean []visited, int [][]skip, int curr, int remain) {
    	// cur: the current position
        // remain: the steps remaining
    	if(remain < 0)
    		return 0;
    	if(remain == 0)
    		return 1;
    	visited[curr] = true;
    	int res = 0;
    	for(int i = 1;i <= 9;i++) {
    	/*
    	 * If vis[i] is not visited and either the number is adjacent i.e
    	 * not number between i and curr or this between number is already visited 
    	 */
    		if(!visited[i] && (skip[i][curr] == 0 || visited[skip[i][curr]]))
    			res += dfs(visited, skip, i, remain-1);
    	}
    	visited[curr] = false;
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int m = 5, n = 7;
		System.out.println(new Solution().numberOfPatterns(m, n));
	}
}