package main;
// https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/discuss/117595/Short-Java-O(n)-Solution
class Solution {
	
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
    	if(A == null || A.length == 0)
    		return 0;
    	int s = 0, ans = 0, count = 0;
    	
    	for(int e = 0;e < A.length;e++) {
    		if(A[e] >= L && A[e] <= R) {
    			ans += e-s+1;
    			count = e-s+1;
    		} else if(A[e] < L)
    			ans += count;
    		else { 
    			s = e+1;
    			count = 0;
    		}
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int []A = {2,1,4,3};
		int L = 2, R = 3;*/
		
		/*int []A = {2,9,2,5,6};
		int L = 2, R = 8;*/
		
		int []A = {73,55,36,5,55,14,9,7,72,52};
		int L = 32, R = 69;
		System.out.println(new Solution().numSubarrayBoundedMax(A, L, R));
	}
}
