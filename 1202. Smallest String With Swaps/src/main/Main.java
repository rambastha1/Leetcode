package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
	/* idea is to get each connected components
	 * get each indices and character of each component - sort and put them at right places
	 * The core of the idea is that if (0, 1) is an exchange pair and (0, 2) is an exchange pair, then any 2 in (0, 1, 2) can be exchanged.
	 * 
	 */
	public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
		int n = s.length();
		//src -> list of dest
		//gives connected components in the graph note could be many such components
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (List<Integer> p : pairs) {
        	int src = p.get(0), dest = p.get(1);
        	if(!graph.containsKey(src))
        		graph.put(src, new ArrayList<>());
        	if(!graph.containsKey(dest))
        		graph.put(dest, new ArrayList<>());
        	graph.get(src).add(dest);
        	graph.get(dest).add(src);       	
        }
        
        char[] carr = new char[n];
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < s.length(); i++) {
            if (visited[i]) 
            	continue;
            q.offer(i);
            // get indexes of all nodes in a components
            // get characters of all nodes in a components
            List<Character> ch = new ArrayList<>();
            List<Integer> index = new ArrayList<>();
            
            while (!q.isEmpty()) {
                int curr = q.poll();
                if (visited[curr]) 
                	continue;
                visited[curr] = true;
                index.add(curr);
                ch.add(s.charAt(curr));
                
                List<Integer> list = graph.get(curr);
                if(list == null)
                	continue;
                for (Integer dest : list)
                	q.offer(dest);
            }
            // sort those connected components individually
            Collections.sort(index);
            Collections.sort(ch);
            
            // put these sorted characters in sorted indexes of this connected component
            for (int k = 0; k < ch.size(); k++) {
                carr[index.get(k)] = ch.get(k);
            }
        }
        return new String(carr);
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "dcab";
		List<List<Integer>>pairs = Arrays.asList(Arrays.asList(0,3), Arrays.asList(1,2));
		System.out.println(new Solution().smallestStringWithSwaps(s, pairs));
	}
}
