package main;

import java.util.HashMap;
import java.util.Map;

/*
 * If the depth of a tree is smaller than 5, then this tree can be represented by a list of 
 * three-digits integers.
 * 
 * For each integer in this list:
 * The hundreds digit represents the depth D of this node, 1 <= D <= 4.
 * The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. 
 * The position is the same as that in a full binary tree.
 * The units digit represents the value V of this node, 0 <= V <= 9.
 * Given a list of ascending three-digits integers representing a binary with the depth smaller 
 * than 5. You need to return the sum of all paths from the root towards the leaves.
 * 
 * Example 1:
 * Input: [113, 215, 221]
 * Output: 12
 * Explanation: 
 * The tree that the list represents is:
    3
   / \
  5   1

 * The path sum is (3 + 5) + (3 + 1) = 12.
 * 
 * Example 2:
 * Input: [113, 221]
 * Output: 4
 * Explanation: 
 * The tree that the list represents is: 
    3
     \
      1

 * The path sum is (3 + 1) = 4.
 */

class Solution {
    int ans = 0;
    public int pathSum(int[] nums) {
    	if(nums == null || nums.length == 0)
    		return 0;
    	if(nums.length == 1)
    		return nums[0];
    	Map<Integer, Integer> map = new HashMap<>();
    	for(int num : nums)
    		map.put(num/10, num%10);
    	dfs(nums[0]/10, map, 0);    	
    	return ans;
    }
    
    void dfs(int node, Map<Integer, Integer> map, int sum) {
    	if(!map.containsKey(node))
    		return;
    	
    	sum += map.get(node);
    	int depth = node/10, pos = node%10;
    	int left = (depth+1)*10 + 2*pos-1, right = left + 1;
    	if(!map.containsKey(left) && !map.containsKey(right)) {
    		ans+= sum;
    	} else {
    		dfs(left, map, sum);
    		dfs(right, map, sum);
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {113, 215, 221};
		System.out.println(new Solution().pathSum(nums));
	}
}