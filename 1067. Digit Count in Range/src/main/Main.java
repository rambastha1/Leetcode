package main;

/* Given an integer d between 0 and 9, and two positive integers low and high as lower and upper bounds, respectively. 
 * Return the number of times that d occurs as a digit in all integers between low and high, including the bounds low and high.
 

Example 1:

Input: d = 1, low = 1, high = 13
Output: 6
Explanation: 
The digit d=1 occurs 6 times in 1,10,11,12,13. Note that the digit d=1 occurs twice in the number 11.
Example 2:

Input: d = 3, low = 100, high = 250
Output: 35
Explanation: 
The digit d=3 occurs 35 times in 103,113,123,130,131,...,238,239,243.
 

Note:

0 <= d <= 9
1 <= low <= high <= 2×10^8
 * 
 */


// Question 233
class Solution {
    public int digitsCount(int d, int low, int high) {
    	return getcount(high, d) - getcount(low - 1, d);
    }
    
    private int getcount(int n, int d) {
    	int count = 0;
    	for(long base = 1;base <= n;base *= 10) {
    		long a = n/base, b = n%base;
    		count += a/10 *base;
    		if(a%10 == d)
    			count += b+1;
    		if(a%10 > d)
    			count += base;
    		if(d == 9)
    			count -= base;
    	}
    	return count;
    }
}

public class Main {
	public static void main(String[] args) {
		int d = 0, low = 1, high = 10;
		System.out.println(new Solution().digitsCount(d, low, high));
	}
}