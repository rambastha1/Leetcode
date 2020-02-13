package main;

import java.util.Arrays;
// https://leetcode.com/problems/reverse-pairs/discuss/97268/General-principles-behind-problems-similar-to-%22Reverse-Pairs%22
class Solution {
	public int reversePairs(int[] nums) {
	    int res = 0;
	    int[] copy = Arrays.copyOf(nums, nums.length);
	    int[] bit = new int[copy.length + 1];
	    
	    Arrays.sort(copy);
	    
	    for (int ele : nums) {
	        res += sum(bit, index(copy, 2L * ele + 1));
	        update(bit, index(copy, ele));
	    }
	    
	    return res;
	}

	private int index(int[] arr, long val) {
	    int l = 0, r = arr.length - 1, m = 0;
	    	
	    while (l <= r) {
	    	m = l + ((r - l) >> 1);
	    		
	    	if (arr[m] >= val) {
	    	    r = m - 1;
	    	} else {
	    	    l = m + 1;
	    	}
	    }
	    
	    return l + 1;
	}
    
    int sum(int []bit, int i) {
    	//i++;
    	int sum = 0;
    	while(i < bit.length) {
    		sum += bit[i];
    		i += i & (-i);
    	}
    	return sum;
    }
    
    void update(int []bit, int i) {
    	//i++;
    	while(i > 0) {
    		bit[i]++;
    		i -= i & (-i);
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,3,2,3,1};
		System.out.println(new Solution().reversePairs(nums));
	}
}
