package main;

class Solution {
	public int movesToMakeZigzag(int[] nums) {
        int n = nums.length;
        if(n <= 1)
            return 0;
        int even = 0, odd = 0;
        
        // make even indexed elements smaller than neighbors
        for(int i = 0;i < n;i += 2) {
            int left = i-1 == -1?Integer.MAX_VALUE:nums[i-1];
            int right = i+1 == n?Integer.MAX_VALUE:nums[i+1];
            if(left > nums[i] && nums[i] < right)
                continue;
            int min = Math.min(left, right);
            even += 1 + Math.abs(nums[i] - min);
        }
        
        // make even indexed elements smaller than neighbors
        for(int i = 1;i < n;i += 2) {
        	int left = i-1 == -1?Integer.MAX_VALUE:nums[i-1];
            int right = i+1 == n?Integer.MAX_VALUE:nums[i+1];
            
            if(left > nums[i] && nums[i] < right)
                continue;
            int min = Math.min(left, right);
            odd += 1 + Math.abs(nums[i] - min);
        }
        return Math.min(odd, even);
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {2,7,10,9,8,9};
		System.out.println(new Solution().movesToMakeZigzag(nums));
	}
}
