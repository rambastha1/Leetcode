package main;
import java.util.Stack;

// https://leetcode.com/problems/132-pattern/discuss/94071/Single-pass-C%2B%2B-O(n)-space-and-time-solution-(8-lines)-with-detailed-explanation.
class Solution {
	
	// Top of stack maintains s2(not always), we keep track of s3 and find a number less than s3
	/* when nums[i] > s3 but less than stak.peek(), just push it, s3 will act as s2.
	 * here s3 does work of both s2 and s3
	 */
	public boolean find132pattern(int[] nums) {
		int s3 = Integer.MIN_VALUE, n = nums.length;
		Stack<Integer> stack = new Stack<>();
		for(int i = n-1;i >= 0;i--) {
			if(nums[i] < s3)
				return true;
			else {
				while(!stack.isEmpty() && nums[i] > stack.peek()) { // s2 = nums[i]
					s3 = stack.pop();
				}
			}
			stack.push(nums[i]);
		}
		return false;
	}
	
	// Time 0(N^2)
    public boolean find132pattern1(int[] nums) {
    	if(nums == null || nums.length < 3)
    		return false;
    	int n = nums.length;
    	for(int i = 0;i < n-2;i++) {
    		int biggest = nums[i];
    		for(int j = i+1;j < n;j++) {
    			if(nums[j] <= nums[i])
    				continue;
    			else if(nums[j] >= biggest)
    				biggest = nums[j];
    			else
    				return true;
    		}
    	}
    	return false;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []nums = {1, 2, 3, 4};
		//int []nums = {3,1,4,2};
		int []nums = {-1,3,2,0};
		System.out.println(new Solution().find132pattern(nums));
	}
}
