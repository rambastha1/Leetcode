package main;

import java.util.Arrays;

// https://leetcode.com/discuss/interview-question/379066/Twitter-or-OA-2019-or-Parking-Dilemma

class Solution {
	public int carparking(int []A, int k) {
		Arrays.sort(A);
		int n = A.length;
		int res = Integer.MAX_VALUE;
		for(int i = 0;i < n-k+1;i++) {
			res = Math.min(res, A[i+k-1] - A[i] + 1);
		}
		return res;
	}
}

public class Main {
	public static void main(String[] args) {
		int []A = {2,10,8,17};
		int k = 3;
		System.out.println(new Solution().carparking(A, k));
	}
}
