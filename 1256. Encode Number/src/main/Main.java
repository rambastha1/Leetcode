package main;

/* Given a non-negative integer num, Return its encoding string.

The encoding is done by converting the integer to a string using a secret function that you should deduce from the following table:

n	f(n)
0	""
1	"0"
2	"1"
3	"00"
4	"01"
5	"10"
6	"11"
7	"000"

Example 1:

Input: num = 23
Output: "1000"
Example 2:

Input: num = 107
Output: "101100"
 

Constraints:

0 <= num <= 10^9
 * 
 */

class Solution {
    public String encode(int num) {
    	return Integer.toBinaryString(num+1).substring(1);
    }
}

public class Main {
	public static void main(String[] args) {
		System.out.println(new Solution().encode(23));
	}
}