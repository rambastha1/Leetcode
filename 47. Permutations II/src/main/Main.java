package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
    	List<List<Integer>> res = new ArrayList<>();
    	if(nums == null || nums.length == 0)
    		return res;
    	boolean []used = new boolean[nums.length];
    	
    	// count array better for negative numbers
    	Map<Integer, Integer> map = new HashMap<>();
    	for(int num : nums)
    		map.put(num, map.getOrDefault(num, 0)+1);
    	
    	Arrays.sort(nums);
    	//dfs(nums, res, new ArrayList<>(), used);
    	dfs(nums, res, new ArrayList<>(), map);
    	return res;	
    }
    
    void dfs(int []nums, List<List<Integer>> res, List<Integer> curr, Map<Integer, Integer> map) {
    	if(curr.size() == nums.length) {
			res.add(new ArrayList<>(curr));
			return;
    	}
    	for(int num : map.keySet()) {
    		if(map.get(num) <= 0)
    			continue;
    		map.put(num, map.getOrDefault(num, 0)-1);
    		curr.add(num);
    		dfs(nums, res, curr, map);
    		curr.remove(curr.size()-1);
    		map.put(num, map.getOrDefault(num, 0)+1);
    	}
    }
    
    // complex method
    void dfs(int []nums, List<List<Integer>> res, List<Integer> curr, boolean []used) {
    	if(curr.size() == nums.length) {
			res.add(new ArrayList<>(curr));
			return;
    	}
    	for(int i = 0;i < nums.length;i++) {
    		if(used[i])
    			continue;
    		if(i > 0 && nums[i-1] == nums[i] && !used[i-1])
    			continue;
    		used[i] = true;
    		curr.add(nums[i]);
    		dfs(nums, res, curr, used);
    		curr.remove(curr.size()-1);
    		used[i] = false;
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,1,2};
		System.out.println(new Solution().permuteUnique(nums));
	}
}
