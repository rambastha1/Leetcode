package main;

import java.util.HashSet;
import java.util.Set;

/*
 * Given an array with n integers, you need to find if there are triplets (i, j, k) which 
 * satisfies following conditions:
 * 0 < i, i + 1 < j, j + 1 < k < n - 1
 * Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
 * where we define that subarray (L, R) represents a slice of the original array starting 
 * from the element indexed L to the element indexed R.
 * 
 * Example:
 * Input: [1,2,1,2,1,2,1]
 * Output: True
 * 
 * Explanation:
 * i = 1, j = 3, k = 5. 
 * sum(0, i - 1) = sum(0, 0) = 1
 * sum(i + 1, j - 1) = sum(2, 2) = 1
 * sum(j + 1, k - 1) = sum(4, 4) = 1
 * sum(k + 1, n - 1) = sum(6, 6) = 1
 * Note:
 * 1 <= n <= 2000.
 * Elements in the given array will be in range [-1,000,000, 1,000,000].
 */

/* idea is to find two equal sums 0-i-1 and i+1 - j-1 store into set
 * find same sum for j+1 to k-1 and k+1 to len-1
 * if present return true
 */

class Solution {
	public boolean splitArray(int[] nums) {
		int n = nums.length;
		int []prefix = new int[n];
		prefix[0] = nums[0];
		for(int i = 1;i < n;i++) {
			prefix[i] = prefix[i-1] + nums[i];
		}
		
		// 0 ~ i-1  |  i+1 ~ j-1  |  j+1 ~ k-1  |  k+1 ~ n-1
		for(int i = 1;i <= n-6;i++) {
			if(i != 1 && nums[i] == 0 && nums[i-1] == 0)
				continue;
			for(int j = i+2;j <= n-4;j++) {
				if(prefix[i-1] != prefix[j-1] - prefix[i])
					continue;
				for(int k = j+2;k <= n-2;k++) {
					int sum3 = prefix[k-1] - prefix[j];
					int sum4 = prefix[n-1] - prefix[k];
					if(sum3 == sum4 && sum3 == prefix[i-1])
						return true; 
				}
			}
		}
		return false;
	}
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,2,1,2,1,2,1};
		System.out.println(new Solution().splitArray(nums));
	}
}
