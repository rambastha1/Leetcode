package main;

/* In combinatorial mathematics, a derangement is a permutation of the elements of a set, 
 * such that no element appears in its original position.
 * There's originally an array consisting of n integers from 1 to n in ascending order, 
 * you need to find the number of derangement it can generate.
 * Also, since the answer may be very large, you should return the output mod 109 + 7.
 * 
 * Example 1:
 * Input: 3
 * Output: 2
 * Explanation: The original array is [1,2,3]. The two derangements are [2,3,1] and [3,1,2].
 * Note:
 * n is in the range of [1, 10^6].
 */

/* The mis-alignment arrangement can be understood as assigning the hats labeled 1~n to the 
 * people labeled 1~n, and the hat labels that each person gets are 
 * It is different from its own label.
 * https://en.wikipedia.org/wiki/Derangement#Counting_derangements
 * https://www.youtube.com/watch?v=pbXg5EI5t4c
 * https://www.youtube.com/watch?v=qYAWjIVY7Zw&feature=youtu.be
 */

class Solution {
	public int findDerangement(int n) {
		if(n == 1) return 0;
		if(n == 2) return 1;
		if(n == 3) return 2;
		int []dp = new int[n+1];
		dp[0] = 1;
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 2;
		int mod = (int)Math.pow(10, 9)+7;
		for(int i = 4;i <= n;i++) {
			long val  = (i-1)*(dp[i-1] + dp[i-2]);
			dp[i] = (int)val%mod;
		}
		return dp[n];
	}
}

public class Main {
	public static void main(String[] args) {
		int n = 7;
		System.out.println(new Solution().findDerangement(n));
	}
}
