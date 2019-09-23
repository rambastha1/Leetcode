package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* Given an int array nums and an int target, find how many unique pairs in the array such that their sum is equal to target. 
 * Return the number of pairs.

Example 1:

Input: nums = [1, 1, 2, 45, 46, 46], target = 47
Output: 2
Explanation:
1 + 46 = 47
2 + 45 = 47

Example 2:

Input: nums = [1, 1], target = 2 
Output: 1
Explanation:
1 + 1 = 2
 * 
 */

class Solution {
	public int getUniquePairs(int[] nums, int target) {
		Set<List<Integer>> set = new HashSet<>();
		// sum -> list.of indices 
		Map<Integer, List<Integer>> map = new HashMap<>();
		int n = nums.length;
		for(int i = 0;i < n;i++) {
			if(map.containsKey(target - nums[i])) {
				for(int p : map.get(target-nums[i])) {
					set.add(Arrays.asList(nums[p], nums[i]));
				}
			}
			
			if(!map.containsKey(nums[i]))
				map.put(nums[i], new ArrayList<>());
			map.get(nums[i]).add(i);
		}
		return set.size();
	}
}


public class Main {
	public static void main(String[] args) {
		int []nums = {1, 1, 2, 45, 46, 46};
		int target = 47;
		System.out.println(new Solution().getUniquePairs(nums, target));
	}
}
