package main;

// 9-1 digit number, 90-2 digits 900-3 digits 9000-4 digits
// https://leetcode.com/problems/nth-digit/discuss/88369/0ms-C%2B%2B-Solution-with-Detail-Explanation
class Solution {
	public int findNthDigit(int n) {
    	long count = 9, base = 1, num = 1;;
    	while(n > count*base) {
    		n -= base*count;
    		count *= 10;
    		base++;
    		num *= 10;
    	}
    	
    	long index = n%base;
    	if(index == 0)
    		index = base;
    	
    	num += (index== base)?(n/base)-1:n/base;
    	// index will become unit's digit
    	for(long i = index;i < base;i++)
    		num /= 10;
    	
    	return (int)num%10;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 249;
		System.out.println(new Solution().findNthDigit(n));
	}
}
