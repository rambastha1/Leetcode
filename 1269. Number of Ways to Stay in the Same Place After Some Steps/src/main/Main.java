package main;

import java.util.Arrays;

/* In the first loop, j indicating how much step taken so far.
In the second loop, i indicating the current location we are focusing on. We are trying to calculate the 
total number of ways to get location i using j steps.
Notice that we are picking the smaller number between n-1 and steps-j. steps-j means that we cannot go steps-j far 
from 0 since we need j steps to get back. This will boost up the speed dramatically.
 * 
 */

class Solution {
	// Similar to Knight Dialer
    public int numWays(int steps, int arrLen) {
        int []dp = new int[arrLen];
        if(arrLen <= 1)
        	return arrLen;
        
        dp[0] = dp[1] = 1;
        int mod = (int)Math.pow(10, 9) + 7;
        
        for(int i = 1;i < steps;i++) {
            int []temp = new int[arrLen];
            // go to steps because half steps are required to get back to original position
            for(int j = 0;j <= Math.min(arrLen-1, steps-i);j++) {
                long val = dp[j];
                if(j > 0)
                	val = (val+dp[j-1])%mod;
                if(j < arrLen-1)
                	val = (val+dp[j+1])%mod;
                temp[j] = (int)val;
            }
            dp = temp;
        }
        //System.out.println(dp[0]);
        return dp[0];
    }
}	

public class Main {
	public static void main(String[] args) {
		int steps = 4, arrLen = 2;
		System.out.println(new Solution().numWays(steps, arrLen));
	}
}