package main;

class Solution {
	public int missing_number(int []nums) {
		int l = 0, r = nums.length-1;
		while(r - l > 1) {
			int m = l + (r-l)/2;
			/* We intend to find m-1 index element which is not in correct position
			 * nums[m] should be equal to m + 1 ideal if no element is missing
			 * thus nums[m] != m+1 is checked. It is not in correct position
			 * It checks whether nums[m-1] is in correct position or not
			 * If not then nums[m] is not in correct position
			 * means nums[m] != m+1 && nums[m-1] == m
			 * 
			 * If Both are not if current is not, there is probability that an element
			 * earlier than this is also not in correct position thus r = m-1
			 * 
			 * If this is in correct position check right side
			 */
			if(nums[m] != m+1 && nums[m-1] == m)
				return m+1;
			else if(nums[m] != m+1)
				r = m - 1;
			else
				l = m + 1;
		}		
		return -1;
	}
}

public class Main {
	public static void main(String[] args) {
		//int []nums = {1,3,4,5};
		int []nums = {1,2,3,4,5,7,8};
		System.out.println(new Solution().missing_number(nums));
	}
}