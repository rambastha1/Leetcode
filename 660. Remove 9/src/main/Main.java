package main;

/* Start from integer 1, remove any integer that contains 9 such as 9, 19, 29...

So now, you will have a new integer sequence: 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, ...

Given a positive integer n, you need to return the n-th integer after removing. Note that 1 will be the first integer.

Example 1:
Input: 9
Output: 10
Hint: n will not exceed 9 x 10^8.
https://leetcode.com/problems/remove-9/discuss/106561/one-line-java-solution
why it works?
every number in 9 based system is in increasing order
so if we convert this n(n th number in decimal system) into 9 based number, that number will be nth number in 9 based system
 * 
 */

class Solution {
    public int newInteger(int n) {
    	return Integer.parseInt(Integer.toString(n, 9));
    }
    
    public int newInteger1(int n) {
        int res = 0, pow = 1;
        while (n > 0) {
            res += n % 9 * pow;
            pow *= 10;
            n /=  9;
        }
        return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 18;
		System.out.println(new Solution().newInteger(n));
		System.out.println(new Solution().newInteger1(n));
	}
}