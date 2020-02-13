package main;

import java.util.Arrays;

// https://www.geeksforgeeks.org/sum-of-width-max-and-min-diff-of-all-subsequences/
/* 
 * A[i] * (2 ^ i) means the positive effect of A[i] on res,
while A[i] * 2 ^ (n - i - 1) means the negative effect of A[i] on res.
let say we have : XXXX A[i] XXXX,
from A[i] to left, we have (2 ^ i) Subsequence, since the string is sorted , so for all these (2 ^ i) substring, 
A[i] is the largest, the contribution is A[i].
while from A[i] to right, we have 2 ^ (n - i - 1) Subsequence, so A[i] is the smallest for them, the contribution is -A[i].
 * 
 */
class Solution {
	// 0(NlgN)
	public int sumSubseqWidths(int[] A) {
        Arrays.sort(A);
        long ans = 0;
        int n = A.length;
        int mod = (int)1e9+7;
        
        long[] pows = new long[n]; 
        pows[0] = 1;
        for (int i = 1; i < n; i++)  {
            pows[i] = pows[i-1] * 2 % mod;
        }
        
        for (int i = 0; i < n; i++) {
            int a = i, b = n-1-i;
            ans = (ans + A[i] * (pows[a] - pows[b])) % mod;
        }
        return (int)(ans + mod) % mod;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {1,3,2};
		System.out.println(new Solution().sumSubseqWidths(A));
	}
}
