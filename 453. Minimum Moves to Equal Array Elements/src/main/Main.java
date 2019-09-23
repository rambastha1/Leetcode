package main;

// https://leetcode.com/problems/minimum-moves-to-equal-array-elements/discuss/93817/It-is-a-math-question
class Solution {
    public int minMoves(int[] nums) {
    	int moves = 0, sum = 0, min = Integer.MAX_VALUE;
    	for(int i : nums) {
    		sum += i;
    		min = Math.min(min, i);
    	}
    	return sum - min*nums.length;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,2,3};
		System.out.println(new Solution().minMoves(nums));
	}
}