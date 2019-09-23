package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	//bucket sort Time 0(N)
    public List<Integer> topKFrequent(int[] nums, int k) {
    	List<Integer> res = new ArrayList<>();
    	List<Integer> []bucket = new ArrayList[nums.length+1];
    	Map<Integer, Integer> map = new HashMap<>();
    	//Number -> Frequency Mapping
    	for(int num : nums)
    		map.put(num, map.getOrDefault(num, 0) + 1);
    	
    	for(int key : map.keySet()) {
    		//Higher the map.get(key) higher frequency
    		//elements are stores in increasing order of frequency
    		int freq = map.get(key);
    		if(bucket[freq] == null)
    			bucket[freq] = new ArrayList<>();
    		bucket[freq].add(key); 
    	}
    	//elements are stores in increasing order of frequency
    	//so last element has highest frequency
    	for(int i = bucket.length-1;i >=0 && res.size()<k;i--)
    		if(bucket[i] != null)
    			res.addAll(bucket[i]);    	
    	
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,1,1,2,2,3};
		int k = 2;
		System.out.println(new Solution().topKFrequent(nums, k));
	}
}