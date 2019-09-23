package main;

/*
 * Let's remember n1 (natural1), the cost of making the first i-1 columns increasing and not swapping
 * the i-1th column; and s1 (swapped1), the cost of making the first i-1 columns increasing and 
 * swapping the i-1th column. Now we want candidates n2 (and s2), the costs of making the 
 * first i columns increasing if we do not swap (or swap, respectively) the ith column.
 * https://leetcode.com/articles/minimum-swaps-to-make-sequences-increasing/
 */


class Solution {
    public int minSwap(int[] A, int[] B) {
    	if(A.length == 0 || B.length == 0 || A.length == 1 || B.length == 1)
    		return 0;
    	int n1 = 0, s1 = 1;
    	for(int i = 1;i < A.length;i++) {
    		int n2 = Integer.MAX_VALUE, s2 = Integer.MAX_VALUE;
    		if(A[i-1] < A[i] && B[i-1] < B[i]) {
    			n2 = Math.min(n2, n1);
    			s2 = Math.min(s2, s1 + 1);
    		}
    		
    		if(A[i-1] < B[i] && B[i-1] < A[i]) {
    			n2 = Math.min(n2, s1);
    			s2 = Math.min(s2, n1 + 1);
    		}
    		
    		n1 = n2;
    		s1 = s2;
    	}
    	return Math.min(n1, s1);
    }
}

public class Main {

	public static void main(String[] args) {
		int []A = {1,3,5,4};
		int []B = {1,2,3,7};
		System.out.println(new Solution().minSwap(A, B));
 	}
}