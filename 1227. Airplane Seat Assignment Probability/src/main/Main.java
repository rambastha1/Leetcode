package main;

// https://leetcode.com/problems/airplane-seat-assignment-probability/discuss/407707/O(1)-space-O(1)-runtime-with-intuitive-explanation.
/* cuz every passenger after the 1st wrong seat will have 2 options either to let nth passenger get his own seat by 
 * (occupying someone else's seat/their own seat) or not letting nth person occupy his own seat by occupying nth person's seat. 
 * The main frame is around the nth person's probability of getting his own seat. Therefore, it boils down to the fact that, 
 * any non nth passenger can either work toward giving the nth person his own seat or grabbing the nth person's seat.
 * 
 * The probability of "nth person can get the nth seat" can be divided into 2 parts:

1)The first person gets the first seat, then the nth person is guaranteed to get the nth seat. The probability of this situation is 1/n.
2)The first person gets any other seat except the first one and the nth one, then the problem shrinks into a subproblem with size n-1. 
The probability of this situation is (n-2)/n.

Therefore, P(N) = 1/n + (n-2)/n * P(N - 1)
 */
class Solution {
    public double nthPersonGetsNthSeat(int n) {
        return n == 1?1d:0.5d;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 3;
		System.out.println(new Solution().nthPersonGetsNthSeat(n));
	}
}
