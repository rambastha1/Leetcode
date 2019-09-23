package main;

// https://leetcode.com/problems/rotate-function/discuss/87853/Java-O(n)-solution-with-explanation
// check discussion
class Solution {
	public int maxRotateFunction(int[] A) {
        int len = A.length;
        if(len == 0 || len == 1) return 0;
        int sum = 0, dp0 = 0, max = Integer.MIN_VALUE;
        int[] dp = new int[len + 1];
        for(int i = 0; i < len; i++) {
            sum += A[i];
            dp0 += i * A[i];
        }
        dp[0] = dp0;
        for(int i = 1; i <= len; i++) {
            dp[i] = dp[i - 1] + sum - len * A[len - i];
            max = Math.max(dp[i], max);
        }
        return max;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []A = {4,3,2,6};
		int []A = {Integer.MIN_VALUE, Integer.MIN_VALUE};
		System.out.println(new Solution().maxRotateFunction(A));
	}
}
