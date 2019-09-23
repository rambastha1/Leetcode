package main;

import java.util.Arrays;
// subsequence 376
// Problem is Maximum size of wiggle sorted array

class Solution {
	
	// Constant space solution
	public int maxTurbulenceSize(int[] A) {
        int pre = 0, cur = 0, len = 1, res = 1; 
        for (int i = 1; i < A.length; i++) {
            cur = Integer.compare(A[i], A[i - 1]);
            // if opposite pattern increase length
            if (cur * pre == -1) len++;
            // numbers equal 
            else if (cur == 0) len = 1; 
            // sane pattern 
            else len = 2; 
            res = Math.max(res, len);
            // so that next can be checked
            pre = cur; 
        }
        return res; 
    }
	
	// Constant space solution
	public int maxTurbulenceSize2(int[] A) {
		if(A == null || A.length == 0)
    		return 0;
    	int n = A.length;
    	/*
    	 * if sign keeps wiggle then length will keep increasing as
    	 * dec = inc + 1; and dec = inc + 1;
    	 * if two same sign comes length will become 2 as inc = 1 and dec = 1
    	 * if two numbers are same inc = 1 dec = 1 
    	 */
		int inc = 1, dec = 1, res = 1;
		for(int i = 1;i < n;i++) {
			if(A[i] < A[i-1]) {
				dec = inc + 1;
				inc = 1;
			} else if(A[i] > A[i-1]) {
				inc = dec + 1;
				dec = 1;
			} else {
				inc = 1;
				dec = 1;
			}
			res = Math.max(res, Math.max(dec, inc));
		}
		return res;
	}
	
	// Space 0(N)
    public int maxTurbulenceSize1(int[] A) {
    	if(A == null || A.length == 0)
    		return 0;
    	int n = A.length;
    	int []dp = new int[n];
    	Arrays.fill(dp, 1);
    	dp[1] = 2;
    	int ans = 1;
    	
    	for(int i = 2;i < n;i++) {
    		int j = i-1;
    		if(A[i] == A[j]) {
    			dp[i] = dp[i-1];
    			continue;
    		}
    		
    		if(A[i] > A[i-1] && A[i-1] < A[i-2] || A[i] < A[i-1] && A[i-1] > A[i-2])
    			dp[i] = dp[i-1] + 1;
    		else dp[i] = 2;
    		
    		/*boolean flag = (A[j] < A[j+1])?true:false;
    		for(;j > 0;j--) {    			
    			if(flag && A[j-1] <= A[j] || !flag && A[j-1] >= A[j])
    				break;
    			flag = !flag;
    		}
    		dp[i] = Math.max(dp[i], i-j+1);*/
    		ans = Math.max(ans, dp[i]);
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {9,4,2,10,7,8,8,1,9};
		//int []A = {0,1,1,0,1,0,1,1,0,0};
		System.out.println(new Solution().maxTurbulenceSize(A));
	}
}