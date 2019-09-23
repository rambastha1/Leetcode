package main;

/* https://leetcode.com/problems/maximum-sum-circular-subarray/discuss/262726/java-simple-4ms-solution-which-beats-100
 * calculate max and min subarray sum using kadane's algorithm take max of max and total - min(for wrapping elements)
 */

class Solution {
    public int maxSubarraySumCircular(int[] A) {
    	if(A.length == 1)
    		return A[0];
    	int lmax = -30001, lmin = 30001, gmax = Integer.MIN_VALUE, gmin = Integer.MAX_VALUE, n = A.length, sum = 0;
    	for(int i = 0;i < n;i++) {
    		sum += A[i];
    		lmax = Math.max(lmax+A[i], A[i]);
    		gmax = Math.max(gmax, lmax);
    	}
    	
    	for(int i = 0;i < n;i++) {
    		lmin = Math.min(lmin+A[i], A[i]);
    		gmin = Math.min(gmin, lmin);
    	}
    	// first part necessary when all elements are negative
    	return sum==gmin?gmax:Math.max(gmax, sum - gmin);
    }
}

public class Main {
	public static void main(String[] args) {
		//int A[] = {1,-2,3,-2};
		//int A[] = {5,-3,5};
		int A[] = {-2,-3,-1};
		System.out.println(new Solution().maxSubarraySumCircular(A));
	}
}
