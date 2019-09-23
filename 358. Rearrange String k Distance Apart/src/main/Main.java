package main;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/rearrange-string-k-distance-apart/discuss/83192/Java-7-version-of-PriorityQueue-O(nlogn)-with-comments-and-explanations
class Solution {
	
	// Time 0(Nlg26) = 0(N) not 0(NlgN) as pq/wait hold maximum 26 entries
    public String rearrangeString(String s, int k) {
    	Map<Character, Integer> map = new HashMap<>();
    	for(char c : s.toCharArray())
    		map.put(c, map.getOrDefault(c, 0)+1);
    	
    	PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
			@Override
			public int compare(Map.Entry<Character, Integer> arg0, Map.Entry<Character, Integer> arg1) {
				return arg1.getValue()-arg0.getValue();
			}
		});
    	
    	Queue<Map.Entry<Character, Integer>> wait = new LinkedList<>();
    	
    	pq.addAll(map.entrySet());
    	StringBuilder sb = new StringBuilder();
    	
    	while(!pq.isEmpty()) {
    		Map.Entry<Character, Integer> entry = pq.poll();
    		sb.append(entry.getKey());
    		entry.setValue(entry.getValue()-1);
    		// it should wait k 
    		wait.offer(entry);
    		
    		// intial k-1 chars, waitQueue not full yet
    		// this line makes sure char appears after k positions
    		if(wait.size() < k)
    			continue;
    		
    		// release from waitQueue if char is already k apart
    		Map.Entry<Character, Integer> w = wait.poll();
    		if(w.getValue() > 0)
    			pq.offer(w);
    	}
    	return sb.length() == s.length()?sb.toString():"";
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "aabbcc";
		int k = 3;
		System.out.println(new Solution().rearrangeString(s, k));
	}
}
