package main;

// https://leetcode.com/problems/integer-break/discuss/80721/Why-factor-2-or-3-The-math-behind-this-problem.
// https://leetcode.com/problems/integer-break/discuss/80785/O(log(n))-Time-solution-with-explanation
class Solution {
	
	/* DP solution
	 * https://leetcode.com/problems/integer-break/discuss/80694/Java-DP-solution
	 * Each number can be broken or not thus dp[i] = max(i, dp[i])
	 * dp[i] stores max product if number is broken
	 * 
	 * i = j + (i-j) check product of both broken and not broken
	 */
    public int integerBreak(int n) {
    	int []dp = new int[n+1];
    	dp[1] = 1;
    	for(int i = 2;i <= n;i++) {
    		for(int j = 1;j < i;j++)
    			dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * Math.max(i-j, dp[i-j]));
    	}
    	return dp[n];
    }
	
	
	// Math Solution
	/*public int integerBreak(int n) {
		if(n == 2)
			return 1;
		else if(n == 3)
			return 2;
		else if(n%3 == 0)
			return (int)Math.pow(3, n/3);
		else if(n%3 == 1)
			return (int) (4 * Math.pow(3, (n-4)/3));
		else 
			return (int) (2 * Math.pow(3, n/3));
	}*/
	
	
}

public class Main {
	public static void main(String[] args) {
		int n = 10;
		System.out.println(new Solution().integerBreak(n));
	}
}