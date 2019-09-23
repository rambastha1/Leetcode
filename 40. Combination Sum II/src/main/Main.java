package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	
	// Time 0(2^N) No of Combinations Space 0(N)
	/* Sorting is to make sure that each output list is sorted to avoid the repeated 
	 * combinations in the form like [1 2 3], [3 2 1]
	 * https://www.geeksforgeeks.org/subset-sum-backtracking-4/
	 */
	
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    	List<List<Integer>> res = new ArrayList<>();
    	List<Integer> curr = new ArrayList<>();
    	Arrays.sort(candidates);
    	dfs(candidates, target, res, curr, 0, 0);
    	return res;
    }
    
    public void dfs(int[] candidates, int target, List<List<Integer>> res, List<Integer> curr,
    		int sum, int index) {
    	if(sum > target)
    		return;
    	if(sum == target) {
    		res.add(new ArrayList<>(curr));
    		return;
    	}    	
    	for(int i = index;i < candidates.length;i++) {
    		/* Skip Duplicates
    		 * if i = start , then i-1 = start -1, remember that the first number in this level to try is 
    		 * candidates[start], so candidatres[start -1] is invalid.
    		 */
    		if(i > index && candidates[i] == candidates[i-1])
    			continue;
    		curr.add(candidates[i]);
    		sum += candidates[i];
    		dfs(candidates, target, res, curr, sum, i+1);
    		curr.remove(curr.size()-1);
    		sum -= candidates[i];
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int []candidates = {10,1,2,7,6,1,5};
		int target = 8;
		System.out.println(new Solution().combinationSum2(candidates, target));
	}
}