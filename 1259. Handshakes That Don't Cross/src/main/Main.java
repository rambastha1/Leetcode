package main;

/* You are given an even number of people num_people that stand around a circle and each person shakes hands with someone else, 
 * so that there are num_people / 2 handshakes total.

Return the number of ways these handshakes could occur such that none of the handshakes cross.

Since this number could be very big, return the answer mod 10^9 + 7

 

Example 1:

Input: num_people = 2
Output: 1
Example 2:



Input: num_people = 4
Output: 2
Explanation: There are two ways to do it, the first way is [(1,2),(3,4)] and the second one is [(2,3),(4,1)].
Example 3:



Input: num_people = 6
Output: 5
Example 4:

Input: num_people = 8
Output: 14
 

Constraints:

2 <= num_people <= 1000
num_people % 2 == 0
 * 
 */

/* Consider there are n people (n is even)
For those people to not cross hand, person 1 can shake 2, 4, 6, 8, ..., n:

Shake 2: divide into 2 sets (an emtpy set and a set of people from 3 to n)
Shake 4: divide into 2 sets (a set of people 2 & 3 and a set of people from 5 to n)
Shake 6: divide into 2 sets (a set of people from 2 to 5 and a set of people from 7 to n)
...
Shake n: divide into 2 sets (a set of people from 2 to n-1 and an empty set)
For for n people, there are n/2 way for perosn 1 to shake hand. If person 1 shake hand with person k, 
there are count(2 to k-1)*count(k+1 to n) scenarios.

If we store an array cache where cache[i] denotes numbers of way when there are i people. Then:
count(2 to k-1)*count(k+1 to n) = cache[k-2]*cache[n-k]
 * 
 * 
 * dp[n] is the number of shaking ways of n pairs people
In the the view of first people in these n pairs,
he/she can choose anyone, split i pairs on his left and n - 1 - i pairs on his right.

https://www.youtube.com/watch?time_continue=2&v=YGsmvcQzpxs&feature=emb_logo
https://leetcode.com/problems/handshakes-that-dont-cross/discuss/430483/Java-9-lines-DP-Solution-with-details-explain
 */

class Solution {
	public int numberOfWays(int num_people) {
        long mod = 1_000_000_007;
        // num/2 because every handshake would be counted twice otherwise
        // there are n/2 way for each person to shake hand
        long[] dp = new long[num_people/2+1];
        // if there are 0 people then there is only 1 way to do handshake i.e no handshake
        // split so that there are even number of people on left and right side
        dp[0] = 1;
        for (int i = 1; i <= num_people/2; i++) {
            for (int j = 0; j < i; j++) {
            	// -1 because one person is the person in handshake group considered
                dp[i] = (dp[i] + (dp[j]*dp[i-j-1])) % mod;
            }
        }
        return (int)dp[num_people/2];
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 8;
		System.out.println(new Solution().numberOfWays(n));
	}
}
