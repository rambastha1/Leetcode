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

/* https://leetcode.com/problems/find-the-derangement-of-an-array/discuss/104981/If-you-don't-understand
 * let's consider what D(n) means, it means the number of derangement for an array with index from 1 to n, and value from 1 to n.
Then let's think about value n, we know it can not be put on index n, instead, it can be put on index 1 to n-1, 
so there are n-1 possibilities.
For each of the situation above, let's say value n is put on index i, then we need to discuss about where we put value i:
1.if value i is put on index n (looks like value i and value n swapped their positions), then we can just ignore value i, 
value n, index i, index n, what's left are n-2 different values and n-2 different indexes, the problem becomes D(n-2).
2.if value i is not put on index n, then we can only ignore value n and index i, what's left are n-1 different values and n-1 
different indexes, each value has an index that it can not be put on. (value i can not be put on index n here) 
So the problem becomes D(n-1).

Therefore, D(n) = (n-1) [D(n-2) + D(n-1)].
 * 
 */
class Solution {
	public int findDerangement(int n) {
        if (n <= 1) return 0;
        long dp[] = new long[n + 1];
        long mod = 1000000007;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]) % mod;
        }
        return (int)dp[n];        
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 7;
		System.out.println(new Solution().findDerangement(n));
	}
}
