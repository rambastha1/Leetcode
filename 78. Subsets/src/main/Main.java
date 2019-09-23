package main;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
    	List<List<Integer>> res = new ArrayList<>();
    	dfs(nums, res, new ArrayList<>(), 0, nums.length-1);
    	return res;
    }
    
    void dfs(int []nums, List<List<Integer>> res, List<Integer> curr, int start, int end) {
    	
    	res.add(new ArrayList<>(curr));
    	for(int i = start;i <= end;i++) {
    		if(curr.contains(nums[i]))
    			continue;
    		curr.add(nums[i]);
    		dfs(nums, res, curr, i+1, end);
    		curr.remove(curr.size()-1);
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,2,3};
		System.out.println(new Solution().subsets(nums));
	}
}
