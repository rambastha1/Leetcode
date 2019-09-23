package main;

import java.util.Random;

//https://leetcode.com/problems/random-pick-index/discuss/88072/Simple-Reservoir-Sampling-solution
class Solution {
	
	int []nums;
	
    public Solution(int[] nums) {
    	this.nums = nums;
    }
    
    public int pick(int target) {
        Random r = new Random();
        int result = -1;
        //Count is used to count the target number in nums
        int count = 0;
        for(int i = 0;i < nums.length;i++) {
        	if(nums[i] != target)
        		continue;
        	if(r.nextInt(++count) == 0)
        		result = i;
        }
        return result;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */

public class Main {
	public static void main(String[] args) {
		int []nums = {1,2,3,3,3};
		Solution s = new Solution(nums);
		System.out.println(s.pick(3));
	}
}