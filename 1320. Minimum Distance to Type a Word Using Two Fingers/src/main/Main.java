package main;
// https://leetcode.com/problems/minimum-distance-to-type-a-word-using-two-fingers/discuss/477659/4%2B-DP-Solutions
// dp[k][i][j] -> minimum steps from 0-k characters putting first finger on i and second on j
class Solution {
    // 0(n * 27 ^ m) m -> number of fingers
	public int minimumDistance(String word) {
    	int n = word.length();
    	int [][][]dp = new int[n+1][27][27];
    	for(int w = 1;w <= n;w++) {
    		int c = word.charAt(w-1) - 'A';
    		for(int i = 0;i < 27;i++) {
    			for(int j = 0;j < 27;j++) {
    				dp[w][i][j] = Math.min(dp[w-1][c][i] + cost((char)j, (char)c), dp[w-1][c][j] + cost((char)c, (char)i));
    			}
    		}
    	}
    	return dp[n][26][26];
    }
    
    private int cost(char i, char j) {
    	if(i == 26)
    		return 0;
    	return Math.abs(i/6 - j/6) + Math.abs(i%6 - j%6);
    }
}

public class Main {
	public static void main(String[] args) {
		String word = "HAPPY";
		System.out.println(new Solution().minimumDistance(word));
	}
}
