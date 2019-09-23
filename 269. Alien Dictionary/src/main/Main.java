package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// https://leetcode.com/problems/alien-dictionary/discuss/70119/Java-AC-solution-using-BFS

class Solution {
    public String alienOrder(String[] words) {
    	if(words == null)
    		return "";
    	Map<Character, Set<Character>> graph = new HashMap<>();
    	// character -> count
    	Map<Character, Integer> indegree = new HashMap<>();
    	
    	for(String str : words) {
    		for(char c: str.toCharArray()){
                indegree.put(c,0);
            }
    	}
    	
    	for(int i = 0;i < words.length-1;i++) {
    		String curr = words[i], next = words[i+1];
    		int len = Math.min(curr.length(), next.length());
    		for(int j = 0;j < len;j++) {
    			char c1 = curr.charAt(j);
    			if(!graph.containsKey(c1))
    				graph.put(c1, new HashSet<>());
    			char c2 = next.charAt(j);
    			if(!graph.containsKey(c2))
    				graph.put(c2, new HashSet<>());
    			if(c1 != c2) {
    				Set<Character> set = new HashSet<>();
    				if(graph.containsKey(c1))
    					set = graph.get(c1);
    				if(!set.contains(c2)) {
    					set.add(c2);
    					graph.put(c1, set);
    					indegree.put(c2, indegree.get(c2)+1);
    				}
    				break;
    			}
    		}
    	}
    	
    	Queue<Character> queue = new LinkedList<>();
    	for(Character c : indegree.keySet()) {
    		if(indegree.get(c) == 0)
    			queue.offer(c);
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	while(!queue.isEmpty()) {
    		Character c = queue.poll();
    		sb.append(c);
    		if(graph.get(c) != null && graph.get(c).size() > 0) {
	    		for(Character ch : graph.get(c)) {
	    			indegree.put(ch, indegree.get(ch) - 1);
	    			if(indegree.get(ch) == 0)
	    				queue.offer(ch);
	    		}
    		}
    	}
    	return sb.length() != indegree.size()?"":sb.toString();
    }
}

public class Main {
	public static void main(String[] args) {
		String []words = { "wrt", "wrf", "er", "ett", "rftt"};
		System.out.println(new Solution().alienOrder(words));
	}
}