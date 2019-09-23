package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

class Solution {
	// Priority Queue
	public List<String> topKFrequent(String[] words, int k) {
		if(words == null || words.length == 0 || k == 0)
    		return null;
		Map<String, Integer> map = new HashMap<>();
		for(String s : words)
			map.put(s, map.getOrDefault(s, 0)+1);
		
		// Maintain Min Heap
		PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o1.getValue()==o2.getValue()?o2.getKey().compareTo(o1.getKey()):o1.getValue()-o2.getValue();
			}
		});
		
		for(Map.Entry<String, Integer> key : map.entrySet()) {
			pq.offer(key);
			// Remove min when size > k
			if(pq.size() > k)
				pq.poll();
		}
		
		List<String> res = new ArrayList<>();
		while(!pq.isEmpty())
			// Same as reversing
			res.add(0, pq.poll().getKey());
		return res;
	}
	
	// Bucket Sort Problem is sorted output going 0(NlgN)
    /*public List<String> topKFrequent(String[] words, int k) {
    	if(words == null || words.length == 0 || k == 0)
    		return null;
    	Map<String, Integer> map = new HashMap<>();
    	for(String s : words)
    		map.put(s, map.getOrDefault(s, 0)+1);
    	
    	List<String> []bucket = new ArrayList[words.length];
    	for(String str : map.keySet()) {
    		int index = map.get(str);
    		if(bucket[index] == null) {
    			bucket[index] = new ArrayList<>();
    		}
    		bucket[index].add(str);
    		
    	}
    	List<String> res = new ArrayList<>();
    	for(int i = bucket.length-1;i >= 0 && res.size() < k;i--) {
    		for(int j = 0;bucket[i] != null && j < bucket[i].size();j++)
    			res.add(bucket[i].get(j));
    	}
    	return res;
    }*/
}

public class Main {
	public static void main(String[] args) {
		String []words = {"i", "love", "leetcode", "i", "love", "coding"};
		int k = 2;
		System.out.println(new Solution().topKFrequent(words, k));
	}
}