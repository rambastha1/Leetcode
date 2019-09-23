package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findMaxLength(int[] nums) {
    	Map<Integer, Integer> map = new HashMap<>();
    	// count -> relative number of 0's and 1's 
    	int maxlen = 0, count = 0;
    	for(int i = 0;i < nums.length;i++) {
    		count += (nums[i] == 1)?1:-1;
    		if(count == 0)
    			maxlen = Math.max(maxlen, i+1);
    		
    		if(map.containsKey(count)) {
    			maxlen = Math.max(maxlen, i - map.get(count));
    		} else 
    			map.put(count, i);
    	}
    	return maxlen;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []nums = {0,1};
		int []nums = {0,1,0};
		System.out.println(new Solution().findMaxLength(nums));
		
	}
}
