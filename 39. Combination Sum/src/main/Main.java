package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Sorting is to make sure that each output list is sorted to avoid the repeated 
 * combinations in the form like [1 2 3], [3 2 1]
 */

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	List<List<Integer>> res = new ArrayList();
    	List<Integer> curr = new ArrayList<>();
    	Arrays.sort(candidates);
    	dfs(candidates, target, res, curr, 0, 0);
    	return res;
    }
    
    public void dfs(int[] candidates, int target, List<List<Integer>> res, List<Integer> curr,
    		int sum, int index){
    	
    	if(sum > target)
    		return;
    	else if(sum == target) {
    		res.add(new ArrayList<>(curr));
    		return;
    	}
    	for(int i = index;i < candidates.length;i++) {
    		curr.add(candidates[i]);
    		sum+= candidates[i];
    		dfs(candidates, target, res, curr, sum, i);
    		curr.remove(curr.size()-1);
    		sum-= candidates[i];
    	} 	
    }
}

public class Main {
	public static void main(String[] args) {
		int []candidates = {2,3,6,7};
		int target = 7;
		System.out.println(new Solution().combinationSum(candidates, target));
	}
}