package main;
// https://www.geeksforgeeks.org/find-the-smallest-positive-number-missing-from-an-unsorted-array-set-2/
// https://leetcode.com/problems/first-missing-positive/discuss/17071/My-short-c%2B%2B-solution-O(1)-space-and-O(n)-time
class Solution {
	// 0(N) time and 0(1) space
	public int firstMissingPositive(int[] nums) {
		int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
        	// consider only positive numbers and numbers fit in array
        	// if already at right place or correct slot of this number already has right number i.e duplicates
            if (nums[i] <= 0 || nums[i] > nums.length || nums[i] == i + 1 || nums[nums[i]-1] == nums[i]) 
                continue;
            swap(nums, i, nums[i] - 1);
            // check this value again
            i--;
        }
        
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }
    
    private void swap(int []nums, int a,int b) {
    	int temp = nums[a];
    	nums[a] = nums[b];
    	nums[b] = temp;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {3,4,-1,1};
		System.out.println(new Solution().firstMissingPositive(nums));
	}
}
