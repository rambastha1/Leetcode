package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
	public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, new ArrayList<>(), res, 0);
        return res;
    }
    
    private void dfs(int[] nums, List<Integer> curr, List<List<Integer>> res, int index) {
        if(curr.size() >= 2) {
            res.add(new ArrayList<>(curr));
        }
        if(index >= nums.length) {
            return;
        }
        Set<Integer> set = new HashSet<>();
        for(int i = index; i < nums.length; i++) {
            if(curr.size() == 0 || nums[i] >= curr.get(curr.size()-1)) {
                if(!set.contains(nums[i])) {
                    curr.add(nums[i]);
                    set.add(nums[i]);
                    dfs(nums, curr, res, i+1);
                    curr.remove(curr.size()-1);
                }
            }
        }
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {4,6,7,7};
		//int []nums = {-8,28,68,-54,96,97,84,-32,8,-87,1,-7,-20,12,22};
		System.out.println(new Solution().findSubsequences(nums));
	}
}
