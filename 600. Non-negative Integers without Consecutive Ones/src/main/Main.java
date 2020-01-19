package main;

// https://www.geeksforgeeks.org/count-number-binary-strings-without-consecutive-1s/
// https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/discuss/103749/Java-Solution-DP
class Solution {
	/* Let a[i] be the number of binary strings of length i which do not contain any two consecutive 1’s and which end in 0. 
	 * Similarly, let b[i] be the number of such strings which end in 1. We can append either 0 or 1 to a string ending in 0, 
	 * but we can only append 0 to a string ending in 1. This yields the recurrence relation
	 * a[i] = a[i - 1] + b[i - 1]
	 * b[i] = a[i - 1] 
	 * The base cases of above recurrence are a[1] = b[1] = 1. The total number of strings of length i is just a[i] + b[i].
	 * 
	 * Now we just need to subtract those strings whose value is greater than num
	 * 
	 * we iterate from the MSB to LSB of num:
	 * 
	 * if bit[i] == 1
	 * if bit[i + 1] == 0, i.e. 10** there's no solutions in res that is larger than num, we go further into smaller bit and subtract.
	 * if bit[i + 1] == 1, i.e 11** we know in those res solutions it won't have any consecutive ones. when bit[i + 1] == 1, 
	 * in one[i + 1], the i-th bit in valid solutions must be 0, which are all smaller than 'num', 
	 * we don't need to check smaller bits and subtract, so we break form the loop.
	 * 
	 * if bit[i] == 0
	 * if bit[i + 1] == 1, i.e 01*** there's no solutions in res that is larger than num, we go further into smaller bit and subtract.
	 * if bit[i + 1] == 0, i.e 00*** we know zero[i + 1] includes solutions of i-th == 0 (00***) and i-th bit == 1 (01***), 
	 * we know the i-th bit of num is 0, so we need to subtract all the 01*** solutions because it is larger than num. 
	 * Therefore, one[i] is subtracted from res.
	 * Time 0(n)
	 */
    public int findIntegers(int num) {
    	StringBuilder sb = new StringBuilder(Integer.toBinaryString(num)).reverse();
    	int n = sb.length();
    	int []one = new int[n];
    	int []zero = new int[n];
    	
    	// number of binary string of length zero ending with 0 or 1
    	zero[0] = one[0] = 1;
    	for(int i = 1;i < n;i++) {
    		zero[i] = zero[i-1] + one[i-1];
    		one[i] = zero[i-1]; 
    	}
    	
    	int res = zero[n-1] + one[n-1];
    	for(int i = n-2;i >= 0;i--) {
    		if(sb.charAt(i) == '1' && sb.charAt(i+1) == '1')
    			break;
    		if(sb.charAt(i) == '0' && sb.charAt(i+1) == '0')
    			res -= one[i];
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int num = 1000000000;
		System.out.println(new Solution().findIntegers(num));
	}
}
