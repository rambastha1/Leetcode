package main;

class Solution {
	
	/*
	 * DP solution
	 * Time: 0(N^2) Spcae 0(N)
	 */
	
	public boolean canJump1(int[] nums) {
		int len = nums.length;
		boolean []res =  new boolean[len];
		res[len-1] = true;
		for(int i = len-2;i >= 0;i--) {
			int jump = Math.min(i+nums[i], len-1);
			for(int j = i+1;j <= jump;j++) {
				if(res[j]) {
					res[i] = true;
					break;
				}
			}
		}		
		return res[0];
	}
	
	/*
	 * Greedy Solution
	 * Time 0(N) constant space
	 */
	
	public boolean canJump(int[] nums) {
		int lastpos = nums.length-1;
		for(int i = nums.length-1;i >= 0;i--) {
			if(i + nums[i] >= lastpos)
				lastpos = i;
		}
		return lastpos==0;
	}
	
	/*
	 * Back tracking method
	 * Time limit exceeded for large values
	 */
	
    /*public boolean canJump(int[] nums) {
    	return canJumpUtil(nums, 0);
    }
    
    boolean canJumpUtil(int []nums, int index) {
    	if(index >= nums.length -1)
    		return true;
    	for(int x = nums[index];x > 0;x--) {
    		index += x;
    		if(canJumpUtil(nums, index))
    			return true;
    		index -= x;
    	}
    	return false;
    }*/
}

public class Main {
	public static void main(String[] args) {
		int []nums = {2,3,1,1,4};
		//int []nums = {3,2,1,0,4};
		//int []nums = {1,2,3};
		System.out.println(new Solution().canJump(nums));
	}
}