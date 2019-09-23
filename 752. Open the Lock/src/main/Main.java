package main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// https://leetcode.com/problems/open-the-lock/discuss/110237/Regular-java-BFS-solution-and-2-end-BFS-solution-with-improvement

// https://www.geeksforgeeks.org/bidirectional-search/

// https://www.geeksforgeeks.org/a-search-algorithm/

class Solution {
	
	// 2 ended BFS Time 0(b^d/2)
	// b - branch factor d - height
	public int openLock(String[] deadends, String target) {
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        // Used as visited and dead list
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        begin.add("0000");
        end.add(target);
        int level = 0;
        
        while(!begin.isEmpty() && !end.isEmpty()) {
            Set<String> temp = new HashSet<>();
            for(String s : begin) {
                if(end.contains(s)) return level;
                if(deads.contains(s)) continue;
                deads.add(s);
                StringBuilder sb = new StringBuilder(s);
                for(int i = 0; i < 4; i ++) {
                    char c = sb.charAt(i);
                    String s1 = sb.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + sb.substring(i + 1);
                    String s2 = sb.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + sb.substring(i + 1);
                    if(!deads.contains(s1))
                        temp.add(s1);
                    if(!deads.contains(s2))
                        temp.add(s2);
                }
            }
            level ++;
            begin = end;
            end = temp;
        }
        return -1;
    }
	
	
	
	// Normal BFS Time 0(b^d)
	// b - branch factor d - height
    /*public int openLock(String[] deadends, String target) {
    	if(deadends == null || target == null)
    		return -1;
    	
    	Queue<String> q = new LinkedList<>();
    	Set<String> visited = new HashSet<>();
    	Set<String> deadend = new HashSet<>(Arrays.asList(deadends));
    	
    	int level = 0;
    	q.offer("0000");
    	visited.add("0000");
    	while(!q.isEmpty()) {
    		int size = q.size();
    		for(int i = 0;i < size;i++) {
    			String node = q.poll();
    			if(deadend.contains(node))
    				continue;
    			if(node.compareTo(target) == 0)
    				return level;
    			
    			StringBuilder sb = new StringBuilder(node);
    			for(int j = 0;j < 4;j++) {
    				char c = sb.charAt(j);
    				String s1 = sb.substring(0, j) + ((c==9)?0:c-'0'+1) + sb.substring(j+1);
    				String s2 = sb.substring(0, j) + ((c==0)?9:c-'0'-1) + sb.substring(j+1);
    				
    				if(!visited.contains(s1) && !deadend.contains(s1)) {
    					q.offer(s1);
    					visited.add(s1);
    				}
    				
    				if(!visited.contains(s2) && !deadend.contains(s2)) {
    					q.offer(s2);
    					visited.add(s2);
    				}
    			}
    		}
    		level++;
    	}
    	
    	return -1;
    }*/
}

public class Main {
	public static void main(String[] args) {
		String []deadends = {"0201","0101","0102","1212","2002"}; 
		String target = "0202";
		System.out.println(new Solution().openLock(deadends, target));
	}
}