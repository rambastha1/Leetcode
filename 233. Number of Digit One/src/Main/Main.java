package Main;

// https://leetcode.com/problems/number-of-digit-one/discuss/64381/4%2B-lines-O(log-n)-C%2B%2BJavaPython
// https://leetcode.com/problems/number-of-digit-one/discuss/64390/AC-short-Java-solution

class Solution {
	// Time 0(lgN)
    public int countDigitOne(int n) {
    	int ones = 0;
    	for(long base = 1;base <= n;base *= 10) {
    		long a = n/base, b = n%base;
    		ones += a/10 * base;
    		if (a%10 > 1) 
    			ones += base;
    		if (a%10 == 1) 
    			ones += b+1;
    	}
    	return ones;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 13;
		System.out.println(new Solution().countDigitOne(n));
	}
}