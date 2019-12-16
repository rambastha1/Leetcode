package main;

class Solution {
	//https://leetcode.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/discuss/?currentPage=1&orderBy=most_votes&query=
	
	// Time 0(m*n*lg(min(m,n)))
    public int maxSideLength(int[][] mat, int threshold) {
    	int m = mat.length, n = mat[0].length;
    	int [][]dp = new int[m+1][n+1];
    	for(int i = 1;i <= m;i++) {
    		for(int j = 1;j <= n;j++) {
    			dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + mat[i-1][j-1];
    		}
    	}
    	
    	int l = 0, r = Math.min(m, n);
    	while(l <= r) {
    		int mid = l + (r-l)/2;
    		if(exists(dp, mid, threshold))
    			l = mid + 1;
    		else
    			r = mid - 1;
    	}
    	return r;
    }
    
    private boolean exists(int [][]sum, int mid, int threshold) {
    	for(int i = mid;i < sum.length;i++) {
    		for(int j = mid;j < sum[0].length;j++) {
    			if(sum[i][j] - sum[i-mid][j] - sum[i][j-mid] + sum[i-mid][j-mid] <= threshold) 
    				return true;
    		}
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]mat = {{1,1,3,2,4,3,2},{1,1,3,2,4,3,2},{1,1,3,2,4,3,2}};
		int threshold = 4;
		System.out.println(new Solution().maxSideLength(mat, threshold));
	}
}
