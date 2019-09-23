package main;

/* Given a positive integer a, find the smallest positive integer b whose multiplication of each digit equals to a.
 * If there is no answer or the answer is not fit in 32-bit signed integer, then return 0.
 * 
 * Example 1
 * Input:
 * 48 
 * Output:
 * 68
 * 
 * Example 2
 * Input:
 * 15
 * Output:
 * 35
 * 
 * https://leetcode.com/articles/minimum-factorization/
 */

class Solution {
    public int smallestFactorization(int a) {
    	if(a == 1)
    		return a;
    	long res = 0;
    	int mul = 1;
    	for(int i = 9;i >= 2;i--) {
    		// a will never be 0, it will become 1 but loop ends at 2
    		while(a%i == 0) {
    			a /= i;
    			res = i * mul + res;
    			mul *= 10;
    		}
    	}
    	return (a < 2 && res<= Integer.MAX_VALUE)?(int)res:0;
    }
}

public class Main {
	public static void main(String[] args) {
		int a = 100;
		System.out.println(new Solution().smallestFactorization(a));
	}
}
