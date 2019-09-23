package main;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		int []nums = {3,2,3};
		//int []nums = {2,2,1,1,1,2,2};
		System.out.println(majorityElement(nums));
	}
	
	/*public static int majorityElement(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0;i < nums.length;i++) {
			if(map.containsKey(nums[i]))
				map.put(nums[i], map.get(nums[i]) + 1);
			else
				map.put(nums[i], 1);				
		}
		for(Map.Entry<Integer, Integer> m : map.entrySet()) {
			if(m.getValue() > Math.floor(nums.length/2))
				return m.getKey();
		}
		return 0;
    }*/
	
	/*
	 * Boyer Moore Algorithm
	 * 0(n) time and constant space 
	 */	
	public static int majorityElement(int []nums) {
		
		if(nums.length == 0)
			return 0;
		
		int count = 0;
		int cand = nums[0];
		for(int num : nums) {
			if(count == 0)
				cand = num;
			count += (num == cand)?1:-1;
		}
		return cand;
	}
}
