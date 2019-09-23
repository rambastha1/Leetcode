package main;

import java.util.Arrays;

/* Given an array of  n  integers  nums  and a  target , find the number of index triplets  i, j, k 
 * with  0 <= i < j < k < n that satisfy the condition  nums[i] + nums[j] + nums[k] < target.
 *  
 * For example, given  nums  =  [-2, 0, 1, 3], and  target  = 2.
 * Return 2. Because there are two triplets which sums are less than 2:
 * 
 * [-2, 0, 1]
 * [-2, 0, 3]
 * Follow up:
 * Could you solve it in  O ( n 2 ) runtime?
 */

class Solution {
	
	/* Sliding window finding maximum r upto which sum < target i.e. expand window
	 * increment counter till r. when sum >= target shift left counter or shrink window 
	 */
	
    public int threeSumSmaller(int[] nums, int target) {
    	if(nums == null || nums.length == 0)
    		return 0;
    	int n = nums.length;
    	Arrays.sort(nums);
    	
    	int count = 0;
    	for(int i = 0;i < n-2;i++) {
    		int l = i+1, r = n-1;
    		while(l < r) {
    			int sum = nums[i] + nums[l] + nums[r];
    			if(sum >= target)
    				r--;
    			else {
    				/* if arr[i] + arr[l] + arr[r] makes total sum less than sum asked in problem and 
    				 * we know that arr[r] is the largest number in the array therefore, we can directly 
    				 * say that if adding larger number doesn't false the condition then the elements in 
    				 * between l and r would not false the condition. 
    				 */
    				count += r-l;
    				l++;
    			}
    		}
    	}
    	return count;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int []nums = {-2, 0, 1, 3};
		int target = 2;*/
		int []nums = {5, 1, 3, 4, 7};
		int target = 12;
		System.out.println(new Solution().threeSumSmaller(nums, target));
	}
}
