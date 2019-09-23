package main;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
    	int []nums = {1,2,3,4,5,6,7,8,9};
    	List<List<Integer>> res = new ArrayList<>();
    	List<Integer> curr = new ArrayList<>();
    	dfs(nums, n, k, res, curr, 0, 0);
    	return res;
    }
    
    void dfs(int []nums, int target, int k, List<List<Integer>> res, List<Integer> curr, 
    		int sum, int index) {
    	if(index > nums.length)
    		return;
    	if(sum == target && curr.size() == k) {
    		res.add(new ArrayList<>(curr));
    		return;
    	}
    	
    	for(int i = index;i < nums.length;i++) {
    		if(curr.contains(nums[i]))
    			continue;
    		curr.add(nums[i]);
    		sum += nums[i];
    		dfs(nums, target, k, res, curr, sum, i+1);
    		curr.remove(curr.size()-1);
    		sum -= nums[i];
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 6, k = 2;
		System.out.println(new Solution().combinationSum3(k, n));
	}
}