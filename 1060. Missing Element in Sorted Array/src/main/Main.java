package main;

/* Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.
Example 1:

Input: A = [4,7,9,10], K = 1
Output: 5
Explanation: 
The first missing number is 5.
Example 2:

Input: A = [4,7,9,10], K = 3
Output: 8
Explanation: 
The missing numbers are [5,6,8,...], hence the third missing number is 8.
Example 3:

Input: A = [1,2,4], K = 3
Output: 6
Explanation: 
The missing numbers are [3,5,6,7,...], hence the third missing number is 6.
 

Note:

1 <= A.length <= 50000
1 <= A[i] <= 1e7
1 <= K <= 1e8
 * 
 */

class Solution {
	public int missingElement(int[] nums, int k) {
    	int n = nums.length;
    	int l = 0, r = n-1;
    	// missing = total values from nums[0] to nums[n-1] - total present in array
    	int missing = nums[n-1] - nums[0] - (n-1);
    	// missing k is larger than total numbers missing in this range
    	if(missing < k)
    		return nums[n-1] + k - missing;
    	
    	while(r-l > 1) {
    		int m = l + (r-l)/2;
    		int mis = nums[m] - nums[l] - (m-l);
    		if(mis >= k)
    			// when the number is larger than k, then the index won't be located in (m, h]
    			r = m;
    		else {
    			// when the number is smaller than k, then the index won't be located in [l, m), update k -= missing
    			l = m;
    			k -= mis;
    		}
    	}
    	return nums[l] + k;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int []nums = {4,7,9,10};
		int k = 1;*/
		int []nums = {1,2,4};
		int k = 3;
		System.out.println(new Solution().missingElement(nums, k));
	}
}