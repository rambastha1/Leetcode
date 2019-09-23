package main;

/*
 * Scan the prefix sum array from index L + M, which is the first possible position;
 * update the max value of the L-length subarray; then update max value of the sum of the both;
 * we need to swap L and M to scan twice, since either subarray can occur before the other.
 * In private method, prefix sum difference p[i - M] - p[i - M - L] is L-length subarray 
 * from index i - M - L to i - M - 1, and p[i] - p[i - M] is M-length subarray 
 * from index i - M to i - 1. 
 * 
 * why ? Lmax = A[i-M] - A[i-M-L] and A[i-M] - A[i-M-L]
 * It ensures that two sum never overlap
 * I think the step that ensures it never overlap with the M elements in the end 
 * is res = max(res, Lmax + A[i] - A[i - M], Mmax + A[i] - A[i - L]). 
 * This step is res is the maximum among current res, current Lmax + A[i]-A[i-M], 
 * that is current Lmax plus forward M numbers sum, and same idea with Mmax. 
 * Lmax is the sum of numbers before A[i-M], while A[i]-A[i-M] is the sum of numbers after 
 * A[[i-M]. Therefore, it won't overlap. Hope that helps
 * https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/discuss/278251/JavaC%2B%2BPython-O(N)Time-O(1)-Space
 */

class Solution {
	
	//Time 0(N) constant space
	public int maxSumTwoNoOverlap(int[] A, int L, int M) {
		for(int i = 1;i < A.length;i++)
			A[i] += A[i-1];
		//first possible position
		int res = A[L+M-1], Lmax = A[L-1], Mmax = A[M-1];
		
		for(int i = L+M;i < A.length;i++) {
			// update max of L-length subarray
			
			Lmax = Math.max(Lmax, A[i-M] - A[i-M-L]);
			// update max of M-length subarray
			Mmax = Math.max(Mmax, A[i-L] - A[i-M-L]);
			//max(L before M, M before L)
			res = Math.max(res, Math.max(Lmax + A[i] - A[i-M], Mmax + A[i] - A[i-L]));
		}
		return res;
	}
	
	//Time and Space 0(N)
	/*public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int[] prefixSum = new int[A.length + 1];
        for (int i = 0; i < A.length; ++i) {
            prefixSum[i + 1] = prefixSum[i] + A[i];
        }
        //max(L before M, M before L)
        return Math.max(maxSum(prefixSum, L, M), maxSum(prefixSum, M, L));
    }
	
    private int maxSum(int[] p, int L, int M) {
        int ans = 0, maxL = 0, maxM = 0;
        for (int i = L + M; i < p.length; ++i) {
            maxL = Math.max(maxL, p[i - M] - p[i - M - L]); // update max of L-length subarray.
            maxM = Math.max(maxM, p[i] - p[i-M]); // // update max of M-length subarray.
            ans = Math.max(ans, maxL + maxM); // update max of the sum of L-length & M-length subarrays.
        }
        return ans;
    }*/
}

public class Main {
	public static void main(String[] args) {
		/*int [] A = {0,6,5,2,2,5,1,9,4};
		int L = 1, M = 2;*/
		int [] A = {3,8,1,3,2,1,8,9,0};
		int L = 3, M = 2;
		/*int [] A = {2,1,5,6,0,9,5,0,3,8};
		int L = 4, M = 3;*/
		
		System.out.println(new Solution().maxSumTwoNoOverlap(A, L, M));
	}
}