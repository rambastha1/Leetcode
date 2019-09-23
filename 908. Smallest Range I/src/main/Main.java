package main;
import java.util.Arrays;
// https://leetcode.com/articles/smallest-range-i/

class Solution {
	public int smallestRangeI(int[] A, int K) {
		if(A.length == 1)
    		return 0;
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for(int a : A) {
			min = Math.min(min, a);
			max = Math.max(max, a);
		}
		return Math.max(max-min-2*K, 0);
	}
	
    public int smallestRangeII(int[] A, int K) {
    	if(A.length == 1)
    		return 0;
    	int n = A.length;
    	Arrays.sort(A);
    	int low = A[0] + K, high = A[n-1] - K;
    	return high-low>0?high-low:0;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {0,10};
		int K = 2;
		System.out.println(new Solution().smallestRangeI(A, K));
	}
}
