package main;

/* "Using the given numbers 1, 2 and 4, we can already build all sums from 0 to 7"
 * it's intuitive to see that they are binary 01, 10, 100, so anything below 111b is do able.
 * Let miss be the smallest sum in [0,n] that we might be missing. Meaning we already know we can build all sums in [0,miss). 
 * Then if we have a number num <= miss in the given array, we can add it to those smaller sums to build all sums in [0,miss+num). 
 * If we don't, then we must add such a number to the array, and it's best to add miss itself, to maximize the reach.
 * 
 * https://leetcode.com/problems/patching-array/discuss/78488/Solution-%2B-explanation
 */

class Solution {
	/* I think it should be O(log(n)), because the worst case is when nums is empty, 
	 * the miss will double itself in each iteration until larger than n.
	 * 
	 * for array [1,1,1,1,1,1,1,...] n = some large number
	 * thus 0(lgN + nums.length)
	 */
    public int minPatches(int[] nums, int n) {
    	// miss is long because if n = 2^31 -1 infinite loop
    	long miss = 1;
    	int i = 0, ans = 0;
    	while(miss <= n) {
    		// i < length is check for empty array 
    		if(i < nums.length && nums[i] <= miss) {
    			miss += nums[i++];
    		} else {
    			// we add miss for maximum reach greedy since binary all sums [0, miss+miss) is possible 
    			miss += miss;
    			ans++;
    		}
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {};
		int n = 7;
		System.out.println(new Solution().minPatches(nums, n));
	}
}
