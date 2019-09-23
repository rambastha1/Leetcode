package main;

import java.util.Arrays;

/*
 * We can check whether two numbers have the same digits by comparing the count of their digits. 
 * For example, 338 and 833 have the same digits because they both have exactly two 3's and one 8.
 * Since N could only be a power of 2 with 9 digits or less (namely, 2^0, 2^1, ... , 2^2*9
 * we can just check whether N has the same digits as any of these possibilities.
 */

class Solution {
	//Time 0(lg ^ 2)
    public boolean reorderedPowerOf2(int N) {
    	//Size is lgN
    	int []count = util(N);
    	/*2^30 = 10^9 = 1GB
    	 *Constraint in question is 1 <= N <= 10 ^9
    	 *Thus loop to 30
    	 */
    	for(int i = 0;i < 31;i++) {
    		int []temp = util(1 << i);
    		if(Arrays.equals(count, temp))
    			return true;
    	}
    	return false;
    }
    //It takes 0(lgN) time
    public int[] util(int N) {
    	int []res = new int[10];
    	while(N > 0) {
    		res[N%10]++;
    		N /= 10;
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int N = 32;
		System.out.println(new Solution().reorderedPowerOf2(N));
	}
}