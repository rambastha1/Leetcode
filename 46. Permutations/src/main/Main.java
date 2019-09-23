package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)

class Solution {
	//dfs method
    public List<List<Integer>> permute(int[] nums) {
    	List<List<Integer>> res = new ArrayList<>();
    	List<Integer> curr = new ArrayList<>();
    	util(nums, res, curr);
    	return res;
    }
    
    void util(int []nums, List<List<Integer>> res, List<Integer> curr) {
    	if(curr.size() == nums.length)
    		res.add(new ArrayList<>(curr));
    	for(int i = 0;i < nums.length;i++) {
    		if(curr.contains(nums[i]))
    			continue;
    		curr.add(nums[i]);
    		util(nums, res, curr);
    		curr.remove(curr.size()-1);
    	}
    }
    
    
    //Another Method
    //backtracking
    public List<List<Integer>> perm(int[] nums) {
    	List<List<Integer>> res = new ArrayList<>();
    	List<Integer> curr = new ArrayList<>();
    	util1(nums,res, 0);
    	return res;
    }
    
    void util1(int []nums, List<List<Integer>> res, int index) {
    	if(index == nums.length) {
    		List<Integer> curr = new ArrayList<>();
    		for(int i= 0;i < nums.length;i++)
    			curr.add(nums[i]);
    		res.add(new ArrayList<>(curr));
    	}
    		
    	for(int i = index;i < nums.length;i++) {
    		swap(nums, index, i);
    		util1(nums, res, index+1);
    		swap(nums, index, i);
    	}    		
    }
    
    void swap(int []nums, int a,int b) {
    	int temp = nums[a];
    	nums[a] = nums[b];
    	nums[b] = temp;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,2,3};
		System.out.println(new Solution().permute(nums));
		System.out.println(new Solution().perm(nums));
	}
}