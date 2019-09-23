package main;

import java.util.Arrays;

class Solution {
	
	public int threeSumClosest(int[] nums, int target) {
		if(nums == null || nums.length == 0)
    		return 0;
		Arrays.sort(nums);
		int ans = nums[0] + nums[1] + nums[nums.length-1];
		for(int i = 0;i < nums.length-2;i++) {
			if(i > 0 && nums[i] == nums[i-1]) continue;
			int l = i+1, r = nums.length-1;
			while(l < r) {
				int sum = nums[i] + nums[l] + nums[r];
				if(Math.abs(sum - target) < Math.abs(ans - target))
					ans = sum;
				
				if(sum < target)
					l++;
				else
					r--;
			}
		}
		return ans;
	}
	
	// 0(N^2) working soluton
    /*public int threeSumClosest(int[] nums, int target) {
    	if(nums == null || nums.length == 0)
    		return 0;
    	Map<Integer, Pair<Integer, Integer>> map = new HashMap<>();
    	int n = nums.length;
    	for(int i = 0;i < n;i++) {
    		for(int j = i+1;j < n;j++) {
    			map.put((nums[i] + nums[j]), new Pair<>(i, j));
    		}
    	}
    	int min_diff = Integer.MAX_VALUE, ans = 0;
    	for(int sum : map.keySet()) {
    		Pair<Integer, Integer> pair = map.get(sum);
    		for(int i = 0;i < n;i ++) {
    			int temp = sum;
    			if(i != pair.getKey() && i != pair.getValue()) {
    				temp += nums[i];
    				if(temp == target)
    					return target;
    				if(Math.abs(target-temp) < min_diff) {
    					ans = temp;
    					min_diff = Math.abs(target-temp);
    				}
    			}
    		}
    	}
    	return ans;
    }*/
}

public class Main {
	public static void main(String[] args) {
		/*int []nums = {-1, 2, 1, -4};
		int target = 1;*/
		
		/*int []nums = {1,1,1,1};
		int target = 4;*/
		
		int []nums = {1, 1, 1, 0};
		int target = 100;
		System.out.println(new Solution().threeSumClosest(nums, target));
	}
}