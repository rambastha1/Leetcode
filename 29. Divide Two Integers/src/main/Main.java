package main;
// https://leetcode.com/problems/divide-two-integers/discuss/13407/C%2B%2B-bit-manipulations
class Solution {
    public int divide(int dividend, int divisor) {
    	if(divisor == 0 || dividend == Integer.MIN_VALUE && divisor == -1)
    			return Integer.MAX_VALUE;
    	
    	int res = 0;
    	int sign = (dividend > 0) ^ (divisor > 0)?-1:1;
    	long dvd = Math.abs((long)dividend); // dividend
    	long dvs = Math.abs((long)divisor); // divisor
    	while(dvs <= dvd) {
    		long temp = dvs, quot = 1;
    		while(temp << 1 <= dvd) {
    			temp <<= 1;
    			quot <<= 1;
    		}
    		dvd -= temp;
    		res += quot;
    	}
    	return sign == 1?res:-res;
    }
}

public class Main {
	public static void main(String[] args) {
		int dividend = 10, divisor = 3;
		System.out.println(new Solution().divide(dividend, divisor));
	}
}
