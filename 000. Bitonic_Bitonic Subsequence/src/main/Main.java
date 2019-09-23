package main;

/*
 * This problem is a variation of standard Longest Increasing Subsequence (LIS) problem. 
 * Let the input array be arr[] of length n. 
 * We need to construct two arrays lis[] and lds[] using DP solution of LIS problem. 
 * lis[i] stores the length of the Longest Increasing subsequence ending with arr[i]. 
 * lds[i] stores the length of the longest Decreasing subsequence starting from arr[i]. 
 * Finally, we need to return the max value of lis[i] + lds[i] – 1 where i is from 0 to n-1
 */

/*
 * Logic for length and sum is exactly same. In sum use nums[i] and
 * in length use 1
 */

class Solution {
	public int max_bitonic_length(int []nums) {
		int n = nums.length;
		int []LIS = new int[n];
		int []LDS = new int[n];
		for(int i = 0;i < LIS.length;i++) {
			LIS[i] = 1;
			LDS[i] = 1;
		}
		
		for(int i = 1;i < LIS.length;i++) {
			for(int j = 0;j < i;j++) {
				if(nums[i] > nums[j] && LIS[i] < 1 + LIS[j])
					LIS[i] = 1 + LIS[j];
			}
		}
		//print(LIS);
		for(int i = LDS.length - 2;i >= 0;i--) {
			for(int j = LDS.length-1;j > i;j--) {
				if(nums[i] > nums[j] && LDS[i] <  1 + LDS[j])
					LDS[i] = 1 + LDS[j];
			}
		}
		//print(LDS);
		
		int max = Integer.MIN_VALUE;
		for(int i = 0;i < LIS.length;i++) {
			max = Math.max(max, LIS[i] + LDS[i] - 1);
		}
		
		return max;
	}
	
	
	int max_bitonic_sum(int []arr) {
		int n = arr.length;
		int []LIS = new int[n];
		int []LDS = new int[n];
		LIS[0] = arr[0];
		LDS[n-1] = arr[n-1];
		for(int i = 1;i < n;i++) {
			for(int j = 0;j < i;j++) {
				if(arr[i] > arr[j] && LIS[i] < LIS[j] + arr[i]) {
					LIS[i] = LIS[j] + arr[i];
				}
			}
		}
		//print(LIS);
		for(int i = n-2;i >= 0;i--) {
			for(int j = n-1;j > i;j--) {
				if(arr[i] > arr[j] && LDS[i] < LDS[j] + arr[i])
					LDS[i] = LDS[j] + arr[i];
			}
		}
		int max = Integer.MIN_VALUE;
		for(int i = 0;i < n;i++)
			max = Math.max(max, LIS[i] + LDS[i] - arr[i]);
		return max;
	}
	
	void print(int []arr) {
		for(int i : arr)
			System.out.print(i + " ");
		System.out.println();
	}
}

public class Main {
	public static void main(String[] args) {
		//int []nums = {1, 11, 2, 10, 4, 5, 2, 1};
		//int []nums = {12, 23, 35, 46, 57, 68, 70, 81, 93};
		int []nums = { 1, 15, 51, 45, 33, 100, 12, 18, 9 };
		System.out.println(new Solution().max_bitonic_length(nums));
		System.out.println(new Solution().max_bitonic_sum(nums));
		
	}
}