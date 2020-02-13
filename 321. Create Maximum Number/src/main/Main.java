package main;

import java.util.Arrays;
import java.util.Stack;

class Solution {
	/* First, we divide the k digits required into two parts, i and k-i. We then find the maximum number of length i in one 
	 * array and the maximum number of length k-i in the other array using the algorithm in section 1. 
	 * Now we combine the two results in to one array using the algorithm in section 2. 
	 * After that we compare the result with the result we have and keep the larger one as final answer.
	 * time 0((m+n)^3)
	 * https://web.archive.org/web/20160120093629/http://algobox.org/create-maximum-number/
	 * https://leetcode.com/problems/create-maximum-number/discuss/77285/Share-my-greedy-solution
	 */
	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
	    int n = nums1.length;
	    int m = nums2.length;
	    int[] ans = new int[k];
	    for (int i = Math.max(0, k - m); i <= k && i <= n; ++i) {
	        int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, k - i), k);
	        if (greater(candidate, 0, ans, 0)) ans = candidate;
	    }
	    return ans;
	}
	 
	private int[] merge(int[] nums1, int[] nums2, int k) {
	    int[] ans = new int[k];
	    for (int i = 0, j = 0, r = 0; r < k; ++r)
	        ans[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
	    return ans;
	}
	public boolean greater(int[] nums1, int i, int[] nums2, int j) {
	    while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
	        i++;
	        j++;
	    }
	    return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
	}

	// Given one array of length n, create the maximum number of length k.
	public int[] maxArray(int[] nums, int k) {
	    int n = nums.length;
	    Stack<Integer> s = new Stack<>();
	    for(int i = 0;i < n;i++) {
	    	while(!s.isEmpty() && n-i > k-s.size() && nums[i] > s.peek())
	    		s.pop();
	    	if(s.size() < k)
	    		s.push(nums[i]);
	    }
	    int[] ans = new int[k];
	    int i = 0;
	    for(int num : s)
	    	ans[i++] = num;
	    return ans;
	}
}

public class Main {
	public static void main(String[] args) {
		int []nums1 = {6,7}, nums2 = {6,0,4};
		int k = 5;
		System.out.println(Arrays.toString(new Solution().maxNumber(nums1, nums2, k)));
	}
}
