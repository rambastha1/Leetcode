package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int longestSubsequence(int[] arr, int difference) {
    	int n = arr.length;
    	// arr[i] -> length of AP with common difference = difference ending at arr[i] 
    	// look for last element in the sequence
    	Map<Integer, Integer> map = new HashMap<>();
    	int maxlen = 0;
    	for(int i = 0;i < n;i++) {
    		if(!map.containsKey(arr[i] - difference))
    			map.put(arr[i], 1);
    		else 
    			map.put(arr[i], map.get(arr[i] - difference) + 1);
    		maxlen = Math.max(maxlen, map.get(arr[i]));
    	}
    	return maxlen;
    }
}

public class Main {
	public static void main(String[] args) {
		int []arr = {1,5,7,8,5,3,4,2,1};
		int difference = -2;
		System.out.println(new Solution().longestSubsequence(arr, difference));
	}
}
