package main;

class Solution {
	
	public int numberOfSubarrays(int[] nums, int k) {
    	int n = nums.length;
    	return subarray(nums, k) - subarray(nums, k-1);
    }
    
    private int subarray(int []nums, int k) {
    	int n = nums.length;
    	int sum = 0, start = 0, ans = 0;
    	for(int end = 0;end < n;end++) {
    		sum += nums[end]%2==1?1:0;
    		while(sum > k) {
    			sum -= nums[start++]%2==1?1:0;
    		}
    		ans += end - start + 1;
    	}
    	return ans;
    }
    
    
}

public class Main {
	public static void main(String[] args) {
		int []nums = {2,2,2,1,2,2,1,2,2,2};
		int k = 2;
		System.out.println(new Solution().numberOfSubarrays(nums, k));
	}
}
