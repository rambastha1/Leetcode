package main;
// https://www.youtube.com/watch?v=l3hda49XcDE&feature=emb_logo
class Solution {
	
	/* // Induction rule is very similar to edit distance, where we also consider from the end. And it is based on what 
	 * character in the pattern we meet.
        // 1. if p.charAt(j) == s.charAt(i), M[i][j] = M[i - 1][j - 1]
		//    ######a(i)
		//    ####a(j)
        // 2. if p.charAt(j) == '.', M[i][j] = M[i - 1][j - 1]
        // 	  #######a(i)
        //    ####.(j)
        // 3. if p.charAt(j) == '*':
        //    1. if p.charAt(j - 1) != '.' && p.charAt(j - 1) != s.charAt(i), then b* is counted as empty. M[i][j] = M[i][j - 2]
        //       #####a(i)
        //       ####b*(j)
        //    2.if p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i):
        //       ######a(i)
        //       ####.*(j)
		//
		// 	  	 #####a(i)
        //    	 ###a*(j)
        //      2.1 if p.charAt(j - 1) is counted as empty, then M[i][j] = M[i][j - 2]
        //      2.2 if counted as one, then M[i][j] = M[i - 1][j - 2]
        //      2.3 if counted as multiple, then M[i][j] = M[i - 1][j]
	 * 
	 */
	// 0(m*n)
    public boolean isMatch(String s, String p) {
    	int m = s.length(), n = p.length();
    	boolean [][]dp = new boolean[m+1][n+1];
    	// empty pattern matches empty string
    	dp[0][0] = true;
    	
    	for(int j = 1;j <= n;j++) {
    		if(p.charAt(j-1) == '*')
    			dp[0][j] = dp[0][j-2];
    	}
    	
    	for(int i = 1;i <= m;i++) {
    		for(int j = 1;j <= n;j++) {
    			if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {
    				dp[i][j] = dp[i-1][j-1];
    			} else if(p.charAt(j-1) == '*') {
    				dp[i][j] = dp[i][j-2];
    				if(p.charAt(j-2) == '.' || s.charAt(i-1) == p.charAt(j-2))
    					dp[i][j] |= dp[i-1][j];
    			} else 
    				dp[i][j] = false;
    		} 
    	}
    	return dp[m][n];
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "aab", p = "c*a*b";
		System.out.println(new Solution().isMatch(s, p));
	}
}
