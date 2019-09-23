package main;

// https://leetcode.com/problems/integer-replacement/discuss/87920/A-couple-of-Java-solutions-with-explanations
/* Even if n is int_max, We can increment n, but it becomes negative. That's why I have while (n != 1) instead of while (n > 1)
 * using unsigned right shift n >>>= 1; instead of n /= 2 to handle the overflow issue.
 * if n is negative, n/2 might become less than INT_MIN 
 */
class Solution {
    public int integerReplacement(int n) {
    	int ans = 0;
    	while(n != 1) {
    		if((n&1) == 0)
    			n >>>= 1;
    		else {
    			if(n == 3 || ((n>>>1)&1) == 0)
    				n--;
    			else
    				n++;
    		}
    		ans++;
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 7;
		System.out.println(new Solution().integerReplacement(n));
	}
}
