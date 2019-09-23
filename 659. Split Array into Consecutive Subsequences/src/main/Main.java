package main;

// https://leetcode.com/problems/split-array-into-consecutive-subsequences/discuss/106495/Java-O(n)-time-and-O(1)-space-solution-greedily-extending-shorter-subsequence

class Solution {
	public boolean isPossible(int[] nums) {
	    int pre = Integer.MIN_VALUE, p1 = 0, p2 = 0, p3 = 0;
	    int curr = 0, count = 0, c1 = 0, c2 = 0, c3 = 0;
	    
	    for (int i = 0; i < nums.length;) {
	    	curr = nums[i];
	    	count = 0;
	    	while (i < nums.length && curr == nums[i]) {
	    		count++;
	    		i++;
	    	}
	        
	    	if (curr != pre + 1) {
	            if (p1 != 0 || p2 != 0) return false;
	            c1 = count; c2 = 0; c3 = 0;
	        } else {
	            if (count < p1 + p2) return false;
	            c1 = Math.max(0, count - (p1 + p2 + p3));
	            c2 = p1;
	            c3 = p2 + Math.min(p3, count - (p1 + p2));
	        }
	    	
	    	pre = curr;
	    	p1 = c1;
	    	p2 = c2;
	    	p3 = c3;
	    }
	    return (p1 == 0 && p2 == 0);
	}
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,2,3,3,4,4,5,5};
		System.out.println(new Solution().isPossible(nums));
	}
}
