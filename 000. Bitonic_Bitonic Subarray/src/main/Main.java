package main;


class Solution {
	
	//It looks 0(N^2) but it is 0(N) Constant Space
	int max_bitonic_length(int []nums) {
		int start =  0, next_start = 0, maxlen = 1;
		int i = 0;
		
		while(i < nums.length - 1) {
			//nums.length-1 is used for boundary condition
			while(i < nums.length-1 && nums[i] <= nums[i+1])
				i++;
			while(i < nums.length-1 && nums[i] >= nums[i+1]) {
				if(i < nums.length-1 && nums[i] > nums[i+1])
					next_start = i+1;
				i++;
			}
			
			maxlen = Math.max(maxlen, i-start+1);
			start = next_start;
			i++;
		}
		return maxlen;
	}
	
	//Time 0(N) space 0(N)
	int max_bitonic_sum(int []nums) {
		
		int n = nums.length;
		int []LIS = new int[n];
		int []LDS = new int[n];
		LIS[0] = nums[0];
		LDS[n-1] = nums[n-1];
		for(int i = 1;i < n;i++) {
			if(nums[i] > nums[i-1])
				LIS[i] = LIS[i-1] + nums[i];
			else
				LIS[i] = nums[i];
				
		}
		
		for(int i = n-2;i >= 0;i--) {
			if(nums[i] > nums[i+1])
				LDS[i] = LDS[i+1] + nums[i];
			else
				LDS[i] = nums[i];
		}
		
		int max_sum = Integer.MIN_VALUE;
		for(int i = 0;i < n;i++)
			max_sum = Math.max(max_sum, LIS[i] + LDS[i] - nums[i]);
		
		return max_sum;
	}
}

public class Main {
	public static void main(String[] args) {
		//int []nums = {12, 4, 4, 4, 78, 90, 45, 23, 23, 23, 25, 26, 22, 21, 21};
		int []nums = {5, 3, 9, 2, 7, 6, 4};
		System.out.println(new Solution().max_bitonic_length(nums));
		System.out.println(new Solution().max_bitonic_sum(nums));
	}
}