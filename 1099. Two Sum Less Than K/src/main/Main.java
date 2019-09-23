package main;

import java.util.Arrays;

class Solution {
    public int twoSumLessThanK(int[] A, int K) {
    	int res = -1;
    	int n = A.length;
    	Arrays.sort(A);
    	int l = 0, r = n-1;
    	while(l < r) {
    		if(A[l] >= K)
    			break;
    		if(A[r] >= K) {
    			r--;
    			continue;
    		}
    		int sum = A[l] + A[r];
    		if(sum >= K)
    			r--;
    		else {
    			res = Math.max(res, sum);
    			l++;
    		}
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int []A = {34,23,1,24,75,33,54,8}; 
		int K = 60;*/
		int []A = {10,20,30}; 
		int K = 15;
		System.out.println(new Solution().twoSumLessThanK(A, K));
	}
}
