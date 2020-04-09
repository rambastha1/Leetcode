package main;

import java.util.PriorityQueue;
import javafx.util.Pair;

class Solution {
	public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<Pair<Character, Integer>> pq = new PriorityQueue<>((p,q) -> (q.getValue() - p.getValue()));
        if(a > 0)
            pq.offer(new Pair<Character, Integer>('a', a));
        if(b > 0)
            pq.offer(new Pair<Character, Integer>('b', b));
        if(c > 0)
            pq.offer(new Pair<Character, Integer>('c', c));
        
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
        	Pair<Character, Integer> p = pq.poll();
        	// char ch = p.getKey();
        	// int val = p.getValue();
        	if(sb.length() < 2 || sb.length() >= 2 && (sb.charAt(sb.length() - 2) != p.getKey() || sb.charAt(sb.length() - 1) != p.getKey())) {
        		sb.append(p.getKey());
        		if(p.getValue() > 1)
        			pq.offer(new Pair<Character, Integer>(p.getKey(), p.getValue() - 1));
        	} else if(!pq.isEmpty()) {
        		Pair<Character, Integer> secmax = pq.poll();
        		char ch2 = secmax.getKey();
        		int val2 = secmax.getValue();
        		if((sb.charAt(sb.length() - 2) != secmax.getKey() && sb.charAt(sb.length() - 1) != secmax.getKey())) {
        			sb.append(ch2);
        			if(val2 > 1)
        				pq.offer(new Pair<Character, Integer>(secmax.getKey(), secmax.getValue() - 1));
        		}
        		pq.offer(p);
        	}
        }
        return sb.toString();
    }
}

public class Main {
	public static void main(String[] args) {
		int a = 1, b = 1, c = 7;
		System.out.println(new Solution().longestDiverseString(a, b, c));
	}
}
