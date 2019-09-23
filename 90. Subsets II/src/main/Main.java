package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

class Solution {
	// Time 0(n!)
	public List<List<Integer>> subsetsWithDup(int[] nums) {
    	List<List<Integer>> res = new ArrayList<>();
    	if(nums == null || nums.length == 0)
    		return res;
    	Arrays.sort(nums);
    	dfs(nums, res, new ArrayList<>(), 0);
    	return res;
    }
    
    void dfs(int []nums, List<List<Integer>> res, List<Integer> curr, int index) {
    	if(index > nums.length)
    		return;
    	res.add(new ArrayList<>(curr));
    	for(int i = index;i < nums.length;i++) {
    		/* Avoid index out of bound for getting nums[i-1] operation.
    		 * only skip the iteration when the duplicated elements has been counted.
    		 * (only two cases exist, i == index and i > index, the case when i == index will 
    		 * only happen once and this time, it will be considered, otherwise, it will not be considered. )
    		 */
    		if(i > index && nums[i] == nums[i-1])
    			continue;
    		curr.add(nums[i]);
    		dfs(nums, res, curr, i+1);
    		curr.remove(curr.size()-1);
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,2,2};
		System.out.println(new Solution().subsetsWithDup(nums));
	}
}
