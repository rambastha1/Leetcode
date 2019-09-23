package main;

/* why binary search works first comment
 * https://leetcode.com/problems/sqrtx/discuss/25057/3-4-short-lines-Integer-Newton-Every-Language
 * 
 */

class Solution {
	public int mySqrt(int x) {
	    if (x == 0)
	        return 0;
	    int left = 1, right = Integer.MAX_VALUE;
	    while (true) {
	        int mid = left + (right - left)/2;
	        if (mid > x/mid) {
	            right = mid - 1;
	        } else {
	            if (mid + 1 > x/(mid + 1))
	                return mid;
	            left = mid + 1;
	        }
	    }
	}
}

public class Main {
	public static void main(String[] args) {
		int x = 8;
		System.out.println(new Solution().mySqrt(x));
	}
}
