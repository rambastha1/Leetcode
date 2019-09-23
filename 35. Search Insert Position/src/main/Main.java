package main;

class Solution {
    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return 0;
        if(target > nums[nums.length-1])
            return nums.length;
        if(target < nums[0])
            return 0;
        
        int l = 0, r = nums.length-1;
        while(r - l > 1) {
            int m = (l + r)/2;
            if(nums[m] <= target)
                l = m;
            else
                r = m;
        }
        if(nums[l] != target)
            return l+1;
        return l;        
    }
}

public class Main {
	public static void main(String[] args) {		
		int []nums = {1, 3, 5, 6};
		int target = 5;
		System.out.println(new Solution().searchInsert(nums, target));
	}
}
