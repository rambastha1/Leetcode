package main;

import java.util.Arrays;

class Solution {
	
	//0(NlgN) method
	/*public int findDuplicate(int[] nums) {
		if(nums.length == 0 || nums.length == 1)
			return -1;
		Arrays.sort(nums);
		for(int i= 1;i < nums.length;i++)
			if(nums[i] == nums[i-1])
				return nums[i];
		return -1;
	}*/
    
	
	//0(NlgN) without changing array
	//Brilliant Solution
	public int findDuplicate(int[] nums) {
		int l = 1, r = nums.length - 1;
		while(l <= r) {
			int c = 0;
			int m = l + (r-l)/2;
			for(int a:nums)
				if(a <= m)
					c++;
			if(c <= m)
				l = m+1;
			else
				r = m-1;
		}
		return l;
    }
}

public class Main {

	public static void main(String[] args) {
		int []nums = {1, 3, 4, 2, 2};
		//int []nums = {3, 1, 3, 4, 2};
		//int []nums = {2, 2, 2, 2, 2};
		System.out.println(new Solution().findDuplicate(nums));
	}
}